package com.example.administrator.mode.Fragment.crowd


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.getZhongChouDetailBean
import com.example.administrator.mode.Pojo.getZhongChouListBean
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.DateUtils
import com.example.administrator.mode.Utlis.PreferencesUtil
import com.example.administrator.mode.Utlis.Retrofit_manager
import com.example.administrator.mode.Utlis.SignatureUtil
import kotlinx.android.synthetic.main.crowd_ing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CrowdIng : Fragment() {
    private var baseadaptr: FenAdater<*>? = null
    var listInput = arrayListOf<getZhongChouListBean.DataBean>()
    var list = arrayListOf<getZhongChouListBean.DataBean>()
    private var recLen = 2
    var num = 0
    var timer = Timer()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.crowd_ing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (listInput != null) {
            listInput.clear()
        }
        super.onViewCreated(view, savedInstanceState)
        try {
            crowdIng.setONLoadMoreListener {
                                list.clear()
                                num += 10
                                loadfriend()
                crowdIng.setLoadCompleted()
            }
        } catch (e: Exception) {

        }
        crowdIng.onItemClickListener = AdapterView.OnItemClickListener { p0, p1, p2, p3 ->
            selectTargetParticulars(listInput[p2].id)
        }
        loadfriend()
    }

    private fun selectTargetParticulars(id: Int) {
        try {
            val nowtime = DateUtils.getdata()
            val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(MoneyService::class.java).getZhongChouDetail(sp.getString("user_id", ""), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0", id.toString())
            register.enqueue(object : Callback<getZhongChouDetailBean> {
                override fun onResponse(call: Call<getZhongChouDetailBean>, response: Response<getZhongChouDetailBean>) {
                    if (response.body()!!.code == 1) {
                        val intent = Intent(activity, CrowdParticularsActivity::class.java)
                        intent.putExtra("successRatio", response.body()!!.data.successAmount.toString())
                        intent.putExtra("successAmount", response.body()!!.data.amount.toString())
                        intent.putExtra("releaseDays", DateUtils.countDifferDay(response.body()!!.data!!.endTime.toString(), response.body()!!.data.startTime.toString()))
                        intent.putExtra("crowdId", response.body()!!.data.id)
                        intent.putExtra("crowdname", response.body()!!.data.itemName)
                        intent.putExtra("crowdDesc", response.body()!!.data.itemDesc)
                        startActivity(intent)
                    } else {
                    }
                }

                override fun onFailure(call: Call<getZhongChouDetailBean>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(activity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {

        }

    }

    private fun loadfriend() {
        try {
            val nowtime = DateUtils.getdata()
            val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(MoneyService::class.java).getZhongChouList(sp.getString("user_id", ""), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0", "1", "1", "2", num.toString(), "10")
            register.enqueue(object : Callback<getZhongChouListBean> {
                override fun onResponse(call: Call<getZhongChouListBean>, response: Response<getZhongChouListBean>) {
                    if (response.body()!!.code == 1) {
                        try {
                            list = (response.body()!!.data as ArrayList<getZhongChouListBean.DataBean>?)!!
                            listInput.addAll(list)
                            baseadaptr = object : FenAdater<getZhongChouListBean.DataBean>(activity, listInput, R.layout.crowdfunding_layout) {
                                override fun convert(holder: ViewHolder?, item: getZhongChouListBean.DataBean?) {
                                    holder!!.setText(R.id.crawdNameTextView, item!!.targetCoinName)
                                    holder.setText(R.id.crawdDaysTextView, getString(R.string.ReleaseTheNumberOfDays) + item.releaseDays)
                                    holder.setText(R.id.crawdCurrencyTextView, getString(R.string.AcceptTheCurrency) + item.sourceCoinName)
                                    holder.setText(R.id.crawdProportionTextView, getString(R.string.ReleaseRate) + String.format("%.2f", (item.rate * 100)) + "%")
                                    holder.setText(R.id.crawdAstrictTextView, getString(R.string.IdIimitation) + item.perPersonLimit)
                                    holder.setText(R.id.crawdScaleTextView, item.amount.toString())
                                    holder.setText(R.id.priceScaleTextView, "1:" + item.rate)
                                    holder.setText(R.id.scheduleTextView, String.format("%.2f", (item.successRatio * 100)) + "%")
                                    holder.setText(R.id.crawdTimeTextView, DateUtils.timeslashData(item.startTime.toString()))
                                    holder.ViewTextBackground(R.id.immediatelyBuy, "#00b07c", getString(R.string.buyThisNow))
                                    holder.setProgressBarSchedule(R.id.scheduleProgressBar, String.format("%.0f", (item.successRatio * 100)).toInt())
                                    if (item.status == 1) {
                                        holder.setText(R.id.crowdType, getString(R.string.underwayStarted))
                                        holder.setText(R.id.timeRemainingTextView, getString(R.string.underwayStarted))
                                    }
                                }
                            }
                            crowdIng.adapter = baseadaptr
                        } catch (e: Exception) {

                        }
                    } else {
                    }
                }

                override fun onFailure(call: Call<getZhongChouListBean>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(activity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
        }
    }
}
