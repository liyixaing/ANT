package com.example.administrator.mode.Activity.drawer

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.CountryActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Activity.LoginActivity
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_security_phoneup.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecurityPhoneUpActivity : BaseActivity() {
    var xx = ""
    override fun getContentViewId(): Int {
        return R.layout.activity_security_phoneup
    }

    override fun onStart() {
        super.onStart()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)

        aaguanhao.setText(sp.getString("user_crown", "86"))
        xx = sp.getString("oldwcn","86")
    }

    private fun count_down() {
        val countDownTimer = object : CountDownTimer((60 * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                FindPayget_ssm.setText((millisUntilFinished / 1000).toString() + "")
                FindPayget_ssm.setClickable(false)
            }

            override fun onFinish() {
                FindPayget_ssm.setClickable(true)
                FindPayget_ssm.setText(R.string.Register_getverification)
            }
        }
        countDownTimer.start()
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })

        aaguanhao.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@SecurityPhoneUpActivity, CountryActivity::class.java))
            }
        })
        xxxxx.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@SecurityPhoneUpActivity, CountryActivity::class.java))
            }
        })
        FindPayget_ssm.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                try {
                    val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                    var ssmUtlis = SsmUtlis()
                    ssmUtlis.Ssm(Phoneup_new.text.toString().trim(),aaguanhao.text.toString().trim(), VerifyUtlis.getIMEI(this@SecurityPhoneUpActivity), "3", this@SecurityPhoneUpActivity)
                    count_down()
                } catch (e: Exception) {
                    abnormal(this@SecurityPhoneUpActivity)
                }
            }
        })
        tit_name.setText(R.string.Phoneup_tit)
        loginup_submit.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if ("" == Phoneup_old.text.toString().trim()) {
                    Toast.makeText(this@SecurityPhoneUpActivity, R.string.Phoneup_phone, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == Phoneup_new.text.toString().trim()) {
                    Toast.makeText(this@SecurityPhoneUpActivity, R.string.Phoneup_phone, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == Phoneup_loginpwd.text.toString().trim()) {
                    Toast.makeText(this@SecurityPhoneUpActivity, R.string.Phoneup_loginpwd, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == Phoneup_paypwd.text.toString().trim()) {
                    Toast.makeText(this@SecurityPhoneUpActivity, R.string.Phoneup_paypwd, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == FindPay_ssmiput.text.toString().trim()) {
                    Toast.makeText(this@SecurityPhoneUpActivity, R.string.Register_verificationimport, Toast.LENGTH_SHORT).show()
                    return
                }
                val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                try {
                    Log.i("whz",xx+aaguanhao.text.toString().trim())
                    val nowtime = DateUtils.getdata()
                    val retrofit = Retrofit_manager.getInstance().getUserlogin()
                    val login = retrofit.create(GitHubService::class.java!!).phoneup(sp.getString("user_id", ""), Phoneup_old.text.toString().trim(),xx, Phoneup_new.text.toString().trim(), aaguanhao.text.toString().trim(), FindPay_ssmiput.text.toString().trim(), sp.getString("user_token", ""), Phoneup_paypwd.text.toString().trim(), Phoneup_loginpwd.text.toString().trim(), "0", nowtime, PreferencesUtil.get("language", ""),SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
                    login.enqueue(object : Callback<Common> {
                        override fun onResponse(call: Call<Common>, response: Response<Common>) {
                            try {
                                if (response.body()!!.code == 1) {
                                    Toast.makeText(this@SecurityPhoneUpActivity, R.string.Property_suess, Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@SecurityPhoneUpActivity, LoginActivity::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(this@SecurityPhoneUpActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                abnormal(this@SecurityPhoneUpActivity)
                            }
                        }

                        override fun onFailure(call: Call<Common>, t: Throwable) {
                            val resultException = t as DataResultException
                            Toast.makeText(this@SecurityPhoneUpActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    })
                } catch (e: Exception) {
                    abnormal(this@SecurityPhoneUpActivity)
                }

            }
        })
    }
}
