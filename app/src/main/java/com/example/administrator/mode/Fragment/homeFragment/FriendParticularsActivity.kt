package com.example.administrator.mode.Fragment.homeFragment

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.bumptech.glide.Glide
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.GetNodeMessage
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_friend_particulars.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendParticularsActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_friend_particulars
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            startActivity(Intent(this@FriendParticularsActivity, CommunityFriendActivity::class.java))
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun init() {
        super.init()
        StatService.onPageStart(this@FriendParticularsActivity, "MainModule.HomeView.ActivateFriendsView.Detail\n" +
                "View")
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@FriendParticularsActivity, CommunityFriendActivity::class.java))
                StatService.onPageEnd(this@FriendParticularsActivity, "MainModule.HomeView.ActivateFriendsView.Detail\n" +
                        "View")
                finish()
            }
        })
        tit_name.setText(R.string.Particulars_tit)
        confirm()
    }

    var xxaa = ""
    var aaxx = ""
    fun confirm() {
        if (PreferencesUtil.get("language", "").equals("zh")) {
            xxaa = "推荐人:"
            aaxx = "接点人:"
        } else if (PreferencesUtil.get("language", "").equals("en")) {
            xxaa = "Referrer:"
            aaxx = "Contact person:"
        }
        val uNode = intent.extras.getString("uNode")
        val uIdInput = intent.extras.getString("uIdInput")
        val uPhone = intent.extras.getString("uPhone")
        val nowtime = DateUtils.getdata()
        val uName = intent.extras.getString("uName")
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val retrofit = Retrofit_manager.getInstance().getUserlogin()
        val getNodeMessage = retrofit.create(MoneyService::class.java!!).getNodeMessage(sp.getString("user_id", ""), sp.getString("user_token", ""), uNode, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
        getNodeMessage.enqueue(object : Callback<GetNodeMessage> {
            override fun onResponse(call: Call<GetNodeMessage>, response: Response<GetNodeMessage>) {
                if (response.body()!!.code == 1) {
                    friendName.setText(uName)
                    friendUserId.setText("ID:" + uIdInput)
                    friendUserPhone.setText(uPhone)
                    friendPerson.setText(xxaa + response.body()!!.data.introduce)
                    friendPhone.setText(response.body()!!.data.introducePhone)
                    contactPerson.setText(aaxx + response.body()!!.data.insertPointId)
                    contactPhone.setText(response.body()!!.data.phone)
                    if (response.body()!!.data.avatar != null) {
                        Glide.with(this@FriendParticularsActivity).load(response.body()!!.data.avatar).centerCrop().transform(GlideCircleTransform(this@FriendParticularsActivity)).into(friendHade)
                    }
                } else {
                    Toast.makeText(this@FriendParticularsActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetNodeMessage>, t: Throwable) {
                if (t is DataResultException) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@FriendParticularsActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
