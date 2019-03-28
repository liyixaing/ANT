package com.example.administrator.mode.Fragment.homeFragment

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.administrator.mode.R
import kotlinx.android.synthetic.main.activity_dial.*

class dialImplActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dial_impl)
        setContentView(R.layout.activity_dial)
        dialImplWebView.settings.javaScriptEnabled = true
        dialImplWebView.settings.loadWithOverviewMode = true
        dialImplWebView.settings.domStorageEnabled = true
        dialImplWebView.settings.defaultTextEncodingName = "utf-8"
        dialImplWebView.addJavascriptInterface(this, "ANT")
        dialImplWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return true
            }

        }
        val sp = this.getSharedPreferences("USER", Context.MODE_PRIVATE)
        dialImplWebView.loadUrl("http://ipfs.fuyer.com/ipns/Qma5JwPPYmHEGSdxwvF8dQDrFxe4z2uHUSBZB4WAdv5Crc/src/index/WelfareDrawRecord.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", ""))

    }
    @JavascriptInterface
    fun dialImplViewFinish() {
        finish()
    }

}
