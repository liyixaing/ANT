package com.example.administrator.mode.Activity.drawer

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_security_find_paypwd.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecurityFindPaypwdActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_security_find_paypwd
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
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        Findpay_op.setText(sp.getString("user_phone", ""))
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
        FindPayget_ssm.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                try {
                    var ssmUtlis = SsmUtlis()
                    ssmUtlis.Ssm(sp.getString("user_phone", ""), sp.getString("oldwcn", ""), VerifyUtlis.getIMEI(this@SecurityFindPaypwdActivity), "1", this@SecurityFindPaypwdActivity)
                    count_down()
                } catch (e: Exception) {
                    abnormal(this@SecurityFindPaypwdActivity)
                }
            }

        })
        tit_name.setText(R.string.FindPay_tit)
        loginup_submit.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                if ("" == FindPay_newpwd.text.toString().trim()) {
                    Toast.makeText(this@SecurityFindPaypwdActivity, R.string.Loginup_newspwdinput, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == FindPay_affirmnewpwd.text.toString().trim()) {
                    Toast.makeText(this@SecurityFindPaypwdActivity, R.string.Loginup_affirmnewpwdinput, Toast.LENGTH_SHORT).show()
                    return
                }
                if (FindPay_affirmnewpwd.text.toString().trim() != FindPay_newpwd.text.toString().trim()) {
                    Toast.makeText(this@SecurityFindPaypwdActivity, R.string.UserfindPwd_pwdError, Toast.LENGTH_SHORT).show()
                    return
                }
                if (6 != FindPay_affirmnewpwd.text.toString().trim().length) {
                    Toast.makeText(this@SecurityFindPaypwdActivity, R.string.Payup_newspwderroe, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == FindPay_ssmiput.text.toString().trim()) {
                    Toast.makeText(this@SecurityFindPaypwdActivity, R.string.Register_verificationimport, Toast.LENGTH_SHORT).show()
                    return
                }
                try {
                    val nowtime = DateUtils.getdata()
                    val retrofit = Retrofit_manager.getInstance().getUserlogin()
                    Log.i("whz111", sp.getString("user_crown", ""))
                    val register = retrofit.create(MoneyService::class.java!!).findpaypwd(sp.getString("user_crown", ""), Findpay_op.text.toString().trim(), sp.getString("user_token", ""), FindPay_ssmiput.text.toString().trim(), FindPay_affirmnewpwd.text.toString().trim(), "0", nowtime,PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
                    register.enqueue(object : Callback<Common> {
                        override fun onResponse(call: Call<Common>, response: Response<Common>) {
                            try {
                                if (response.body()!!.code == 1) {
                                    val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                                    //获得一个SharedPreferences编辑器
                                    val edit = sp.edit()
                                    edit.putBoolean("first", true)
                                    edit.commit();
                                    Toast.makeText(this@SecurityFindPaypwdActivity, R.string.Property_suess, Toast.LENGTH_SHORT).show()
                                    finish()
                                } else {
                                    Toast.makeText(this@SecurityFindPaypwdActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                abnormal(this@SecurityFindPaypwdActivity)
                            }
                        }

                        override fun onFailure(call: Call<Common>, t: Throwable) {
                            val resultException = t as DataResultException
                            Toast.makeText(this@SecurityFindPaypwdActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    })
                } catch (e: Exception) {
                    abnormal(this@SecurityFindPaypwdActivity)
                }
            }
        })

    }
}
