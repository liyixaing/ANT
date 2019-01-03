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
import com.example.administrator.mode.Pojo.NodeTurn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_node_award.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class NodeAwardActivity : BaseActivity() {
    private var baseadaptr: FenAdater<*>? = null
    private var count = 1
    private var recLen = 2
    var list = arrayListOf<NodeTurn.DataBean.ListsBean>()
    var listInput = arrayListOf<NodeTurn.DataBean.ListsBean>()
    var timer = Timer()
    var xx = 1
    var nodeCardInput = 0.0
    var nodepropertyInput = 0.0
    var yesterdayNodeInput = 0.0
    var accumulateNodeInput = 0.0
    var zichan1=""
    var tongzheng=""
    override fun getContentViewId(): Int {
        return R.layout.activity_node_award
    }

    override fun init() {
        super.init()
        StatService.onPageStart(this,"MainModule.HomeView.NodeListView.NodeDetailView")
        if (PreferencesUtil.get("language", "").equals("ch")) {
            zichan1="资产"
            tongzheng="通证"

        } else if (PreferencesUtil.get("language", "").equals("en")) {
            zichan1="asset"
            tongzheng="token"
        }
        tit_iv1.setImageResource(R.drawable.eyeopen)
        nodeMeagess.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(this@NodeAwardActivity, MessageExActivity::class.java)
                intent.putExtra("messagess", "node")
                startActivity(intent)
            }
        })
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                StatService.onPageEnd(this@NodeAwardActivity,"MainModule.HomeView.NodeListView.NodeDetailView")
                finish()
            }
        })
        tit_name.setText(R.string.Node_tit)
        tit_iv1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (xx == 1) {
                    tit_iv1.setImageResource(R.drawable.eyeclose)
                    nodeCard.text = "****"
                    nodeproperty.text = "****"
                    dyesterdaynode.text = "****"
                    daccumulatenode.text = "****"
                    xx++
                    return
                }
                if (xx == 2) {
                    tit_iv1.setImageResource(R.drawable.eyeopen)
                    nodeCard.text = String.format("%.2f", nodeCardInput)
                    nodeproperty.text = String.format("%.2f", nodepropertyInput)
                    dyesterdaynode.text = String.format("%.2f", yesterdayNodeInput)
                    daccumulatenode.text = String.format("%.2f", accumulateNodeInput)
                    xx--
                    return
                }
            }
        })
        nodeList.setONLoadMoreListener {
            val task = object : TimerTask() {
                @SuppressLint("ResourceType")
                override fun run() {
                    runOnUiThread {
                        if (recLen == 0) {
                            count++
                            pullload(count)
                            nodeList.setLoadCompleted()
                        }
                        recLen--
                    }
                }
            }
            timer.schedule(task, 1000, 1000)
            recLen = 2
        }
        pullFirstLoad()
    }

    private fun pullload(count: Int) {
        try {
            val nowtime = DateUtils.getdata()
            val retrofit = Retrofit_manager.getInstance().userlogin
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val pos = retrofit.create(MoneyService::class.java).getAntBananceNodeList(sp.getString("user_id", ""), sp.getString("user_token", ""), "3", count.toString(), "10", "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            pos.enqueue(object : Callback<NodeTurn> {
                override fun onResponse(call: Call<NodeTurn>, response: Response<NodeTurn>) {
                    if (response.body()!!.code == 1) {
                        list = response.body()!!.data!!.lists as ArrayList<NodeTurn.DataBean.ListsBean>
                        listInput.addAll(list)
                        baseadaptr!!.notifyDataSetChanged()
                    } else {

                        Toast.makeText(this@NodeAwardActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<NodeTurn>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@NodeAwardActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this)
        }
    }

    private fun pullFirstLoad() {
        try {
            val nowtime = DateUtils.getdata()
            val retrofit = Retrofit_manager.getInstance().userlogin
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val pos = retrofit.create(MoneyService::class.java).getAntBananceNodeList(sp.getString("user_id", ""), sp.getString("user_token", ""), "3", "1", "10", "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            pos.enqueue(object : Callback<NodeTurn> {
                override fun onResponse(call: Call<NodeTurn>, response: Response<NodeTurn>) {
                    if (response.body()!!.code == 1) {
                        nodeCardInput = response.body()!!.data.yestoday_node_ant_earnings
                        nodepropertyInput = response.body()!!.data.history_node_ant_earnings
                        yesterdayNodeInput = response.body()!!.data.yestoday_node_score_earnings
                        accumulateNodeInput = response.body()!!.data.history_node_score_earnings
                        nodeCard.text = String.format("%.2f", nodeCardInput)
                        nodeproperty.text = String.format("%.2f", nodepropertyInput)
                        dyesterdaynode.text = String.format("%.2f", yesterdayNodeInput)
                        daccumulatenode.text = String.format("%.2f", accumulateNodeInput)
                        /*   val posTurn: MutableList<PosTurn.DataBean.ListsBean>? = response.body()!!.data!!.lists
                           list = posTurn as ArrayList<PosTurn.DataBean.ListsBean>*/
                        list = response.body()!!.data!!.lists as ArrayList<NodeTurn.DataBean.ListsBean>
                        listInput.addAll(list)
                        baseadaptr = object : FenAdater<NodeTurn.DataBean.ListsBean>(this@NodeAwardActivity, listInput, R.layout.poslayout) {
                            override fun convert(holder: ViewHolder?, item: NodeTurn.DataBean.ListsBean?) {
                                if (item!!.changeType == 7) {
                                    holder!!.setText(R.id.postUserId, "T1")
                                } else {
                                    holder!!.setText(R.id.postUserId, "T2")
                                }
                                holder.setText(R.id.postUserTime, DateUtils.timeslashData(item.changeTime.toString()))
                                if (item.isAnt != 1) {
                                    holder.setText(R.id.postUserCard, "+" + String.format("%.2f", item.changeAmount) + zichan1)
                                } else {
                                    holder.setText(R.id.postUserCard, "+" + String.format("%.2f", item.changeAmount) + tongzheng)
                                }

                            }
                        }
                        nodeList.adapter = baseadaptr
                    } else {

                        Toast.makeText(this@NodeAwardActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<NodeTurn>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@NodeAwardActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this)
        }

    }
}

