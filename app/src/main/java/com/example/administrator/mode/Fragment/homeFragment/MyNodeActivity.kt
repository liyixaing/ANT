package com.example.administrator.mode.Fragment.homeFragment

import android.content.Context
import android.content.Intent

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.MyNodeTurn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_my_node.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyNodeActivity : BaseActivity() {
    var num = 1
    var tttt = ""
    var t1lList = arrayListOf<MyNodeTurn.DataBean.ListsBean>()
    override fun getContentViewId(): Int {
        return R.layout.activity_my_node
    }

    override fun init() {
        super.init()
        StatService.onPageStart(this@MyNodeActivity, "MainModule.HomeView.NodeListView")
        if (PreferencesUtil.get("language", "") == "zh") {
            tttt = "节点"

        } else if (PreferencesUtil.get("language", "") == "en") {
            tttt = "node"
        } else {
            tttt = "节点"
        }
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                StatService.onPageEnd(this@MyNodeActivity, "MainModule.HomeView.NodeListView")
                finish()
            }
        })

        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val edit = sp.edit()

        /*  if (sp.getBoolean("lookNode", true)) {
              tit_iv1.setImageResource(R.drawable.eyeopen)
          } else {
              tit_iv1.setImageResource(R.drawable.eyeclose)
          }*/
        myNodeListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, _ -> startActivity(Intent(this@MyNodeActivity, NodeAwardActivity::class.java)) }
        tit_iv1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (num == 1) {
                    tit_iv1.setImageResource(R.drawable.eyeclose)
                    edit.putBoolean("lookNode", true)
                    edit.commit()
                    num++
                } else if (num == 2) {
                    getMyNode()
                    tit_iv1.setImageResource(R.drawable.eyeopen)
                    edit.putBoolean("lookNode", false)
                    edit.commit()
                    num--
                }
            }
        })

        tit_name.setText(R.string.MyNode_tit)
        getMyNode()
    }

    private fun getMyNode() {
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val retrofit = Retrofit_manager.getInstance().userlogin
        val nowtime = DateUtils.getdata()
        val login = retrofit.create(MoneyService::class.java).getMyNode(sp.getString("user_id", ""), sp.getString("user_token", ""), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
        login.enqueue(object : Callback<MyNodeTurn> {
            override fun onResponse(call: Call<MyNodeTurn>, response: Response<MyNodeTurn>) {
                try {
                    if (response.body()!!.code == 1) {
                        if (response.body()!!.data!!.nodes == 0) {
                            myNodeListView.visibility = View.GONE
                            re.visibility = View.VISIBLE
                        } else {
                            var num = 0
                            t1lList = response.body()!!.data!!.lists as ArrayList<MyNodeTurn.DataBean.ListsBean>
                            myNodeListView.adapter = object : FenAdater<MyNodeTurn.DataBean.ListsBean>(this@MyNodeActivity, t1lList, R.layout.mynodelayout) {
                                override fun convert(holder: ViewHolder?, item: MyNodeTurn.DataBean.ListsBean?) {
                                    holder!!.setText(R.id.t1, "T" + "${num + 1}" + tttt)
                                    holder.setText(R.id.t1Name, item!!.username)
                                    holder.setText(R.id.t1Userid, item.id.toString())
                                    holder.setText(R.id.t1UserPhone, item.phone)
                                    holder.setText(R.id.t1Card, String.format("%.2f", item.score))
                                    holder.setText(R.id.t1Recommend, item.introducer.toString())
                                    if (PreferencesUtil.get("language", "").equals("zh")) {
                                        holder.setText(R.id.t1Num, "节点量:" + item.childs)
                                    } else if (PreferencesUtil.get("language", "").equals("en")) {
                                        holder.setText(R.id.t1Num, "Invite the number:" + item.childs)
                                    } else {
                                        holder.setText(R.id.t1Num, "节点量:" + item.childs)
                                    }
                                    holder.setText(R.id.t1RecommendPhone, item.introducerPhone)
                                    if (item.avatar != null) {
                                        holder.setImageByUrl(R.id.t1Hade, item.avatar)
                                    }
                                    num++
                                    if (num == 2) {
                                        num = 0
                                    }
                                }
                            }
                        }
                        /*if (response.body()!!.data!!.nodes == 1) {
                            t1lList = response.body()!!.data!!.t1 as ArrayList<MyNodeTurn.DataBean.>
                            t2.visibility = View.GONE
                            relativeT2.visibility = View.GONE
                            for (x in t1lList) {
                                t1Name.setText(x.username)
                                t1Userid.setText(x.id)
                                t1UserPhone.setText(x.phone)T1Bean
                                t1Card.setText("资产量:" + x.parentNode.toString())
                                t1Recommend.setText("推荐人ID:" + x.introducer.toString())
                                t1Num.setText("节点量:" + x.childs)
                                t1RecommendPhone.setText(x.introducerPhone)
                                if (x.avatar != null) {
                                    Glide.with(this@MyNodeActivity).load(x.avatar).centerCrop().transform(GlideCircleTransform(this@MyNodeActivity)).into(t1Hade)
                                }
                            }
                        } else if (response.body()!!.data!!.nodes == 2) {
                            t1lList = response.body()!!.data!!.t1 as ArrayList<MyNodeTurn.DataBean.T1Bean>
                            for (x in t1lList) {
                               Log.i("whz",x.username)
                                t1Name.setText(x.username)
                                t1Userid.setText(x.id)
                                t1UserPhone.setText(x.phone)
                                t1Card.setText("资产量:" + x.parentNode.toString())
                                t1Recommend.setText("推荐人ID:" + x.introducer.toString())
                                t1Num.setText("节点量:" + x.childs)
                                t1RecommendPhone.setText(x.introducerPhone)
                                if (x.avatar != null) {
                                    Glide.with(this@MyNodeActivity).load(x.avatar).centerCrop().transform(GlideCircleTransform(this@MyNodeActivity)).into(t1Hade)
                                }
                            }
                            t2lList = response.body()!!.data!!.t1 as ArrayList<MyNodeTurn.DataBean.T2Bean>
                            for (z in t2lList) {
                                t2Name.setText(z.username)
                                t2Userid.setText(z.id)
                                t2UserPhone.setText(z.phone)
                                t2Card.setText("资产量:" + z.parentNode.toString())
                                t2Recommend.setText("推荐人ID:" + z.introducer.toString())
                                t2Num.setText("节点量:" + z.childs)
                                t2RecommendPhone.setText(z.introducerPhone)
                                if (z.avatar != null) {
                                    Glide.with(this@MyNodeActivity).load(z.avatar).centerCrop().transform(GlideCircleTransform(this@MyNodeActivity)).into(t2Hade)
                                }
                            }*/
                    }
                } catch (e: Exception) {
                }
            }

            override fun onFailure(call: Call<MyNodeTurn>, t: Throwable) {

                if (t is DataResultException) {
                    Toast.makeText(this@MyNodeActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
