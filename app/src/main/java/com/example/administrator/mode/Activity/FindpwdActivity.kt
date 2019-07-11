package com.example.administrator.mode.Activity

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer

import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import com.example.administrator.mode.Utlis.SsmUtlis
import com.example.administrator.mode.Utlis.VerifyUtlis
import kotlinx.android.synthetic.main.activity_findpwd.*

class FindpwdActivity : BaseActivity() {

    override fun getContentViewId(): Int {
        return R.layout.activity_findpwd
    }

    override fun onStart() {
        super.onStart()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        pwd_state1.setText(sp.getString("user_crown", "86"))
        if (sp.getString("language", "zh").equals("zh")) {
            pwd_state.setText(sp.getString("user_Nation_CN", "中国") + " +")
        } else {
            pwd_state.setText(sp.getString("user_Nation_EN", "China") + " +")
        }
    }

    override fun init() {
        super.init()
        StatService.onPageEnd(this@FindpwdActivity,"LoginModule.ForgetView")
        pwd_state.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@FindpwdActivity, CountryActivity::class.java))
            }
        })

        findlogin.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@FindpwdActivity, LoginActivity::class.java))
                StatService.onPageEnd(this@FindpwdActivity,"LoginModule.ForgetView")
                finish()
            }
        })
        findregister.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@FindpwdActivity, RegisterActivity::class.java))
                StatService.onPageEnd(this@FindpwdActivity,"LoginModule.ForgetView")

                finish()
            }
        })
        user_finGetssm.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (user_Finname.text.toString().trim().length > 0) {

                    var ssmUtlis = SsmUtlis()
                    ssmUtlis.Ssm(user_Finname.text.toString().trim(), pwd_state1.text.toString().trim(), VerifyUtlis.getIMEI(this@FindpwdActivity), "2", this@FindpwdActivity)
                    count_down()
                } else {
                    Toast.makeText(this@FindpwdActivity, R.string.Register_userPhoneerror, Toast.LENGTH_SHORT).show()
                }
            }
        })

        find_next.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if ("" == user_Finname.text.toString().trim()) {
                    Toast.makeText(this@FindpwdActivity, R.string.Phoneup_phone, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == user_Finssm.text.toString().trim()) {
                    Toast.makeText(this@FindpwdActivity, R.string.Register_verificationimport, Toast.LENGTH_SHORT).show()
                    return
                }
                val intent = Intent(this@FindpwdActivity, FindpwdSecondActivity::class.java)
                intent.putExtra("user_Finname", user_Finname.text.toString().trim())
                intent.putExtra("user_Finssm", user_Finssm.text.toString().trim());
                startActivity(intent)
                StatService.onPageEnd(this@FindpwdActivity,"LoginModule.ForgetView")
                finish()
            }
        })

    }

    private fun count_down() {
        val countDownTimer = object : CountDownTimer((60 * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                user_finGetssm.setText((millisUntilFinished / 1000).toString() + "")
                user_finGetssm.setClickable(false)

            }

            override fun onFinish() {
                user_finGetssm.setClickable(true)
                user_finGetssm.setText(R.string.Register_getverification)
            }
        }
        countDownTimer.start()
    }
}
