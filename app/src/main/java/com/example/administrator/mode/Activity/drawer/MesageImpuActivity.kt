package com.example.administrator.mode.Activity.drawer

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.GetNoticeDetail
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_mesage_impu.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MesageImpuActivity : BaseActivity() {
    var sssss = ""
    override fun getContentViewId(): Int {
        return R.layout.activity_mesage_impu
    }

    override fun init() {
        super.init()
        if (PreferencesUtil.get("language", "") == "zh") {
            sssss = "发布时间"

        } else if (PreferencesUtil.get("language", "") == "en") {
            sssss = "release time"
        } else {
            sssss = "发布时间"
        }
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@MesageImpuActivity, MessageListActivity::class.java))
                finish()
            }
        })
        tit_name.setText(R.string.ttt)
        initData()
    }

    private fun initData() {
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val retrofit = Retrofit_manager.getInstance().userlogin
        val nowtime = DateUtils.getdata()
        val login = retrofit.create(MoneyService::class.java).getNoticeDetail(sp.getString("user_id", ""), sp.getString("user_token", ""), intent.extras.getString("jumpMessageImptaa"), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
        login.enqueue(object : Callback<GetNoticeDetail> {
            override fun onResponse(call: Call<GetNoticeDetail>, response: Response<GetNoticeDetail>) {
                try {
                    if (response.body()!!.code == 1) {
                        messageImplTit.text = response.body()!!.data.title
                        messageImplContent.text = VerifyUtlis.stringChang("\n" + response.body()!!.data.content + "\n")
                        Log.i("dawda",response.body()!!.data.content)
                    }
                } catch (e: Exception) {
                }
            }

            override fun onFailure(call: Call<GetNoticeDetail>, t: Throwable) {
                if (t is DataResultException) {
                    Toast.makeText(this@MesageImpuActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            startActivity(Intent(this@MesageImpuActivity, MessageListActivity::class.java))
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)


    }
}
