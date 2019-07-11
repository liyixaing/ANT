package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.MainActivity
import com.example.administrator.mode.Fragment.deal.SubpageDealActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.app.MyApplication
import kotlinx.android.synthetic.main.activity_commodity_deal.*
import java.lang.Exception

class CommodityDealActivity : BaseActivity() {

    var webViewAlbum: WebViewAlbum? = null
    override fun getContentViewId(): Int {
        return R.layout.activity_commodity_deal
    }

    @SuppressLint("JavascriptInterface")
    override fun init() {
        super.init()
        storeWebView
        storeWebView.settings.javaScriptEnabled = true
        storeWebView.settings.loadWithOverviewMode = true
        storeWebView.settings.domStorageEnabled = true
        storeWebView.settings.defaultTextEncodingName = "utf-8"
        storeWebView.addJavascriptInterface(this, "ANT")
        storeWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return true
            }
        }
        webViewAlbum = WebViewAlbum(this)
        storeWebView.webChromeClient = webViewAlbum
        val ua = storeWebView.settings.userAgentString
        storeWebView.settings.userAgentString = "$ua/AntDapp"
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
       // storeWebView.loadUrl("http://192.168.31.211:8020/ant/src/Mall/TransactionPayment.html?order_no=" +intent.extras.getString("commodityOrder")+ "&mall_key=" + intent.extras.getString("commodityOrderkey") + "&language=" + sp.getString("language", "") + "&loginType=android&user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", ""))
        storeWebView.loadUrl("http://ipfs.fuyer.com/ipns/Qma5JwPPYmHEGSdxwvF8dQDrFxe4z2uHUSBZB4WAdv5Crc/src/Mall/TransactionPayment.html?order_no=" +intent.extras.getString("commodityOrder")+ "&mall_key=" + intent.extras.getString("commodityOrderkey") + "&language=" + sp.getString("language", "") + "&loginType=android&user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", ""))

    }
    @JavascriptInterface
    fun jumpHome() {
        startActivity(Intent(this@CommodityDealActivity, MainActivity::class.java))
        finish()
    }
    @JavascriptInterface
    fun jumpMarketOrder() {
        val intent = Intent(this@CommodityDealActivity, ANTStoreActivity::class.java)
                    intent.putExtra("url", "http://mall.fcsap.com/s?route=account/order")
                    startActivity(intent)
                    finish()
    }

    @JavascriptInterface
    fun isInstallApp(): Boolean {
        return VerifyUtlis.isInstallApp(this)
    }

    @JavascriptInterface
    fun jumpSubpage(url: String) {
        val intent = Intent(this, SubpageDealActivity::class.java)
        intent.putExtra("dealurl", url)
        startActivity(intent)
    }

    @JavascriptInterface
    fun openApp(url:String) {
        try {
            val intent = Intent()
            intent.data = Uri.parse(url)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }catch (e:Exception){
        }

    }

    @JavascriptInterface
    fun getPrivatelyKey(): String {
        return MyApplication.keyAddressBeans.name
    }

    @JavascriptInterface
    fun getUserPwd(): String {
        return MyApplication.keyAddressBeans.userPrivatelyKey
    }
    @JavascriptInterface
    fun finishThis() {
        finish()
    }
//        close.setOnClickListener(object : ClickUtlis() {
//            override fun onMultiClick(v: View?) {
//                finish()
//            }
//        })
//        pay.setOnReleasedListener {
//            val payDialog = PayDialog(this@CommodityDealActivity)
//            payDialog.setCommodityDealActivity(this, getString(R.string.AffirmDeal_tit))
//            payDialog.show()
//        }
//        initview()

//    private fun initview() {
//        val nowtime = DateUtils.getdata()
//        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
//        val retrofit = Retrofit_manager.getInstance().userlogin
//        val transfer = retrofit.create(MoneyService::class.java).selectOrder(sp.getString("user_id", ""), sp.getString("user_token", ""), intent.extras.getString("commodityOrder"), intent.extras.getString("commodityOrderkey"), "1", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
//        transfer.enqueue(object : Callback<selectOrderDetail> {
//            override fun onResponse(call: Call<selectOrderDetail>, response: Response<selectOrderDetail>) {
//                if (response.body()!!.code == 1) {
//                    commodityCollection.text = response.body()!!.data.shopName
//                    commodityOrder.text = response.body()!!.data.orderNo
//                    payType.text = "通证"
//                    payNumber.text = String.format("%.8f", response.body()!!.data.totalAmount)
//                    payGetCard.text = "+" + String.format("%.8f", response.body()!!.data.harvestScore)
//                }
//
//            }
//
//            override fun onFailure(call: Call<selectOrderDetail>, t: Throwable) {
//                if (t is DataResultException) {
//                    Toast.makeText(this@CommodityDealActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
//                }
//                finish()
//            }
//        })
//
//
//    }
//
//    //自定义支付密码框回掉
//    fun OnInputPayPasswrodSuccess(inputPwd: String) {
//        if (MyApplication.keyAddressBeans.userPrivatelyKey != inputPwd) {
//            Toast.makeText(this, R.string.pwdInputError, Toast.LENGTH_SHORT).show()
//            return
//        }
//        val nowtime = DateUtils.getdata()
//        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
//        val retrofit = Retrofit_manager.getInstance().userlogin
//        val keyPair = ECKeyPair.create(Numeric.hexStringToByteArray(SharedPreferencesUtil.getData("userPrivatelyKey", "").toString()))
//        val getNodeMessage = retrofit.create(GitHubService::class.java).antpay(sp.getString("user_id", ""), sp.getString("user_token", ""), intent.extras.getString("commodityOrderkey"), intent.extras.getString("commodityOrder"), "", "1", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), MessageSignUtils.Sign(Credentials.create(keyPair), Numeric.toHexString(VerifyUtlis.toHash(nowtime + sp.getString("user_token", ""))).substring(2, Numeric.toHexString(VerifyUtlis.toHash(nowtime + sp.getString("user_token", ""))).length)))
//        getNodeMessage.enqueue(object : Callback<Common> {
//            override fun onResponse(call: Call<Common>, response: Response<Common>) {
//                if (response.body()!!.code == 1) {
//                    Toast.makeText(this@CommodityDealActivity, R.string.CommodityDeal_ok, Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this@CommodityDealActivity, ANTStoreActivity::class.java)
//                    intent.putExtra("url", "http://mall.fcsap.com/?route=account/order")
//                    startActivity(intent)
//                    finish()
//                } else {
//                    Toast.makeText(this@CommodityDealActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Common>, t: Throwable) {
//                if (t is DataResultException) {
//                    Toast.makeText(this@CommodityDealActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
//
//    }
}
