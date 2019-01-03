package com.example.administrator.mode.Fragment.homeFragment

import android.content.Context
import android.content.Intent
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Activity.drawer.SecurityFindPaypwdActivity
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.selectOrderDetail
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_commodity_deal.*

class CommodityDealActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_commodity_deal
    }

    override fun init() {
        super.init()
        close.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        pay.setOnReleasedListener {
            val payDialog = PayDialog(this@CommodityDealActivity)
            payDialog.commodityDealActivity = this@CommodityDealActivity
            payDialog.show()
        }
        initview()
    }

    private fun initview() {
            val nowtime=DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val transfer = retrofit.create(MoneyService::class.java!!).selectOrder(sp.getString("user_id", ""), sp.getString("user_token", ""), intent.extras.getString("commodityOrder"), intent.extras.getString("commodityOrderkey"), "1",nowtime,PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "")+nowtime))
            transfer.enqueue(object : Callback<selectOrderDetail> {
                override fun onResponse(call: Call<selectOrderDetail>, response: Response<selectOrderDetail>) {
                        if (response.body()!!.code == 1) {
                            commodityCollection.text = response.body()!!.data.shopName
                            commodityOrder.text = response.body()!!.data.orderNo
                            payType.text = "通证"
                            payNumber.text = String.format("%.8f", response.body()!!.data.totalAmount)
                            payGetCard.text = "+" + String.format("%.8f", response.body()!!.data.harvestScore)
                        }

                }
                override fun onFailure(call: Call<selectOrderDetail>, t: Throwable) {
                    Log.i("whzzzzzz",t.message.toString())
                    if (t is DataResultException) {
                        Toast.makeText(this@CommodityDealActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                    finish()
                }
            })


    }

    fun tiaozhuan() {
        startActivity(Intent(this@CommodityDealActivity, SecurityFindPaypwdActivity::class.java))
    }

    //自定义支付密码框回掉
    fun OnInputPayPasswrodSuccess(inputPwd: String) {

            val nowtime= DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val getNodeMessage = retrofit.create(GitHubService::class.java!!).antpay(sp.getString("user_id", ""), sp.getString("user_token", ""), intent.extras.getString("commodityOrderkey"), intent.extras.getString("commodityOrder"),inputPwd,"1",nowtime,PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "")+nowtime))
            getNodeMessage.enqueue(object : Callback<Common> {
                override fun onResponse(call: Call<Common>, response: Response<Common>) {
                    if (response.body()!!.code == 1) {
                        Toast.makeText(this@CommodityDealActivity, R.string.CommodityDeal_ok, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@CommodityDealActivity, ANTStoreActivity::class.java)
                        intent.putExtra("url", "http://mall.fcsap.com/?route=account/order")
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@CommodityDealActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Common>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@CommodityDealActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })

    }
}