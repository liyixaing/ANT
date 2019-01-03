package com.example.administrator.mode.Activity.drawer

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Activity.LoginActivity
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_security_login_up.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecurityLoginUpActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_security_login_up
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        loginup_submit.setOnClickListener(object :ClickUtlis(){
            override fun onMultiClick(v: View?) {
                if ("" == loginup_oldpwd.text.toString().trim()) {
                  Toast.makeText(this@SecurityLoginUpActivity, R.string.Loginup_oldpwdinput, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == loginup_newpwd.text.toString().trim()) {
                   Toast.makeText(this@SecurityLoginUpActivity, R.string.Loginup_newspwdinput, Toast.LENGTH_SHORT).show()
                    return
                }
                if (loginup_newpwd.text.toString().trim().length<6){
                    Toast.makeText(this@SecurityLoginUpActivity, R.string.Loginup_newspwderror, Toast.LENGTH_SHORT).show()
                    return
                }
                if (loginup_newpwd.text.toString().trim().length>16){
                    Toast.makeText(this@SecurityLoginUpActivity, R.string.Loginup_newspwderror, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == loginup_affirmnewpwd.text.toString().trim()) {
                   Toast.makeText(this@SecurityLoginUpActivity, R.string.Loginup_affirmnewpwdinput, Toast.LENGTH_SHORT).show()
                    return
                }

                if (loginup_affirmnewpwd.text.toString().trim()!=loginup_newpwd.text.toString().trim()) {
                   Toast.makeText(this@SecurityLoginUpActivity, R.string.Phoneup_paypwderor, Toast.LENGTH_SHORT).show()
                    return
                }

                try {
                    val nowtime= DateUtils.getdata()
                    val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                    val retrofit = Retrofit_manager.getInstance().getUserlogin()
                    val register = retrofit.create(GitHubService::class.java!!).loginpwdup(sp.getString("user_id", ""), loginup_oldpwd.text.toString().trim(), loginup_newpwd.text.toString().trim(), sp.getString("user_token", ""),"0",nowtime, PreferencesUtil.get("language", ""),SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "")+nowtime))
                    register.enqueue(object : Callback<Common> {
                        override fun onResponse(call: Call<Common>, response: Response<Common>) {
                            try {
                                if (response.body()!!.code == 1) {
                                    Toast.makeText(this@SecurityLoginUpActivity,R.string.Property_suess,Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@SecurityLoginUpActivity, LoginActivity::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(this@SecurityLoginUpActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                abnormal(this@SecurityLoginUpActivity)
                            }

                        }

                        override fun onFailure(call: Call<Common>, t: Throwable) {
                            val resultException = t as DataResultException
                            Toast.makeText(this@SecurityLoginUpActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    })
                } catch (e: Exception) {
                    abnormal(this@SecurityLoginUpActivity)
                }
            }

        })
        tit_name.setText(R.string.Loginup_tit)
    }
}
