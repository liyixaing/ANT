package com.example.administrator.mode.Activity.drawer

import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_security_pay_up.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecurityPayUpActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_security_pay_up
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })

        tit_name.setText(R.string.Payup_tit)

        payup_submit.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if ("" == payup_oldpwd.text.toString().trim()) {
                    Toast.makeText(this@SecurityPayUpActivity, R.string.Loginup_oldpwdinput, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == payup_newpwd.text.toString().trim()) {
                    Toast.makeText(this@SecurityPayUpActivity, R.string.Loginup_newspwdinput, Toast.LENGTH_SHORT).show()

                    return
                }
                if (6 != payup_newpwd.text.toString().trim().length) {
                    Toast.makeText(this@SecurityPayUpActivity, R.string.Payup_newspwderroe, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == payup_affirmnewpwd.text.toString().trim()) {
                    Toast.makeText(this@SecurityPayUpActivity, R.string.Loginup_affirmnewpwdinput, Toast.LENGTH_SHORT).show()
                    return
                }
                if (payup_affirmnewpwd.text.toString().trim() != payup_newpwd.text.toString().trim()) {
                    Toast.makeText(this@SecurityPayUpActivity, R.string.UserfindPwd_pwdError, Toast.LENGTH_SHORT).show()
                    return
                }
                val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                try {
                    val nowtime= DateUtils.getdata()
                    val retrofit = Retrofit_manager.getInstance().getUserlogin()
                    val register = retrofit.create(MoneyService::class.java!!).paypwdup(payup_oldpwd.text.toString().trim(), sp.getString("user_token", ""), payup_newpwd.text.toString().trim(), sp.getString("user_id", ""),  "0",nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "")+nowtime))
                    register.enqueue(object : Callback<Common> {
                        override fun onResponse(call: Call<Common>, response: Response<Common>) {
                            try {
                                if (response.body()!!.code == 1) {
                                    Toast.makeText(this@SecurityPayUpActivity, R.string.Property_suess, Toast.LENGTH_SHORT).show()

                                    finish()
                                } else {
                                    Toast.makeText(this@SecurityPayUpActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                abnormal(this@SecurityPayUpActivity)
                            }

                        }

                        override fun onFailure(call: Call<Common>, t: Throwable) {
                            if (t is DataResultException) {
                                val resultException = t as DataResultException
                                Toast.makeText(this@SecurityPayUpActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                } catch (e: Exception) {
                    abnormal(this@SecurityPayUpActivity)
                }

            }
        })
    }

}
