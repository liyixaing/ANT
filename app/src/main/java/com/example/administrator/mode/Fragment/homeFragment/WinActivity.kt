package com.example.administrator.mode.Fragment.homeFragment

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.administrator.mode.R
import kotlinx.android.synthetic.main.activity_win.*

class WinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)
        winWebView.settings.javaScriptEnabled = true
        winWebView.settings.loadWithOverviewMode = true
        winWebView.settings.domStorageEnabled = true
        winWebView.settings.defaultTextEncodingName = "utf-8"
        winWebView.addJavascriptInterface(this, "ANT")
        winWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
            }
        }
        val sp = this!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
        winWebView.loadUrl("http://fcsap.com/src/index/GameRecord.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "")+"&language="+sp.getString("language",""))
         /* winWebView.loadUrl("http://192.168.31.211:8020/ant/src/index/GameRecord.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "")+"&language="+sp.getString("language",""))*/
    }

    @JavascriptInterface
    fun winViewFinish() {
        finish()
    }
}
