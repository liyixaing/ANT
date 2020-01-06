package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.webkit.JavascriptInterface
import android.webkit.WebViewClient
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.Retrofit_manager
import com.example.administrator.mode.Utlis.VerifyUtlis
import com.example.administrator.mode.Utlis.WebViewAlbum
import com.example.administrator.mode.app.MyApplication
import com.github.shenyuanqing.zxingsimplify.zxing.Activity.CaptureActivity
import kotlinx.android.synthetic.main.activity_consult.*
import kotlinx.android.synthetic.main.activity_subpage_deal.*
import java.lang.Exception


class ConsultActivity : BaseActivity() {

    var webViewAlbum: WebViewAlbum? = null
    override fun getContentViewId(): Int {
        return R.layout.activity_consult
    }

    @SuppressLint("JavascriptInterface")
    override fun init() {
        super.init()
        xdasdac.settings.domStorageEnabled = true
        xdasdac.settings.javaScriptEnabled = true
        xdasdac.settings.allowFileAccess = true
        xdasdac.settings.allowContentAccess = true
        xdasdac.webViewClient = object : WebViewClient() {}
        xdasdac.settings.loadWithOverviewMode = true
        xdasdac.addJavascriptInterface(this, "ANT")
        val ua = xdasdac.settings.userAgentString
        xdasdac.settings.userAgentString = "$ua/AntDapp"
        webViewAlbum = WebViewAlbum(this)
        xdasdac.webChromeClient = webViewAlbum
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        if (intent.getStringExtra("webUrl") == "https://m.jinse.com/member/209230") {
            xdasdac.loadUrl("https://m.jinse.com/member/209230")
        } else if (intent.getStringExtra("webUrl") == "mountain") {
            // xdasdac.loadUrl("http://192.168.31.211:8020/ant/wallet/src/Ruby/exchange01.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
            xdasdac.loadUrl(Retrofit_manager.WEBURL+"/wallet/src/Ruby/exchange01.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
        } else if (intent.getStringExtra("webUrl") == "funding") {
            /*  xdasdac.loadUrl("http://192.168.31.211:8020/ant/wallet/src/Ruby/exchange.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")*/
            xdasdac.loadUrl(Retrofit_manager.WEBURL+"/wallet/src/Ruby/exchange.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
        } else if (intent.getStringExtra("webUrl") == "purchasing") {
            xdasdac.loadUrl(Retrofit_manager.WEBURL+"/src/index/CloudPurchase.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
        } else if (intent.getStringExtra("webUrl") == "CustomerService") {
            xdasdac.loadUrl(Retrofit_manager.WEBURL+"/src/index/CustomerService.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
        }
    }

    @JavascriptInterface
    fun goThisBackPage() {
        finish()
    }


    fun goBackPage(): Boolean {
        if (xdasdac.canGoBack()) {
            xdasdac.goBack()
            return true
        }
        return false
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (goBackPage()) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    @JavascriptInterface
    fun clipboardSet(message: String) {
        VerifyUtlis.copy(this, message)
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
    fun openScan() {
        try {
            val intent = Intent(this, CaptureActivity::class.java)
            startActivityForResult(intent, 6666)
        } catch (e: Exception) {

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == 6666) {
                val result = data!!.getStringExtra("barCode")
                subpageDealWebView.loadUrl("javascript:ScanningCallBackFunc('$result')")
            }
        } catch (e: Exception) {

        }
    }
}
