package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.aa
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_pst.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DpostActivity : BaseActivity() {
    private var baseadaptr: FenAdater<*>? = null
    private val count = 0
    private var num = 1
    private var recLen = 2
    var timer = Timer()
    var list = arrayListOf<aa.DataBean.ListsBean>()
    var listInput = arrayListOf<aa.DataBean.ListsBean>()
    var dpostCardInput = 0.0
    var dpostpropertyInput = 0.0
    var dyesterdayPostInput = 0.0
    var daccumulatePostInput = 0.0
    var xx = 1
    override fun getContentViewId(): Int {
        return R.layout.activity_pst
    }

    override fun init() {
        super.init()
        StatService.onPageStart(this@DpostActivity,"MainModule.HomeView.DPOSDetailView")
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                StatService.onPageEnd(this@DpostActivity,"MainModule.HomeView.DPOSDetailView")
                finish()
            }
        })
        tit_name.setText(R.string.Dpos_tit)
        tit_iv1.setImageResource(R.drawable.eyeopen)
        Dposmeagess.setOnClickListener(object :ClickUtlis(){
            override fun onMultiClick(v: View?) {
                val intent = Intent(this@DpostActivity, MessageExActivity::class.java)
                intent.putExtra("messagess", "dpos")
                startActivity(intent)
            }
        })
        tit_iv1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (xx == 1) {
                    tit_iv1.setImageResource(R.drawable.eyeclose)
                    dpostCard.setText("****")
                    dpostproperty.setText("****")
                    dyesterdayPost.setText("****")
                    daccumulatePost.setText("****")
                    xx++
                    return
                }
                if (xx == 2) {
                    tit_iv1.setImageResource(R.drawable.eyeopen)
                    dpostCard.setText(String.format("%.2f", dpostCardInput))
                    dpostproperty.setText(String.format("%.2f", dpostpropertyInput))
                    dyesterdayPost.setText(String.format("%.2f", dyesterdayPostInput))
                    daccumulatePost.setText(String.format("%.2f", daccumulatePostInput))
                    xx--
                    return
                }
            }
        })
        dposList.setONLoadMoreListener(object : LoadMoreListView.OnLoadMoreListener {
            override fun onloadMore() {
                val task = object : TimerTask() {
                    @SuppressLint("ResourceType")
                    override fun run() {
                        runOnUiThread {
                            if (recLen == 0) {
                                num++
                                pullposload(num)
                                dposList.setLoadCompleted()
                            }
                            recLen--
                        }
                    }
                }
                timer.schedule(task, 1000, 1000)
                recLen = 2
            }

        })
        DpostLoad()
    }

    private fun pullposload(num: Int) {
        try {
            val nowtime=DateUtils.getdata()
            val retrofit = Retrofit_manager.getInstance().getUserlogin()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val pos = retrofit.create(MoneyService::class.java!!).getAntBananceLogList(sp.getString("user_id", ""), sp.getString("user_token", ""), "2", num.toString(), "10", "0",nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "")+nowtime))
            pos.enqueue(object : Callback<aa> {
                override fun onResponse(call: Call<aa>, response: Response<aa>) {
                    if (response.body()!!.code == 1) {
                        list = response.body()!!.data!!.lists as ArrayList<aa.DataBean.ListsBean>
                        listInput.addAll(list)
                        baseadaptr!!.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@DpostActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<aa>, t: Throwable) {
                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(this@DpostActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this)
        }


    }

    private fun DpostLoad() {
        try {
            val nowtime=DateUtils.getdata()
            val retrofit = Retrofit_manager.getInstance().getUserlogin()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val pos = retrofit.create(MoneyService::class.java!!).getAntBananceLogList(sp.getString("user_id", ""), sp.getString("user_token", ""), "2", count.toString(), "10", "0",nowtime,PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "")+nowtime))
            pos.enqueue(object : Callback<aa> {
                override fun onResponse(call: Call<aa>, response: Response<aa>) {
                    if (response.body()!!.code == 1) {
                        dpostCardInput = response.body()!!.data.history_dpos_ant_earnings
                        dpostpropertyInput = response.body()!!.data.history_dpos_score_earnings
                        dyesterdayPostInput = response.body()!!.data.yestoday_dpos_ant_earnings
                        daccumulatePostInput = response.body()!!.data.yestoday_dpos_score_earnings
                        dpostCard.setText(String.format("%.2f", dpostCardInput))
                        dpostproperty.setText(String.format("%.2f", dpostpropertyInput))
                        dyesterdayPost.setText(String.format("%.2f", dyesterdayPostInput))
                        daccumulatePost.setText(String.format("%.2f", daccumulatePostInput))
                        list = response.body()!!.data!!.lists as ArrayList<aa.DataBean.ListsBean>
                        listInput.addAll(list)
                        baseadaptr = object : FenAdater<aa.DataBean.ListsBean>(this@DpostActivity, listInput, R.layout.dposlayout) {
                            override fun convert(holder: ViewHolder?, item: aa.DataBean.ListsBean?) {
                                holder!!.setText(R.id.dPostUserTime, DateUtils.timeslashData(item!!.time.toString()))
                                holder!!.setText(R.id.dPostUserpr, "+"+String.format("%.2f", item!!.ant))
                                holder!!.setText(R.id.dPostUserCard,"+"+ String.format("%.2f", item!!.score))
                            }
                        }
                        dposList.adapter = baseadaptr
                    } else {
                        Toast.makeText(this@DpostActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<aa>, t: Throwable) {
                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(this@DpostActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this)
        }

    }
}
