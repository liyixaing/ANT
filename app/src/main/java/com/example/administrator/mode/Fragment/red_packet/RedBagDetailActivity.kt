package com.example.administrator.mode.Fragment.red_packet

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.DrawSingleEnvelope
import com.example.administrator.mode.Pojo.geSingleEnvelopeList
import com.example.administrator.mode.Pojo.getRedEnvelopeRecordList
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_red_bag_detail.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*

class RedBagDetailActivity : BaseActivity() {
    private var recLen = 2
    private var baseadaptr: FenAdater<*>? = null
    var num = 0
    var num1 = 0
    var timer = Timer()
    var list = arrayListOf<geSingleEnvelopeList.DataBean>()
    var listInput = arrayListOf<geSingleEnvelopeList.DataBean>()
    var list1 = arrayListOf<getRedEnvelopeRecordList.DataBean>()
    var listInput1 = arrayListOf<getRedEnvelopeRecordList.DataBean>()

    override fun getContentViewId(): Int {
        return R.layout.activity_red_bag_detail
    }

    override fun init() {
        try {
            if (intent.extras.get("userMessageRedBag") == "main") {
                redDetail.visibility = View.VISIBLE
                lodeRedBag()
            }
        } catch (e: Exception) {
            redDetail1.visibility = View.VISIBLE
            redDetail1Detail()
            areaMessage.setTextColor(Color.parseColor("#FFFFFF"))
            areaMessage.setBackgroundColor(Color.parseColor("#00b07c"))
            commonMessage.setTextColor(Color.parseColor("#666666"))
            commonMessage.setBackgroundColor(Color.parseColor("#F5F5F7"))
        }

        areaMessage.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                areaMessage.setTextColor(Color.parseColor("#FFFFFF"))
                areaMessage.setBackgroundColor(Color.parseColor("#00b07c"))
                commonMessage.setTextColor(Color.parseColor("#666666"))
                commonMessage.setBackgroundColor(Color.parseColor("#F5F5F7"))
                redDetail.visibility = View.GONE
                redDetail1.visibility = View.VISIBLE
                num1 = 0
                listInput1.clear()
                redDetail1Detail()
            }
        })

        commonMessage.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                commonMessage.setTextColor(Color.parseColor("#FFFFFF"))
                commonMessage.setBackgroundColor(Color.parseColor("#00b07c"))
                areaMessage.setTextColor(Color.parseColor("#666666"))
                areaMessage.setBackgroundColor(Color.parseColor("#F5F5F7"))
                redDetail.visibility = View.VISIBLE
                redDetail1.visibility = View.GONE
                num = 0
                listInput.clear()
                lodeRedBag()
            }
        })
        tit_iv.setOnClickListener { finish() }
        tit_name.setText(R.string.RedEnvelopeMyRedBag)
        redDetail.setONLoadMoreListener {
            val task = object : TimerTask() {
                @SuppressLint("ResourceType")
                override fun run() {
                    runOnUiThread {
                        if (recLen == 0) {
                            num++
                            lodeRedBag()
                            redDetail.setLoadCompleted()
                        }
                        recLen--
                    }
                }
            }
            timer.schedule(task, 1000, 1000)
            recLen = 2
        }

        redDetail1.setONLoadMoreListener {
            val task = object : TimerTask() {
                @SuppressLint("ResourceType")
                override fun run() {
                    runOnUiThread {
                        if (recLen == 0) {
                            num1++
                            redDetail1Detail()
                            redDetail1.setLoadCompleted()
                        }
                        recLen--
                    }
                }
            }
            timer.schedule(task, 1000, 1000)
            recLen = 2
        }
    }

    private fun getRed(id: Int) {
        try {
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(MoneyService::class.java).drawSingleEnvelope(sp.getString("user_id", ""), id.toString(), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
            register.enqueue(object : Callback<DrawSingleEnvelope> {
                override fun onResponse(call: Call<DrawSingleEnvelope>, response: Response<DrawSingleEnvelope>) {
                    if (response.body()!!.code == 1) {
                        num += 10
                        listInput.clear()
                        lodeRedBag()
                        Toast.makeText(this@RedBagDetailActivity, getString(R.string.Toreceivethe) + response.body()!!.data.score.toString() + getString(R.string.RedEnvelopeProperty), Toast.LENGTH_SHORT).show()
                    } else {
                    }
                }

                override fun onFailure(call: Call<DrawSingleEnvelope>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@RedBagDetailActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: java.lang.Exception) {

        }
    }

    private fun redDetail1Detail() {
        try {
            val retrofit = Retrofit_manager.getInstance().userlogin
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            var id = sp.getString("user_id", "")
            val nowtime = DateUtils.getdata()
            val login = retrofit.create(MoneyService::class.java!!).getRedEnvelopeRecordList(id, "1", "0", num1.toString(), "10", nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
            login.enqueue(object : Callback<getRedEnvelopeRecordList> {
                override fun onResponse(call: Call<getRedEnvelopeRecordList>, response: Response<getRedEnvelopeRecordList>) {
                    if (response.body()!!.code == 1) {
                        try {
                            val fe: ArrayList<getRedEnvelopeRecordList.DataBean>? = response.body()!!.data as ArrayList<getRedEnvelopeRecordList.DataBean>?
                            list1 = fe as ArrayList<getRedEnvelopeRecordList.DataBean>
                            listInput1.addAll(list1)
                            baseadaptr = object : FenAdater<getRedEnvelopeRecordList.DataBean>(this@RedBagDetailActivity, listInput1, R.layout.red_detail_layout) {
                                override fun convert(holder: ViewHolder?, item: getRedEnvelopeRecordList.DataBean?) {
                                    holder!!.setText(R.id.redDetailId, DateUtils.ymd(item!!.changeTime.toString()))
                                    holder.setText(R.id.redDetailCard, item.changeScore.toString() + getString(R.string.RedEnvelopeProperty))
                                    holder.setText(R.id.redDetailTime, DateUtils.hm(item.changeTime.toString()))
                                    if (item.envelopeType == 5) {
                                        holder.setText(R.id.redDetailCard, getString(R.string.RedEnvelopeArea))
                                    }
                                    holder.setText(R.id.redDetailType, 1, 1, item.changeScore.toString() + getString(R.string.RedEnvelopeProperty))
                                }
                            }
                            redDetail1.adapter = baseadaptr
                        } catch (e: Exception) {
                        }
                    } else {
                        Toast.makeText(this@RedBagDetailActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<getRedEnvelopeRecordList>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@RedBagDetailActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: java.lang.Exception) {

        }
    }

    fun lodeRedBag() {
        val nowtime = DateUtils.getdata()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val retrofit = Retrofit_manager.getInstance().userlogin
        val login = retrofit.create(MoneyService::class.java).geSingleEnvelopeList(sp.getString("user_id", ""), "", num.toString(), "10", nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
        login.enqueue(object : Callback<geSingleEnvelopeList> {
            override fun onResponse(call: Call<geSingleEnvelopeList>, response: Response<geSingleEnvelopeList>) {
                if (response.body()!!.code == 1) {
                    val fe: ArrayList<geSingleEnvelopeList.DataBean>? = response.body()!!.data as ArrayList<geSingleEnvelopeList.DataBean>?
                    list = fe as ArrayList<geSingleEnvelopeList.DataBean>
                    listInput.addAll(list)
                    baseadaptr = object : FenAdater<geSingleEnvelopeList.DataBean>(this@RedBagDetailActivity, listInput, R.layout.red_detail_layout) {
                        override fun convert(holder: ViewHolder?, item: geSingleEnvelopeList.DataBean?) {
                            holder!!.setText(R.id.redDetailId, "ID:" + item!!.sendId.toString())
                            holder.setText(R.id.redDetailCard, item.amountScore.toString() + getString(R.string.RedEnvelopeProperty))
                            holder!!.setText(R.id.redDetailTime, DateUtils.hm(item!!.createTime.toString()))
                            if (item.status == 0) {
                                holder.setText(R.id.redDetailType, 1, getString(R.string.get_red))
                            } else if (item.status == 1) {
                                holder.setText(R.id.redDetailType, 1, 1, getString(R.string.already_received))
                            }
                        }
                    }
                    redDetail.adapter = baseadaptr
                    redDetail.onItemClickListener = AdapterView.OnItemClickListener { p0, p1, p2, p3 ->
                        if (listInput[p2].status == 0) {
                            getRed(listInput[p2].id)
                        }
                    }

                } else {
                    Toast.makeText(this@RedBagDetailActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<geSingleEnvelopeList>, t: Throwable) {
                if (t is DataResultException) {
                    Toast.makeText(this@RedBagDetailActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}