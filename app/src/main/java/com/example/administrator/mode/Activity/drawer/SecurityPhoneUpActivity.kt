package com.example.administrator.mode.Activity.drawer

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.CountryActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Activity.LoginActivity
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.app.MyApplication
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
        resetKey.isChecked = MyApplication.isaaa != ""
        aaguanhao.text = sp.getString("user_crown", "86")
        xx = sp.getString("oldwcn", "86")
    }

    private fun count_down() {
        val countDownTimer = object : CountDownTimer((60 * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                FindPayget_ssm.text = (millisUntilFinished / 1000).toString() + ""
                FindPayget_ssm.isClickable = false
            }

            override fun onFinish() {
                FindPayget_ssm.isClickable = true
                FindPayget_ssm.setText(R.string.Register_getverification)
            }
        }
        countDownTimer.start()
    }

    override fun init() {
        super.init()
        resetKey.setOnClickListener {
            if (resetKey.isChecked) {
                startActivity(Intent(this,KeyMessageActivity::class.java))
            } else {

            }
        }
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
                    ssmUtlis.Ssm(Phoneup_new.text.toString().trim(), aaguanhao.text.toString().trim(), VerifyUtlis.getIMEI(this@SecurityPhoneUpActivity), "2", this@SecurityPhoneUpActivity)
                    count_down()
                } catch (e: Exception) {
                    abnormal(this@SecurityPhoneUpActivity)
                }
            }
        })
        tit_name.setText(R.string.UserfindPwd_succeed1)
        loginup_submit.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                AlertView(getString(R.string.hint), getString(R.string.hintdd), null, arrayOf(getString(R.string.reception), getString(R.string.Welcome_error)), null, this@SecurityPhoneUpActivity, AlertView.Style.Alert, object : OnItemClickListener {
                    override fun onItemClick(o: Any?, position: Int) {
                        if (position == 0) {
                            var isReset = 0
                            if (resetKey.isChecked) {
                                isReset = 1
                            } else {
                                isReset = 0
                            }
                            if ("" == Phoneup_new.text.toString().trim()) {
                                Toast.makeText(this@SecurityPhoneUpActivity, R.string.Phoneup_phone, Toast.LENGTH_SHORT).show()
                                return
                            }
                            if ("" == Phoneup_loginpwd.text.toString().trim()) {
                                Toast.makeText(this@SecurityPhoneUpActivity, R.string.Phoneup_loginpwd, Toast.LENGTH_SHORT).show()
                                return
                            }
                            if (Phoneup_loginpwd.text.toString().trim() != Phoneup_paypwd.text.toString().trim()) {
                                Toast.makeText(this@SecurityPhoneUpActivity, R.string.Phoneup_paypwderor, Toast.LENGTH_SHORT).show()
                                return
                            }
                            if ("" == FindPay_ssmiput.text.toString().trim()) {
                                Toast.makeText(this@SecurityPhoneUpActivity, R.string.Register_verificationimport, Toast.LENGTH_SHORT).show()
                                return
                            }
                            try {
                                val nowtime = DateUtils.getdata()
                                val retrofit = Retrofit_manager.getInstance().userlogin
                                val login = retrofit.create(GitHubService::class.java).phoneup(Phoneup_new.text.toString().trim(),
                                        FindPay_ssmiput.text.toString().trim(),
                                        aaguanhao.text.toString().trim(),
                                        Phoneup_loginpwd.text.toString().trim(),
                                        isReset.toString(), "0", nowtime,
                                        PreferencesUtil.get("language", ""),
                                        SignatureUtil.signtureByPrivateKey(nowtime))
                                login.enqueue(object : Callback<Common> {
                                    override fun onResponse(call: Call<Common>, response: Response<Common>) {
                                        try {
                                            if (response.body()!!.code == 1) {
                                                Toast.makeText(this@SecurityPhoneUpActivity, R.string.Property_suess, Toast.LENGTH_SHORT).show()
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
                    }
                }).show()

            }
        })
    }
}
