package com.example.administrator.mode.Activity

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_findpwd_second.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindpwdSecondActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_findpwd_second
    }

    override fun init() {
        super.init()
        findlogin.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@FindpwdSecondActivity, LoginActivity::class.java))
                finish()
            }
        })
        register.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@FindpwdSecondActivity, RegisterActivity::class.java))
                finish()
            }
        })
        Fin_ok.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val user_Finnameinput = intent.extras.getString("user_Finname")
                val user_Finssminput = intent.extras.getString("user_Finssm")
                val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                if ("" == user_newpwd.text.toString().trim()) {
                    Toast.makeText(this@FindpwdSecondActivity, R.string.Loginup_newspwdinput, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == user_newpwds.text.toString().trim()) {
                    Toast.makeText(this@FindpwdSecondActivity, R.string.Loginup_affirmnewpwdinput, Toast.LENGTH_SHORT).show()
                    return
                }
                if (user_newpwd.text.toString().trim() != user_newpwds.text.toString().trim()) {
                    Toast.makeText(this@FindpwdSecondActivity, R.string.Phoneup_paypwderor, Toast.LENGTH_SHORT).show()
                    return
                }
                try {
                    val nowtime = DateUtils.getdata()
                    val retrofit = Retrofit_manager.getInstance().userlogin
                    val login = retrofit.create(GitHubService::class.java).findpwd(sp.getString("user_crown", ""), user_Finnameinput, user_Finssminput, user_newpwd.text.toString().trim(), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey( nowtime))
                    login.enqueue(object : Callback<Common> {
                        override fun onResponse(call: Call<Common>, response: Response<Common>) {
                            try {
                                if (response.body()!!.code == 1) {
                                    Toast.makeText(this@FindpwdSecondActivity, R.string.Property_suess, Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@FindpwdSecondActivity, LoginActivity::class.java))
                                    StatService.onEvent(this@FindpwdSecondActivity, "ForgetView.FindPasswordSuccess", "xms", 1)
                                    finish()
                                } else {
                                    Toast.makeText(this@FindpwdSecondActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                abnormal(this@FindpwdSecondActivity)
                            }
                        }
                        override fun onFailure(call: Call<Common>, t:Throwable) {
                            Log.i("whz", "gggg")
                            if (t is DataResultException) {
                                val attributes = HashMap<String, String>()
                                attributes.put("UserName", user_Finnameinput);
                                StatService.onEvent(this@FindpwdSecondActivity, "ForgetView.SendVerifyCode", "[请填写事件标签名]", 1, attributes);
                                val resultException = t
                                Toast.makeText(this@FindpwdSecondActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                } catch (e: Exception) {
                    abnormal(this@FindpwdSecondActivity)
                }
            }
        })
    }
}
