package com.example.administrator.mode.Fragment.homeFragment

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Earningtrun
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_my_community.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyCommunityActivity : BaseActivity() {
    val num = 1
    val siz = 10
    var list = arrayListOf<Earningtrun.DataBean>()
    override fun getContentViewId(): Int {
        return R.layout.activity_my_community
    }

    override fun onStart() {
        super.onStart()

        loadcommun()

    }

    fun loadcommun() {
        val retrofit = Retrofit_manager.getInstance().getUserlogin()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        var token = sp.getString("user_token", "")
        var id = sp.getString("user_id", "")
        val nowtime=DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java!!).getearning(id, token, num.toString(), siz.toString(), "0",nowtime,PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "")+nowtime))
            login.enqueue(object : Callback<Earningtrun> {
                override fun onResponse(call: Call<Earningtrun>, response: Response<Earningtrun>) {
                    try {
                        if (response.body()!!.code == 1) {
                            list = response.body()!!.data as ArrayList<Earningtrun.DataBean>
                            lv_friend.setAdapter(object : FenAdater<Earningtrun.DataBean>(this@MyCommunityActivity, list, R.layout.communitylayout) {
                                override fun convert(holder: ViewHolder?, item: Earningtrun.DataBean?) {
                                    holder!!.setText(R.id.community_date, DateUtils.timeslash(item!!.changeTime))
                                    holder!!.setText(R.id.community_card, "+" + String.format("%.8f", item!!.nodeEarnings!!.toDouble()))
                                }
                            })
                        } else {

                            Toast.makeText(this@MyCommunityActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        abnormal(this@MyCommunityActivity)
                    }

                }

                override fun onFailure(call: Call<Earningtrun>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@MyCommunityActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })

        } catch (e: Exception) {
            abnormal(this@MyCommunityActivity)
        }
    }

    override fun init() {
        super.init()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        myinvite.setText(sp.getString("user_id", ""))
        /*       val aa = "ID:8875567"
               val base = byteArrayOf(aa.toByte())
               val xxx = Base32Core.encode(base)
               Log.i("whz", aa.toByte().toString())
               Log.i("whz", xxx)
               val zzz = Base32Core.decode(xxx)
               for (i in zzz) {
                   Log.i("whz", i.toString())
               }*/
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })

        tit_name.setText(R.string.Mycomm_tit)
        lookfriend.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@MyCommunityActivity, CommunityFriendActivity::class.java))
            }
        })
        t1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                VerifyUtlis.copy(this@MyCommunityActivity, sp.getString("user_id", ""))
            }
        })
    }
}
