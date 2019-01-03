package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.administrator.mode.R
import kotlinx.android.synthetic.main.activity_dial.*

class dialActivity : AppCompatActivity() {

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        dialImplWebView.loadUrl("http://fcsap.com/src/index/WelfareDraw.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", ""))

    }

    @JavascriptInterface
    fun dialViewFinish() {
        finish()
    }

    @JavascriptInterface
    fun dialSuccess(xx: String) {
        Toast.makeText(this, xx, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun jumpdialImpl() {
        startActivity(Intent(this, dialImplActivity::class.java))
    }

}
