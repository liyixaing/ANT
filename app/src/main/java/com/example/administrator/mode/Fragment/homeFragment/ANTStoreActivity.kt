package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import com.example.administrator.mode.Activity.LoginActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.Encryption
import kotlinx.android.synthetic.main.activity_antstore.*
import com.example.administrator.mode.Activity.MainActivity
import com.example.administrator.mode.Utlis.WebViewAlbum

class ANTStoreActivity : AppCompatActivity() {
    var paxWebChromeClient: WebViewAlbum?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antstore)
        initView(intent.extras.getString("url"))
        Log.i("sdasdsa", intent.extras.getString("url"))
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView(url: String) {
        try {
            shopping.settings.domStorageEnabled = true
            shopping.webViewClient = object : WebViewClient() {}
            shopping.settings.javaScriptEnabled = true
            shopping.addJavascriptInterface(this, "ANTAndroid")
            shopping.settings.allowFileAccess = true
            shopping.settings.allowContentAccess = true
            paxWebChromeClient=WebViewAlbum(this)
            shopping.webChromeClient = paxWebChromeClient

            val ua = shopping.settings.userAgentString
            shopping.settings.userAgentString = "$ua/AntDapp"
            shopping.loadUrl(url)
        }catch (e:Exception){
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (goBackPage()) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun goBackPage(): Boolean {
        if (shopping.canGoBack()) {
            shopping.goBack()
            return true
        }
        return false
    }

    @JavascriptInterface
    fun PaymentOrder(commodityOrder: String, commodityOrderkey: String) {
        val intent = Intent(this@ANTStoreActivity, CommodityDealActivity::class.java)
        intent.putExtra("commodityOrder", commodityOrder)
        intent.putExtra("commodityOrderkey", commodityOrderkey)
        startActivity(intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        paxWebChromeClient!!.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }
    @JavascriptInterface
    fun VerifyUser(userid: String, usertoken: String): Boolean {
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        if (userid == sp.getString("user_id", "")) {
            if (Encryption.verifyTokenFromShopTransmit(sp.getString("user_token", ""), usertoken)) {
                return true
            }
            return false
        }
        return false
    }

    @JavascriptInterface
    fun jumpLogin() {
        startActivity(Intent(this@ANTStoreActivity, LoginActivity::class.java))
        finish()
    }

    @JavascriptInterface
    fun inANTAndroid(): Boolean {
        return true
    }

    @JavascriptInterface
    fun jumpHome() {
        startActivity(Intent(this@ANTStoreActivity, MainActivity::class.java))
        finish()
    }

}