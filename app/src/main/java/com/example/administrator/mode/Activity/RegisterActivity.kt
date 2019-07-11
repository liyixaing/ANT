package com.example.administrator.mode.Activity

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer

import android.view.View
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.drawer.SecurityPhoneUpActivity
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.app.MyApplication
import com.example.administrator.mode.creatorprivatekey.ReadAgreementActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    override fun getContentViewId(): Int {
        return R.layout.activity_register
    }

    override fun onStart() {
        super.onStart()
        StatService.onPageStart(this@RegisterActivity, "LoginModule.RegisterView")
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        if (sp.getString("language", "zh").equals("zh")) {
            register_state.text = sp.getString("user_Nation_CN", "中国") + " +"
        } else {
            register_state.text = sp.getString("user_Nation_EN", "China") + " +"
        }
        login_state1.text = sp.getString("user_crown", "86")
    }

    override fun init() {
        super.init()
        qqzz.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@RegisterActivity, TermsOfServiceActivity::class.java))
            }
        })
        registerlogin.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }
        })
        register_state.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@RegisterActivity, CountryActivity::class.java))
            }
        })
        registerfindpwd.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@RegisterActivity, SecurityPhoneUpActivity::class.java))
                StatService.onPageEnd(this@RegisterActivity, "LoginModule.RegisterView")
            }
        })
        reinput.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (re_yaoq.text.toString().trim() == "") {
                    Toast.makeText(this@RegisterActivity, R.string.Login_invite1, Toast.LENGTH_SHORT).show()
                    return
                }
                xxx()
            }
        })
        Regnext.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (user_Regname.text.toString().trim() == "") {
                    Toast.makeText(this@RegisterActivity, R.string.Phoneup_phone, Toast.LENGTH_SHORT).show()
                    return
                }
                if (user_Regssm.text.toString().trim() == "") {
                    Toast.makeText(this@RegisterActivity, R.string.Register_verificationimport, Toast.LENGTH_SHORT).show()
                    return
                }
                if (Reg_type.isChecked) {
                    val nowtime = DateUtils.getdata()
                    val retrofit = Retrofit_manager.getInstance().userlogin
                    val register = retrofit.create(GitHubService::class.java).register(user_Regname.text.toString().trim(), re_yaoq.text.toString().trim(), login_state1.text.toString().trim(), user_Regssm.text.toString().trim(), VerifyUtlis.getIMEI(this@RegisterActivity), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(nowtime))
                    register.enqueue(object : Callback<Common> {
                        override fun onResponse(call: Call<Common>, response: Response<Common>) {
                            if (response.body()!!.code == 1) {
                                val intent = Intent(this@RegisterActivity, ReadAgreementActivity::class.java)
                                MyApplication.userPhone = user_Regname.text.toString().trim()
                                MyApplication.userWc = login_state1.text.toString().trim()
                                startActivity(intent)
                                StatService.onPageEnd(this@RegisterActivity, "LoginModule.RegisterView")
                            } else {
                                Toast.makeText(this@RegisterActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<Common>, t: Throwable) {
                            val resultException = t as DataResultException
                            Toast.makeText(this@RegisterActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    })
                } else {
                    Toast.makeText(this@RegisterActivity, R.string.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
        Reg_ssm.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                try {
                    val ssmUtlis = SsmUtlis()
                    ssmUtlis.Ssm(user_Regname.text.toString().trim(), login_state1.text.toString().trim(), VerifyUtlis.getIMEI(this@RegisterActivity), "0", this@RegisterActivity)
                    count_down()
                } catch (e: Exception) {
                    abnormal(this@RegisterActivity)
                }
            }
        })
    }

    fun xxx() {
        reinput.visibility = View.GONE
        ll.visibility = View.GONE
        ll1.visibility = View.VISIBLE
    }

    private fun count_down() {
        val countDownTimer = object : CountDownTimer((60 * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Reg_ssm.text = (millisUntilFinished / 1000).toString() + ""
                Reg_ssm.isClickable = false
            }

            override fun onFinish() {
                Reg_ssm.isClickable = true
                Reg_ssm.setText(R.string.Register_getverification)
            }
        }
        countDownTimer.start()
    }
}
