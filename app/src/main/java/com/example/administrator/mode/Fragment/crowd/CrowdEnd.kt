package com.example.administrator.mode.Fragment.crowd


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.getZhongChouListBean
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.DateUtils
import com.example.administrator.mode.Utlis.PreferencesUtil
import com.example.administrator.mode.Utlis.Retrofit_manager
import com.example.administrator.mode.Utlis.SignatureUtil
import kotlinx.android.synthetic.main.crowd_end.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CrowdEnd : Fragment() {
    private var baseadaptr: FenAdater<*>? = null
    var listInput = arrayListOf<getZhongChouListBean.DataBean>()
    var num = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.crowd_end, container, false)
    }

    override fun onStart() {
        super.onStart()
        num = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (listInput != null) {
            listInput.clear()
        }
        try {
            crowdEnd.setONLoadMoreListener {

                                num += 10
                                loadfriend()
                                crowdEnd.setLoadCompleted()
            }
        } catch (e: Exception) {

        }

        loadfriend()
    }

    private fun loadfriend() {
        try {
            val nowtime = DateUtils.getdata()
            val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(MoneyService::class.java).getZhongChouList(sp.getString("user_id", ""), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0", "2", "1", "2", num.toString(), "10")
            register.enqueue(object : Callback<getZhongChouListBean> {
                override fun onResponse(call: Call<getZhongChouListBean>, response: Response<getZhongChouListBean>) {
                    if (response.body()!!.code == 1) {
                        try {
                            var list = (response.body()!!.data as ArrayList<getZhongChouListBean.DataBean>?)!!
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
                                    holder.ViewTextColor(R.id.immediatelyBuy, "#b9b9b9", getString(R.string.buyThisNow))
                                    holder.setProgressBarSchedule(R.id.scheduleProgressBar, String.format("%.0f", (item.successRatio * 100)).toInt())
                                    if (item.status == 2) {
                                        holder.ViewTextBackground(R.id.crowdType, "#00b07c", getString(R.string.finishedStarted))
                                        holder.setText(R.id.timeRemainingTextView, getString(R.string.finishedStarted))
                                    }
                                }
                            }
                            crowdEnd.adapter = baseadaptr
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
