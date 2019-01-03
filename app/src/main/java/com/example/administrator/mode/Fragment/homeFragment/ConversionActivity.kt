package com.example.administrator.mode.Fragment.homeFragment

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Activity.drawer.SecurityFindPaypwdActivity
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.Balanceturn
import com.example.administrator.mode.Pojo.prturn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_conversion.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConversionActivity : BaseActivity() {
    var num = ""
    var zi = ""
    var mix = ""
    var Available1 = ""
    override fun getContentViewId(): Int {
        return R.layout.activity_conversion
    }

    override fun onStart() {
        super.onStart()
        conload()
        StatService.onPageStart(this@ConversionActivity, "MainModule.HomeView.PropertyDetailView")
        loadpr()
    }

    fun loadpr() {
        if (PreferencesUtil.get("language", "").equals("zh")) {
            Available1 = "可用通证"

        } else if (PreferencesUtil.get("language", "").equals("en")) {
            Available1 = "Available through the"
        }


        val retrofit = Retrofit_manager.getInstance().getUserlogin()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        var token = sp.getString("user_token", "")
        var id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java!!).paroper(id, token, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<prturn> {
                override fun onResponse(call: Call<prturn>, response: Response<prturn>) {
                    try {
                        if (response.body()!!.code == 1) {
                            zi = String.format("%.8f", response.body()!!.data!!.user_ant!!.toDouble())
                            carinput.setText(String.format("%.8f", response.body()!!.data!!.user_ant!!.toDouble()))
                            con_integral.setText(String.format("%.8f", response.body()!!.data!!.user_score!!.toDouble()))
                            tvcard.setText(Available1 + String.format("%.8f", response.body()!!.data!!.user_ant!!.toDouble()))
                        } else {
                            Toast.makeText(this@ConversionActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                    }
                }

                override fun onFailure(call: Call<prturn>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@ConversionActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })

        } catch (e: Exception) {
        }

    }

    fun conload() {
        val retrofit = Retrofit_manager.getInstance().getUserlogin()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        var token = sp.getString("user_token", "")
        var id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java!!).getbalan(id, token, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<Balanceturn> {
                override fun onResponse(call: Call<Balanceturn>, response: Response<Balanceturn>) {
                    try {
                        if (response.body()!!.code == 1) {
                            num = response.body()!!.data!!.ant_convert_rate!!
                            mix = response.body()!!.data!!.ant_convert_min_value!!
                        } else {
                            Toast.makeText(this@ConversionActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                    }
                }

                override fun onFailure(call: Call<Balanceturn>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@ConversionActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
        }
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })

        tit_name.setText(R.string.AffirmDeal_conversion)
        conversion_ok.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {

                if (duihuanshu.text.toString().trim().length < 1) {
                    Toast.makeText(this@ConversionActivity, R.string.Conversion_shurushuliang, Toast.LENGTH_SHORT).show()
                    duihuanshu.setHint("0.00")
                    car_qian.setText("0.00")
                    conversion_rate.setText("0.00")
                    return
                }
                try {
                    if (duihuanshu.text.toString().trim().toInt() < mix.toInt()) {
                        Toast.makeText(this@ConversionActivity, "最少输入" + mix, Toast.LENGTH_SHORT).show()
                        duihuanshu.setHint("0.00")
                        car_qian.setText("0.00")
                        conversion_rate.setText("0.00")
                        return
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@ConversionActivity, "最少输入" + mix, Toast.LENGTH_SHORT).show()
                    duihuanshu.setHint("0.00")
                    car_qian.setText("0.00")
                    conversion_rate.setText("0.00")
                    return
                }


                val payDialog = PayDialog(this@ConversionActivity)
                payDialog.setConversionActivity(this@ConversionActivity)
                payDialog.show()
            }
        })

        duihuanshu.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(e: Editable?) {
                var dui = ""
                dui = duihuanshu.text.toString()
                try {
                    if (dui.toDouble() > zi.toDouble()) {
                        duihuanshu.setText(zi.substring(0, zi.length - 11) + "00")
                        duihuanshu.setSelection(duihuanshu.text.toString().trim().length);
                        return
                    }
                    carinput.setText(String.format("%.8f", (zi.toDouble() - dui.toDouble())))
                    conversion_rate.setText("+" + String.format("%.2f", (num.toDouble() * dui.trim().toDouble())))
                    con_integral.setText(String.format("%.8f", (zi.toDouble() + (num.toDouble() * dui.trim().toDouble()))))
                    car_qian.setText("-" + String.format("%.2f", dui.toDouble()))
                } catch (e: Exception) {
                    duihuanshu.setHint("0.00")
                    car_qian.setText("0.00")
                    conversion_rate.setText("0.00")
                }
            }
        })
    }

    fun tiaozhuan() {
        startActivity(Intent(this@ConversionActivity, SecurityFindPaypwdActivity::class.java))
    }

    fun OnInputPayPasswrodSuccess(inputPwd: String) {
        try {
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().getUserlogin()
            val transfer = retrofit.create(MoneyService::class.java!!).conversion(sp.getString("user_id", ""), duihuanshu.text.toString().trim(), sp.getString("user_token", ""), inputPwd, "", "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            transfer.enqueue(object : Callback<Common> {
                override fun onResponse(call: Call<Common>, response: Response<Common>) {
                    if (response.body()!!.code == 1) {
                        try {
                            val attributes = HashMap<String, String>()
                            attributes.put("UserName", sp.getString("user_id", ""))
                            attributes.put("Amount", duihuanshu.text.toString().trim())
                            StatService.onEvent(this@ConversionActivity, "ExchangeView.ExchangeAmount", "[请填写事件标签名]", 1, attributes)
                            StatService.onPageEnd(this@ConversionActivity, "MainModule.HomeView.PropertyDetailView")
                            Toast.makeText(this@ConversionActivity, R.string.Property_success, Toast.LENGTH_SHORT).show()
                            finish()
                        } catch (e: Exception) {
                            abnormal(this@ConversionActivity)
                        }

                    } else {
                        Toast.makeText(this@ConversionActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Common>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@ConversionActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
            abnormal(this@ConversionActivity)
        }

    }
}