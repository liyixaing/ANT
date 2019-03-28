package com.example.administrator.mode.Fragment.crowd


import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.getMyZhongChouBuyRecordBean
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.crowd_record.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CrowdRecord : Fragment() {
    private var baseadaptr: FenAdater<*>? = null
    var listInput = arrayListOf<getMyZhongChouBuyRecordBean.DataBean>()
    var num = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.crowd_record, container, false)
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
        crowdRecordList.setONLoadMoreListener {
            num += 10
            loadCrowdRecord()
            crowdRecordList.setLoadCompleted()
        }

        showCrowdRecord.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                crowdRecord.visibility = View.VISIBLE
                releaseRecord.visibility = View.GONE
                if (listInput.size > 0) {
                    listInput.clear()
                }
                loadCrowdRecord()
            }
        })
        loadCrowdRecord()
    }

    private fun loadCrowdRecord() {
        try {
            val nowtime = DateUtils.getdata()
            val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(MoneyService::class.java).getMyZhongChouBuyRecord(sp.getString("user_id", ""), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0", "", num.toString(), "10")
            register.enqueue(object : Callback<getMyZhongChouBuyRecordBean> {
                override fun onResponse(call: Call<getMyZhongChouBuyRecordBean>, response: Response<getMyZhongChouBuyRecordBean>) {
                    if (response.body()!!.code == 1) {
                        try {
                            val list = (response.body()!!.data as ArrayList<getMyZhongChouBuyRecordBean.DataBean>?)!!
                            listInput.addAll(list)
                            if (listInput.size == 0) {
                                xxxxdada.visibility = View.VISIBLE
                            } else {
                                xxxxdada.visibility = View.GONE
                            }
                            baseadaptr = object : FenAdater<getMyZhongChouBuyRecordBean.DataBean>(activity, listInput, R.layout.crowd_record_layout) {
                                override fun convert(holder: ViewHolder?, item: getMyZhongChouBuyRecordBean.DataBean?) {
                                    holder!!.setText(R.id.top2, "     " + String.format("%.2f", item!!.targetAmount))
                                    holder.setText(R.id.top4, "-" + String.format("%.2f", item.sourceAmount))
                                    holder.setText(R.id.top3, DateUtils.ymd(item.createTime.toString()))
                                }
                            }
                            crowdRecordList.adapter = baseadaptr
                        } catch (e: Exception) {

                        }
                    } else {
                    }
                }

                override fun onFailure(call: Call<getMyZhongChouBuyRecordBean>, t: Throwable) {
                    Log.e("dada", t.message)
                    val resultException = t as DataResultException
                    Toast.makeText(activity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
            Log.e("dada", e.toString())
        }
    }
}
