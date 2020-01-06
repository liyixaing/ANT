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
        storeWebView.loadUrl( Retrofit_manager.WEBURL+"/src/Mall/TransactionPayment.html?order_no=" + intent.extras.getString("commodityOrder") + "&mall_key=" + intent.extras.getString("commodityOrderkey") + "&language=" + sp.getString("language", "") + "&loginType=android&user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", ""))

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
    fun openApp(url: String) {
        try {
            val intent = Intent()
            intent.data = Uri.parse(url)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        } catch (e: Exception) {
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
}
