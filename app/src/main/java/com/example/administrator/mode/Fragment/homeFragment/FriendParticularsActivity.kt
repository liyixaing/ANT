package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
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
        if (PreferencesUtil.get("language", "") == "zh") {
            xxaa = "推荐人:"
            aaxx = "接点人:"
        } else if (PreferencesUtil.get("language", "") == "en") {
            xxaa = "Referrer:"
            aaxx = "Contact person:"
        }else{
            xxaa = "推荐人:"
            aaxx = "接点人:"
        }
        val uNode = intent.extras.getString("uNode")
        val uIdInput = intent.extras.getString("uIdInput")
        val uPhone = intent.extras.getString("uPhone")
        val nowtime = DateUtils.getdata()
        val uName = intent.extras.getString("uName")
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val retrofit = Retrofit_manager.getInstance().userlogin
        val getNodeMessage = retrofit.create(MoneyService::class.java).getNodeMessage(sp.getString("user_id", ""), sp.getString("user_token", ""), uNode, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
        getNodeMessage.enqueue(object : Callback<GetNodeMessage> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<GetNodeMessage>, response: Response<GetNodeMessage>) {
                if (response.body()!!.code == 1) {
                    friendName.text = getString(R.string.nameOf)+uName
                    friendUserId.text = "ID:$uIdInput"
                    friendUserPhone.text = getString(R.string.phoneOf)+uPhone
                    friendPerson.text = xxaa + response.body()!!.data.introduce
                    friendPhone.text = response.body()!!.data.introducePhone
                    contactPerson.text = aaxx + response.body()!!.data.insertPointId
                    contactPhone.text = response.body()!!.data.phone
                    if (response.body()!!.data.avatar != null) {
                        Glide.with(this@FriendParticularsActivity).load(response.body()!!.data.avatar).apply(bitmapTransform( GlideCircleTransform(this@FriendParticularsActivity))).into(friendHade)
                    }
                } else {
                    Toast.makeText(this@FriendParticularsActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetNodeMessage>, t: Throwable) {
                if (t is DataResultException) {
                    Toast.makeText(this@FriendParticularsActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
