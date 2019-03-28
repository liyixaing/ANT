package com.example.administrator.mode.Activity

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.creatorprivatekey.ReadAgreementActivity
import kotlinx.android.synthetic.main.activity_register_thirdly.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterThirdlyActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_register_thirdly
    }

    override fun init() {
        super.init()
        registerthreelogin.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@RegisterThirdlyActivity, LoginActivity::class.java))
                finish()
            }
        })

        registerthreefindpwd.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@RegisterThirdlyActivity, FindpwdActivity::class.java))
                finish()
            }
        })

        Reg_ok.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if ("" == user_RegpayPwd.text.toString().trim()) {
                    Toast.makeText(this@RegisterThirdlyActivity, R.string.Phoneup_paypwd, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == user_RegpayPwds.text.toString().trim()) {
                    Toast.makeText(this@RegisterThirdlyActivity, R.string.Phoneup_paypwds, Toast.LENGTH_SHORT).show()
                    return
                }
                if (user_RegpayPwd.text.toString().trim() != user_RegpayPwds.text.toString().trim()) {
                    Toast.makeText(this@RegisterThirdlyActivity, R.string.Phoneup_paypwderor, Toast.LENGTH_SHORT).show()
                    return
                }
                if (6 != user_RegpayPwd.text.toString().trim().length) {
                    Toast.makeText(this@RegisterThirdlyActivity, R.string.Payup_newspwderroe, Toast.LENGTH_SHORT).show()
                    return
                }
                try {
                    val nowtime = DateUtils.getdata()
                    val phoneinput = intent.extras.getString("user_phone")
                    val wcinout = intent.extras.getString("user_wc")
                    val retrofit = Retrofit_manager.getInstance().userlogin
                    val register = retrofit.create(GitHubService::class.java).registerthree(phoneinput, user_RegpayPwds.text.toString().trim(), wcinout, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(nowtime))
                    register.enqueue(object : Callback<Common> {
                        override fun onResponse(call: Call<Common>, response: Response<Common>) {
                            try {
                                if (response.body()!!.code == 1) {
                                    startActivity(Intent(this@RegisterThirdlyActivity, ReadAgreementActivity::class.java))
                                    StatService.onEvent(this@RegisterThirdlyActivity, "RegisterView.RegisterSuccess", "[请填写事件标签名]", 1);
                                    finish()
                                } else {
                                    toast(response.body()!!.message)
                                }
                            } catch (e: Exception) {
                                abnormal(this@RegisterThirdlyActivity)
                            }
                        }

                        override fun onFailure(call: Call<Common>, t: Throwable) {
                            if (t is DataResultException) {
                                val attributes = HashMap<String, String>()
                                attributes["UserName"] = phoneinput
                                StatService.onEvent(this@RegisterThirdlyActivity, "RegisterView.SendVerifyCode", "[请填写事件标签名]", 1, attributes)
                                Toast.makeText(this@RegisterThirdlyActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                } catch (e: Exception) {
                    abnormal(this@RegisterThirdlyActivity)
                }
            }
        })
    }
}
