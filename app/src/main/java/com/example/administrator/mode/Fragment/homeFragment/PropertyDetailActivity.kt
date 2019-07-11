package com.example.administrator.mode.Fragment.homeFragment

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.PropertyStm
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_property_detail.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyDetailActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_property_detail
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            startActivity(Intent(this@PropertyDetailActivity, PropertyActivity::class.java))
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@PropertyDetailActivity, PropertyActivity::class.java))
                finish()
            }
        })
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val token = sp.getString("user_token", "")
        val id = sp.getString("user_id", "")
        tit_name.setText(R.string.Deal_tit)
        try {

            if (intent.extras.getString("propertytype") == "tong") {
                val nowtime = DateUtils.getdata()
                val login = retrofit.create(MoneyService::class.java).getAntBananceLogDetailStm(id, token, intent.extras.getString("order"), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
                login.enqueue(object : Callback<PropertyStm> {
                    override fun onResponse(call: Call<PropertyStm>, response: Response<PropertyStm>) {
                        try {
                            if (response.body()!!.code == 1) {
                                dealNumber.text = response.body()!!.data.orderId.toString()
                                dealType.text = response.body()!!.data.tradeTypeDesc

                                dealTime.text = DateUtils.timeslashData(response.body()!!.data.createTime.toString())
                                dealnameOut.text = response.body()!!.data.phone
                                dealName.text = response.body()!!.data.username
                                if (String.format("%.8f", response.body()!!.data.ant).indexOf("-") != -1) {
                                    dealCarGet.text = String.format("%.8f", response.body()!!.data.ant)
                                } else {
                                    dealCarGet.text = "+" + String.format("%.8f", response.body()!!.data.ant)
                                }
                                if (String.format("%.8f", response.body()!!.data.score).indexOf("-") != -1) {
                                    dealPropertyOut.text = String.format("%.8f", response.body()!!.data.score)
                                } else {
                                    dealPropertyOut.text = "+" + String.format("%.8f", response.body()!!.data.score)
                                }

                            } else {
                                Toast.makeText(this@PropertyDetailActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                        }
                    }

                    override fun onFailure(call: Call<PropertyStm>, t: Throwable) {
                        if (t is DataResultException) {
                            val resultException = t as DataResultException
                            Toast.makeText(this@PropertyDetailActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
            if (intent.extras.getString("propertytype") == "property") {
                val nowtime = DateUtils.getdata()
                val login = retrofit.create(MoneyService::class.java).getAntLogScoreDetailStm(id, token, intent.extras.getString("order"), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
                login.enqueue(object : Callback<PropertyStm> {
                    override fun onResponse(call: Call<PropertyStm>, response: Response<PropertyStm>) {
                        try {
                            if (response.body()!!.code == 1) {
                                dealNumber.text = response.body()!!.data.orderId.toString()
                                dealType.text = response.body()!!.data.tradeTypeDesc
                                if (response.body()!!.data.tradeTypeDesc == "地域红包支付" || response.body()!!.data.tradeTypeDesc == "普通红包支付") {
                                    dsada.text="支出账户"
                                    dasd.text="支出方信息"
                                }
                                dealTime.text = DateUtils.timeslashData(response.body()!!.data.createTime.toString())
                                dealnameOut.text = response.body()!!.data.phone
                                dealName.text = response.body()!!.data.username
                                dealCarGet.text = String.format("%.8f", response.body()!!.data.ant)
                                if (String.format("%.8f", response.body()!!.data.score).indexOf("-") != -1) {
                                    dealPropertyOut.text = String.format("%.8f", response.body()!!.data.score)
                                } else {
                                    dealPropertyOut.text = "+" + String.format("%.8f", response.body()!!.data.score)
                                }

                            } else {
                                Toast.makeText(this@PropertyDetailActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                        }
                    }

                    override fun onFailure(call: Call<PropertyStm>, t: Throwable) {
                        if (t is DataResultException) {
                            Toast.makeText(this@PropertyDetailActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        } catch (e: Exception) {
            abnormal(this)
        }

    }
}
