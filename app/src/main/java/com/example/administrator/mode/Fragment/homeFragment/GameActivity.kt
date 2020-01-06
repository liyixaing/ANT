package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.administrator.mode.Activity.drawer.SecurityFindPaypwdActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.Retrofit_manager
import kotlinx.android.synthetic.main.activity_antstore.*
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameWebView.settings.javaScriptEnabled = true
        gameWebView.settings.loadWithOverviewMode = true
        gameWebView.settings.domStorageEnabled = true
        gameWebView.settings.defaultTextEncodingName = "utf-8"
        gameWebView.addJavascriptInterface(this, "ANT")
        gameWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return true
            }
        }
        val sp = this.getSharedPreferences("USER", Context.MODE_PRIVATE)
        gameWebView.loadUrl(Retrofit_manager.WEBURL+"/src/index/Game.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", ""))
        /* gameWebView.loadUrl("http://192.168.31.211:8020/ant/src/index/Game.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", ""))*/
    }

    @JavascriptInterface
    fun gameViewFinish() {
        finish()
    }

    @JavascriptInterface
    fun gameSuccess(xx: String) {
        Toast.makeText(this, xx, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun gameReload() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
        finish()
    }


    @JavascriptInterface
    fun jumpWin() {
        startActivity(Intent(this, WinActivity::class.java))
    }

    fun GoBackPage(): Boolean {
        if (shopping.canGoBack()) {
            shopping.goBack()
            return true
        }
        return false
    }

    @JavascriptInterface
    fun findPayPwd() {
        startActivity(Intent(this@GameActivity, SecurityFindPaypwdActivity::class.java))
    }
}
