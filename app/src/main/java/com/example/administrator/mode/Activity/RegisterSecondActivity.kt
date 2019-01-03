package com.example.administrator.mode.Activity

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_register_second.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterSecondActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_register_second
    }

    override fun init() {
        super.init()
        registerselogin.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@RegisterSecondActivity, LoginActivity::class.java))
                finish()
            }
        })
        findpwd.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@RegisterSecondActivity, FindpwdActivity::class.java))
                finish()
            }
        })
        Regnex.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (user_Regpwd.text.toString().trim() != user_Regpwds.text.toString().trim()) {
                    Toast.makeText(this@RegisterSecondActivity, R.string.Register_userPwderror, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == user_Regpwd.text.toString().trim()) {
                    Toast.makeText(this@RegisterSecondActivity, R.string.Phoneup_loginpwd, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == user_Regpwds.text.toString().trim()) {
                    Toast.makeText(this@RegisterSecondActivity, R.string.Phoneup_loginpwds, Toast.LENGTH_SHORT).show()
                    return
                }
                if (user_Regpwd.text.toString().trim().length > 16) {
                    Toast.makeText(this@RegisterSecondActivity, R.string.Loginup_newspwderror, Toast.LENGTH_SHORT).show()
                    return
                }
                if (user_Regpwd.text.toString().trim().length < 6) {
                    Toast.makeText(this@RegisterSecondActivity, R.string.Loginup_newspwderror, Toast.LENGTH_SHORT).show()
                    return
                }
                try {
                    val nowtime = DateUtils.getdata()
                    val phoneinput = intent.extras.getString("userphone")
                    val wcinout = intent.extras.getString("userwc")
                    val retrofit = Retrofit_manager.getInstance().getUserlogin()
                    val register = retrofit.create(GitHubService::class.java!!).registertwo(phoneinput, user_Regpwds.text.toString().trim(), wcinout, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(nowtime))
                    register.enqueue(object : Callback<Common> {
                        override fun onResponse(call: Call<Common>, response: Response<Common>) {
                            try {
                                if (response.body()!!.code == 1) {
                                    val intent = Intent(this@RegisterSecondActivity, RegisterThirdlyActivity::class.java)
                                    intent.putExtra("user_phone", phoneinput);
                                    intent.putExtra("user_wc", wcinout);
                                    startActivity(intent);
                                    finish()
                                } else {
                                    Toast.makeText(this@RegisterSecondActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                abnormal(this@RegisterSecondActivity)
                            }
                        }

                        override fun onFailure(call: Call<Common>, t: Throwable) {
                            val resultException = t as DataResultException
                            Toast.makeText(this@RegisterSecondActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    })
                } catch (e: Exception) {
                    abnormal(this@RegisterSecondActivity)
                }
            }
        })
    }
}
