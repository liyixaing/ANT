package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log

import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Activity.MainActivity
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Transferturn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.creatorprivatekey.MessageSignUtils
import kotlinx.android.synthetic.main.activity_particulars.*
import kotlinx.android.synthetic.main.tit.*
import org.web3j.crypto.Credentials
import org.web3j.crypto.ECKeyPair
import org.web3j.utils.Numeric
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ParticularsActivity : BaseActivity() {
    var timer = Timer()
    private var recLen = 4
    var type = ""
    var aaaaazz = ""
    override fun getContentViewId(): Int {
        return R.layout.activity_particulars
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PreferencesUtil.get("language", "") == "zh") {
            aaaaazz = "次日24小时"

        } else if (PreferencesUtil.get("language", "") == "en") {
            aaaaazz = "24 hours before next day"
        } else {
            aaaaazz = "次日24小时前"
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        val rotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        val lin = LinearInterpolator()
        rotate.interpolator = lin
        rotate.duration = 4000//设置动画持续周期
        rotate.repeatCount = 0//设置重复次数
        rotate.fillAfter = true//动画执行完后是否停留在执行完的状态
        particulars_type.animation = rotate
        val task = object : TimerTask() {
            @SuppressLint("ResourceType")
            override fun run() {
                runOnUiThread {
                    if (recLen == 4) {
                        iv_message.visibility = View.VISIBLE
                        tv_message.visibility = View.VISIBLE
                        tv_message1.visibility = View.VISIBLE
                    }
                    if (recLen == 3) {
                        iv_encryption.visibility = View.VISIBLE
                        tv_encryption.visibility = View.VISIBLE
                        tv_encryption1.visibility = View.VISIBLE
                    }
                    if (recLen == 2) {
                        iv_verification.visibility = View.VISIBLE
                        tv_verification.visibility = View.VISIBLE
                        tv_verification1.visibility = View.VISIBLE
                    }
                    if (recLen == 1) {
                        iv_verification.visibility = View.VISIBLE
                        tv_verification.visibility = View.VISIBLE
                        tv_verification1.visibility = View.VISIBLE
                    }
                    if (recLen == 0) {
                        try {
                            val nowtime = DateUtils.getdata()
                            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                            val retrofit = Retrofit_manager.getInstance().userlogin
                            val keyPair = ECKeyPair.create(Numeric.hexStringToByteArray(SharedPreferencesUtil.getData("userPrivatelyKey", "").toString()))
                            val transfer = retrofit.create(MoneyService::class.java).transfertwo(sp.getString("user_id", ""), intent.extras.getString("transferid"), sp.getString("user_token", ""),"", "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), MessageSignUtils.Sign(Credentials.create(keyPair), Numeric.toHexString(VerifyUtlis.toHash(nowtime + sp.getString("user_token", ""))).substring(2, Numeric.toHexString(VerifyUtlis.toHash(nowtime + sp.getString("user_token", ""))).length)))
                            transfer.enqueue(object : Callback<Transferturn> {
                                override fun onResponse(call: Call<Transferturn>, response: Response<Transferturn>) {
                                    try {
                                        if (response.body()!!.code == 1) {
                                            type = "完成"

                                        } else {
                                            type = "未完成"
                                            Toast.makeText(this@ParticularsActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                        }
                                    } catch (e: Exception) {
                                        abnormal(this@ParticularsActivity)
                                    } finally {
                                        but_particulars.visibility = View.VISIBLE
                                        iv_sen.visibility = View.VISIBLE
                                        tv_sen.visibility = View.VISIBLE
                                        tv_sen1.visibility = View.VISIBLE
                                        if (type == "完成") {
                                            tv_type.setText(R.string.Particulars_ok)
                                            particulars_type.setImageResource(R.mipmap.d)
                                            iv_anticipation.visibility = View.VISIBLE
                                            tv_anticipation.text.toString()
                                            tv_anticipation.text = tv_anticipation.text.toString() + aaaaazz
                                            tv_anticipation.visibility = View.VISIBLE
                                        }
                                        if (type == "未完成") {
                                            tv_type.setTextColor(Color.RED)
                                            tv_type.setText(R.string.Particulars_no)
                                            particulars_type.setImageResource(R.mipmap.rit)
                                        }
                                    }

                                }

                                override fun onFailure(call: Call<Transferturn>, t: Throwable) {
                                    val resultException = t as DataResultException
                                    Toast.makeText(this@ParticularsActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()

                                    type = "未完成"
                                    tv_sen1.setTextColor(android.graphics.Color.RED)
                                    tv_sen1.text = "未完成"
                                    tv_type.setText(R.string.Particulars_no)
                                    particulars_type.setImageResource(R.mipmap.rit)
                                }
                            })

                        } catch (e: Exception) {
                            abnormal(this@ParticularsActivity)
                        }
                    }

                    recLen--
                }
            }
        }
        timer.schedule(task, 1000, 1000)
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                recLen = -1
                finish()
            }
        })

        tit_name.setText(R.string.Particulars_jieguo)
        but_particulars.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@ParticularsActivity, MainActivity::class.java))
                recLen = -1
                finish()
            }
        })
    }

    //判断用户是否失误点击了“返回键”
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            recLen = -1
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
