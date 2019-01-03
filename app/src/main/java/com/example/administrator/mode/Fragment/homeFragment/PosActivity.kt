package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.PosTurn
import com.example.administrator.mode.R
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import android.content.Intent
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Utlis.*

import kotlin.collections.ArrayList


class PosActivity : BaseActivity() {
    private var baseadaptr: FenAdater<*>? = null
    var list = arrayListOf<PosTurn.DataBean.ListsBean>()
    var listInput = arrayListOf<PosTurn.DataBean.ListsBean>()
    private var count = 1
    private var recLen = 2
    var timer = Timer()
    var postCardInput = 0.0
    var postpropertyInput = 0.0
    var yesterdayPostInput = 0.0
    var accumulatePostInput = 0.0
    var xx = 1
    override fun getContentViewId(): Int {
        return R.layout.activity_post
    }

    override fun init() {
        super.init()
        StatService.onPageStart(this,"MainModule.HomeView.POSDetailView")
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                StatService.onPageEnd(this@PosActivity,"MainModule.HomeView.POSDetailView")
                finish()
            }
        })
        tit_name.setText(R.string.Post_tit)
        tit_iv1.setImageResource(R.drawable.eyeopen)
        postmeagess.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(this@PosActivity, MessageExActivity::class.java)
                intent.putExtra("messagess", "pos")
                startActivity(intent)
            }
        })
        tit_iv1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (xx == 1) {
                    tit_iv1.setImageResource(R.drawable.eyeclose)
                    postCard.setText("****")
                    postproperty.setText("****")
                    yesterdayPost.setText("****")
                    accumulatePost.setText("****")
                    xx++
                    return
                }
                if (xx == 2) {
                    tit_iv1.setImageResource(R.drawable.eyeopen)
                    postCard.setText(String.format("%.2f", postCardInput))
                    postproperty.setText(String.format("%.2f", postpropertyInput))
                    yesterdayPost.setText(String.format("%.2f", yesterdayPostInput))
                    accumulatePost.setText(String.format("%.2f", accumulatePostInput))
                    xx--
                    return
                }
            }
        })
        postLoad()
        posList.setONLoadMoreListener(object : LoadMoreListView.OnLoadMoreListener {
            override fun onloadMore() {
                val task = object : TimerTask() {
                    @SuppressLint("ResourceType")
                    override fun run() {
                        runOnUiThread {
                            if (recLen == 0) {
                                count++
                                pullload(count)
                                posList.setLoadCompleted()
                            }
                            recLen--
                        }
                    }
                }
                timer.schedule(task, 1000, 1000)
                recLen = 2
            }

        })
    }

    private fun pullload(count: Int) {
        try {
            val nowtime=DateUtils.getdata()
            val retrofit = Retrofit_manager.getInstance().getUserlogin()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val pos = retrofit.create(MoneyService::class.java!!).getAntBanancepos(sp.getString("user_id", ""), sp.getString("user_token", ""), "1", count.toString(), "10","0",nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "")+nowtime))
            pos.enqueue(object : Callback<PosTurn> {
                override fun onResponse(call: Call<PosTurn>, response: Response<PosTurn>) {
                    if (response.body()!!.code == 1) {
                        list = response.body()!!.data!!.lists as ArrayList<PosTurn.DataBean.ListsBean>
                        listInput.addAll(list)

                        baseadaptr!!.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@PosActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<PosTurn>, t: Throwable) {

                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(this@PosActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this)
        }

    }

    private fun postLoad() {
        try {val nowtime=DateUtils.getdata()
            val retrofit = Retrofit_manager.getInstance().getUserlogin()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val pos = retrofit.create(MoneyService::class.java!!).getAntBanancepos(sp.getString("user_id", ""), sp.getString("user_token", ""), "1", "1", "10", "0",nowtime,PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "")+nowtime))
            pos.enqueue(object : Callback<PosTurn> {
                override fun onResponse(call: Call<PosTurn>, response: Response<PosTurn>) {
                    if (response.body()!!.code == 1) {
                        postCardInput = response.body()!!.data.ant
                        postpropertyInput = response.body()!!.data.score
                        yesterdayPostInput = response.body()!!.data.yestoday_pos_earnings
                        accumulatePostInput = response.body()!!.data.history_pos_earnings
                        postCard.setText(String.format("%.2f", postCardInput))
                        postproperty.setText(String.format("%.2f", postpropertyInput))
                        yesterdayPost.setText(String.format("%.2f", yesterdayPostInput))
                        accumulatePost.setText(String.format("%.2f", accumulatePostInput))
                        /*   val posTurn: MutableList<PosTurn.DataBean.ListsBean>? = response.body()!!.data!!.lists
                           list = posTurn as ArrayList<PosTurn.DataBean.ListsBean>*/
                        list = response.body()!!.data!!.lists as ArrayList<PosTurn.DataBean.ListsBean>
                        listInput.addAll(list)
                        baseadaptr = object : FenAdater<PosTurn.DataBean.ListsBean>(this@PosActivity, listInput, R.layout.poslayout) {
                            override fun convert(holder: ViewHolder?, item: PosTurn.DataBean.ListsBean?) {
                                holder!!.setText(R.id.postUserId, item!!.userId.toString())
                                holder!!.setText(R.id.postUserTime, DateUtils.timeslashData(item!!.changeTime.toString()))
                                holder!!.setText(R.id.postUserCard,"+"+ String.format("%.2f", item!!.changeAmount))
                            }
                        }
                        posList.adapter = baseadaptr
                    } else {

                        Toast.makeText(this@PosActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<PosTurn>, t: Throwable) {
                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(this@PosActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this)
        }

    }
}
