package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.bumptech.glide.Glide
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.Friendtrun
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_community_friend.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CommunityFriendActivity : BaseActivity() {

    val siz = 10
    var xx = 0
    var num = 1
    private var recLen = 2
    var timer = Timer()
    var list = arrayListOf<Friendtrun.DataBean.ListBean>()
    var listInput = arrayListOf<Friendtrun.DataBean.ListBean>()
    var ee = ""
    var ww = ""
    var tt = ""
    var rr = ""
    var aaa = ""
    var bbb = ""
    var zzz = ""
    var eeqq = ""
    var rrr = ""
    private var baseadaptr: FenAdater<*>? = null
    override fun getContentViewId(): Int {
        return R.layout.activity_community_friend
    }

    override fun init() {
        super.init()
        StatService.onPageStart(this, " MainModule.HomeView.ActivateFriendsView")
        if (PreferencesUtil.get("language", "").equals("zh")) {
            ee = "默认分配节点"
            ww = "手动分配节点"
            tt = "确定"
            rr = "取消"
            aaa = "请选择分配方式"
            bbb = "是否确认分配默认接点"
            zzz = "已激活"
            rrr = "未激活"
            eeqq = "邀请人数:"
        } else if (PreferencesUtil.get("language", "").equals("en")) {
            ee = "Default allocation node"
            ww = "Manual node allocation"
            tt = "confirm"
            rr = "cancel"
            aaa = "Please select allocation method"
            bbb = "Verify that default contacts are assigned"
            zzz = "activated"
            rrr = "nonactivated"
            eeqq = "Invite the number:"
        } else {
            ee = "默认分配节点"
            ww = "手动分配节点"
            tt = "确定"
            rr = "取消"
            aaa = "请选择分配方式"
            bbb = "是否确认分配默认接点"
            zzz = "已激活"
            rrr = "未激活"
            eeqq = "邀请人数:"
        }
        var items = arrayOf(ee, ww)
        var itemsa = arrayOf(tt, rr)
        loadfriend()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                StatService.onPageEnd(this@CommunityFriendActivity, "MainModule.HomeView.ActivateFriendsView")
                finish()
            }
        })
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        if (sp.getString("useravatar", "") != "") {
            Glide.with(this@CommunityFriendActivity).load(sp.getString("useravatar", "")).centerCrop().transform(GlideCircleTransform(this@CommunityFriendActivity)).into(head)
        }
        tit_name.setText(R.string.Home_friend)
        community_lv.setONLoadMoreListener(object : LoadMoreListView.OnLoadMoreListener {
            override fun onloadMore() {
                val task = object : TimerTask() {
                    @SuppressLint("ResourceType")
                    override fun run() {
                        runOnUiThread {
                            if (recLen == 0) {
                                num++
                                secondlaod(num)
                                community_lv.setLoadCompleted()
                            }
                            recLen--
                        }
                    }
                }
                timer.schedule(task, 1000, 1000)
                recLen = 2
            }

        })
        community_lv.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (listInput.get(p2).parentNode.toString().equals("0")) {
                    val alertDialog = AlertDialog.Builder(this@CommunityFriendActivity)
                            .setTitle(aaa)
                            .setItems(items, DialogInterface.OnClickListener { dialogInterface, i ->
                                if (items[i] == "默认分配节点") {
                                    try {
                                        val alertDialoga = AlertDialog.Builder(this@CommunityFriendActivity)
                                                .setTitle(bbb)
                                                .setItems(itemsa, DialogInterface.OnClickListener { dialogInterface, i ->
                                                    if (itemsa[i] == "确定") {
                                                        try {
                                                            default(listInput.get(p2).id.toString())
                                                        } catch (e: Exception) {
                                                        }
                                                    } else if (itemsa[i] == "取消") {
                                                    } else if (itemsa[i] == "confirm") {
                                                        try {
                                                            default(listInput.get(p2).id.toString())
                                                        } catch (e: Exception) {
                                                        }
                                                    } else if (itemsa[i] == "cancel") {
                                                    }
                                                })
                                                .create()
                                        alertDialoga.show()
                                    } catch (e: Exception) {
                                    }
                                } else if (items[i] == "Default allocation node") {
                                    try {
                                        val alertDialoga = AlertDialog.Builder(this@CommunityFriendActivity)
                                                .setTitle(bbb)
                                                .setItems(itemsa, DialogInterface.OnClickListener { dialogInterface, i ->
                                                    if (itemsa[i] == "确定") {
                                                        try {
                                                            default(listInput.get(p2).id.toString())
                                                        } catch (e: Exception) {
                                                        }
                                                    } else if (itemsa[i] == "取消") {
                                                    } else if (itemsa[i] == "confirm") {
                                                        try {
                                                            default(listInput.get(p2).id.toString())
                                                        } catch (e: Exception) {
                                                        }
                                                    } else if (itemsa[i] == "cancel") {
                                                    }
                                                })
                                                .create()
                                        alertDialoga.show()
                                    } catch (e: Exception) {
                                    }
                                } else if (items[i] == "Manual node allocation") {
                                    try {
                                        val intent = Intent(this@CommunityFriendActivity, AllocationActivity::class.java)
                                        intent.putExtra("uid", list.get(p2).id.toString())
                                        intent.putExtra("allocation", "manual")
                                        startActivity(intent)
                                        finish()
                                    } catch (e: Exception) {
                                    }
                                } else if (items[i] == "手动分配节点") {
                                    try {
                                        val intent = Intent(this@CommunityFriendActivity, AllocationActivity::class.java)
                                        intent.putExtra("uid", list.get(p2).id.toString())
                                        intent.putExtra("allocation", "manual")
                                        startActivity(intent)
                                        finish()
                                    } catch (e: Exception) {
                                    }
                                }
                            })
                            .create()
                    alertDialog.show()
                } else {
                    val intent = Intent(this@CommunityFriendActivity, FriendParticularsActivity::class.java)
                    intent.putExtra("uNode", listInput.get(p2).parentNode.toString())
                    intent.putExtra("uIdInput", listInput.get(p2).id.toString())
                    intent.putExtra("uPhone", listInput.get(p2).phone.toString())
                    intent.putExtra("uName", listInput.get(p2).username.toString())
                    startActivity(intent)
                    finish()
                }
            }
        })
    }

    private fun secondlaod(num: Int) {
        val retrofit = Retrofit_manager.getInstance().getUserlogin()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        var token = sp.getString("user_token", "")
        var id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java!!).getfriend(id, token, num.toString(), siz.toString(), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<Friendtrun> {
                override fun onResponse(call: Call<Friendtrun>, response: Response<Friendtrun>) {
                    try {
                        if (response.body()!!.code == 1) {
                            val fe: List<Friendtrun.DataBean.ListBean>? = response.body()!!.data!!.list
                            list = fe as ArrayList<Friendtrun.DataBean.ListBean>
                            listInput.addAll(list)
                            baseadaptr!!.notifyDataSetChanged()
                        } else {
                            Toast.makeText(this@CommunityFriendActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                    }
                }

                override fun onFailure(call: Call<Friendtrun>, t: Throwable) {
                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(this@CommunityFriendActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this@CommunityFriendActivity)
        }
    }

    override fun onStart() {
        super.onStart()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        community_name.setText(sp.getString("user_name", ""))
        community_phone.setText(sp.getString("user_phone", ""))
    }

    fun loadfriend() {
        val retrofit = Retrofit_manager.getInstance().getUserlogin()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        var token = sp.getString("user_token", "")
        var id = sp.getString("user_id", "")
        try {
            val nowtime = DateUtils.getdata()
            val login = retrofit.create(MoneyService::class.java!!).getfriend(id, token, "1", siz.toString(), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<Friendtrun> {
                override fun onResponse(call: Call<Friendtrun>, response: Response<Friendtrun>) {
                    try {
                        if (response.body()!!.code == 1) {
                            xx = response.body()!!.data!!.total
                            if (xx != 0) {
                                if (sp.getString("language", "").equals("zh")) {
                                    community_user.setText(eeqq + xx)
                                } else {
                                    community_user.setText(eeqq + xx)
                                }
                            }
                            val fe: List<Friendtrun.DataBean.ListBean>? = response.body()!!.data!!.list
                            list = fe as ArrayList<Friendtrun.DataBean.ListBean>
                            /* Baseadaptr<Any>(this@CommunityFriendActivity, list)*/
                            listInput.addAll(list)
                            baseadaptr = object : FenAdater<Friendtrun.DataBean.ListBean>(this@CommunityFriendActivity, listInput, R.layout.friendlayout) {
                                override fun convert(holder: ViewHolder?, item: Friendtrun.DataBean.ListBean?) {
                                    holder!!.setText(R.id.friend_name, item!!.username)
                                    holder!!.setText(R.id.friend_phone, item.phone)
                                    if (item.parentNode.toString().equals("0")) {
                                        holder!!.setText(R.id.friend_card, rrr)
                                    } else {
                                        holder!!.setText(R.id.friend_card, zzz)
                                    }
                                }
                            }
                            community_lv.adapter = baseadaptr
                            /*     community_lv.setAdapter(object : FenAdater<Friendtrun.DataBean.ListBean>(this@CommunityFriendActivity, list, R.layout.friendlayout) {
                                    override fun convert(holder: ViewHolder?, item: Friendtrun.DataBean.ListBean?) {
                                        holder!!.setText(R.id.friend_name, item!!.username)
                                        val aa = item.phone
                                        val bb = aa!!.substring(0, 3) + "****" + aa.substring(7, 11)
                                        holder!!.setText(R.id.friend_phone, bb)
                                        if (item.parentNode.toString().equals("0")) {
                                            holder!!.setText(R.id.friend_card, "未激活")
                                        } else {
                                            holder!!.setText(R.id.friend_card, "已激活")
                                        }
                                    }
                                })*/

                        } else {
                            Toast.makeText(this@CommunityFriendActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                    }

                }

                override fun onFailure(call: Call<Friendtrun>, t: Throwable) {
                    num = 1
                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(this@CommunityFriendActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this@CommunityFriendActivity)
        }
    }

    fun default(id: String) {
        try {
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().getUserlogin()
            val getNodeMessage = retrofit.create(GitHubService::class.java!!).antIntroduce(sp.getString("user_id", ""), sp.getString("user_token", ""), id, "", "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            getNodeMessage.enqueue(object : Callback<Common> {
                override fun onResponse(call: Call<Common>, response: Response<Common>) {
                    if (response.body()!!.code == 1) {
                        val intent = Intent(this@CommunityFriendActivity, AllocationActivity::class.java)
                        intent.putExtra("allocation", response.body()!!.data)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@CommunityFriendActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Common>, t: Throwable) {
                    if (t is DataResultException) {
                        val resultException = t
                        Toast.makeText(this@CommunityFriendActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this)
        }
    }
}
