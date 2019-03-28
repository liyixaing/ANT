package com.example.administrator.mode.Fragment.red_packet

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Friendtrun
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_red_friend.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RedFriendActivity : BaseActivity() {
    private var baseadaptr: FenAdater<*>? = null
    var list = arrayListOf<Friendtrun.DataBean.ListBean>()
    var listInput = arrayListOf<Friendtrun.DataBean.ListBean>()

    var num = 1
    private var recLen = 2
    var timer = Timer()
    override fun getContentViewId(): Int {
        return R.layout.activity_red_friend
    }

    override fun init() {
        super.init()
        titRedFriend.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        loadfriend()
        redFridnd.setONLoadMoreListener {
            val task = object : TimerTask() {
                @SuppressLint("ResourceType")
                override fun run() {
                    runOnUiThread {
                        if (recLen == 0) {
                            num++
                            loadfriend()
                            redFridnd.setLoadCompleted()
                        }
                        recLen--
                    }
                }
            }
            timer.schedule(task, 1000, 1000)
            recLen = 2
        }
        redFridnd.onItemClickListener = AdapterView.OnItemClickListener { p0, p1, p2, p3 ->
            val intent = Intent(this, OrdinaryRedEnvelopesActivity::class.java)
            intent.putExtra("userFirendPhone", listInput[p2].phone.toString())
            startActivity(intent)
            finish()
        }
    }

    fun loadfriend() {
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        var token = sp.getString("user_token", "")
        var id = sp.getString("user_id", "")
        try {
            val nowtime = DateUtils.getdata()
            val login = retrofit.create(MoneyService::class.java).getfriend(id, token, num.toString(), "10", "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<Friendtrun> {
                override fun onResponse(call: Call<Friendtrun>, response: Response<Friendtrun>) {
                    try {
                        if (response.body()!!.code == 1) {
                            val fe: List<Friendtrun.DataBean.ListBean>? = response.body()!!.data!!.list
                            list = fe as ArrayList<Friendtrun.DataBean.ListBean>
                            listInput.addAll(list)
                            baseadaptr = object : FenAdater<Friendtrun.DataBean.ListBean>(this@RedFriendActivity, listInput, R.layout.redfriendlayout) {
                                override fun convert(holder: ViewHolder?, item: Friendtrun.DataBean.ListBean?) {
                                    holder!!.setText(R.id.redBagFriendID, "ID:" + item!!.id.toString())
                                    holder.setText(R.id.redBagFriendPhone, item.phone)
                                }
                            }
                            redFridnd.adapter = baseadaptr
                        } else {
                            Toast.makeText(this@RedFriendActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                    }

                }

                override fun onFailure(call: Call<Friendtrun>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@RedFriendActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
        }
    }
}
