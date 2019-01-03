package com.example.administrator.mode.Fragment.homeFragment

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.PropertyPer
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_person_to_person_detail.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonToPersonDetailActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_person_to_person_detail
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            startActivity(Intent(this@PersonToPersonDetailActivity, PropertyActivity::class.java))
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun init() {
        super.init()
        val retrofit = Retrofit_manager.getInstance().getUserlogin()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        var token = sp.getString("user_token", "")
        var id = sp.getString("user_id", "")
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@PersonToPersonDetailActivity, PropertyActivity::class.java))
                finish()
            }
        })
        tit_name.setText(R.string.Deal_tit)
        try {
            val nowtime = DateUtils.getdata()
            if (intent.extras.getString("propertytype").equals("tong")) {
                val login = retrofit.create(MoneyService::class.java!!).getAntBananceLogDetailPer(id, token, intent.extras.getString("order"), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(token + nowtime))
                login.enqueue(object : Callback<PropertyPer> {
                    override fun onResponse(call: Call<PropertyPer>, response: Response<PropertyPer>) {
                        try {
                            if (response.body()!!.code == 1) {
                                personNumber.text = response.body()!!.data.orderId.toString()
                                personType.text = response.body()!!.data.tradeTypeDesc
                                personTime.text = DateUtils.timeslashData(response.body()!!.data.createTime.toString())
                                dealExpenditureId.text = response.body()!!.data.fromPhone
                                personName.text = response.body()!!.data.fromUsername
                                expenditureNumber.text = String.format("%.8f", response.body()!!.data.fromChangeAnt)
                                expenditurecurrency.text = response.body()!!.data.coin
                                expenditureService.text = response.body()!!.data.rate.toString()
                                expenditureAllOut.text = "+" + String.format("%.8f", response.body()!!.data.fromChangeScore)
                                nameOut.text = response.body()!!.data.toPhone
                                personNameGet.text = response.body()!!.data.toUsername
                                expenditureGet.text = "+" + String.format("%.8f", response.body()!!.data.toChangeAnt)
                                ecurrency.text = response.body()!!.data.coin
                                expenditureAllGet.text = "+" + String.format("%.8f", response.body()!!.data.toChangeScore)
                               Log.i("WHZZZZ",response.body()!!.data.memo+"ssss")
                                beizhuzhu.text=response.body()!!.data.memo
                            } else {
                                Toast.makeText(this@PersonToPersonDetailActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                        }
                    }

                    override fun onFailure(call: Call<PropertyPer>, t: Throwable) {
                        Log.i("whz", t.toString())
                        if (t is DataResultException) {
                            val resultException = t as DataResultException
                            Toast.makeText(this@PersonToPersonDetailActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
            if (intent.extras.getString("propertytype").equals("property")) {
                val nowtime = DateUtils.getdata()
                val login = retrofit.create(MoneyService::class.java!!).getAntLogScoreDetailPer(id, token, intent.extras.getString("order"), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
                login.enqueue(object : Callback<PropertyPer> {
                    override fun onResponse(call: Call<PropertyPer>, response: Response<PropertyPer>) {
                        try {
                            if (response.body()!!.code == 1) {
                                personNumber.text = response.body()!!.data.orderId.toString()
                                personType.text = response.body()!!.data.tradeTypeDesc
                                personTime.text = DateUtils.timeslashData(response.body()!!.data.createTime.toString())
                                dealExpenditureId.text = response.body()!!.data.fromPhone
                                personName.text = response.body()!!.data.fromUsername
                                expenditureNumber.text = String.format("%.8f", response.body()!!.data.fromChangeAnt)
                                expenditurecurrency.text = response.body()!!.data.coin
                                expenditureService.text = response.body()!!.data.rate.toString()
                                expenditureAllOut.text = "+" + String.format("%.8f", response.body()!!.data.fromChangeAnt)
                                nameOut.text = response.body()!!.data.toPhone
                                personNameGet.text = response.body()!!.data.toUsername
                                expenditureGet.text = " + " + String.format("%.8f", response.body()!!.data.toChangeAnt)
                                ecurrency.text = response.body()!!.data.coin
                                expenditureAllGet.text = " + " + String.format("%.8f", response.body()!!.data.toChangeAnt)
                                beizhuzhu.text=response.body()!!.data.memo
                            } else {
                                Toast.makeText(this@PersonToPersonDetailActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                        }
                    }

                    override fun onFailure(call: Call<PropertyPer>, t: Throwable) {
                        if (t is DataResultException) {
                            val resultException = t as DataResultException
                            Toast.makeText(this@PersonToPersonDetailActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        } catch (e: Exception) {
            abnormal(this)
        }


    }
}
