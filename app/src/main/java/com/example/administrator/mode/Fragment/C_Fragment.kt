package com.example.administrator.mode.Fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.administrator.mode.Fragment.deal.SubpageDealActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.Retrofit_manager
import com.example.administrator.mode.Utlis.VerifyUtlis
import com.example.administrator.mode.Utlis.WebViewAlbum
import com.example.administrator.mode.app.MyApplication
import kotlinx.android.synthetic.main.fragment_c.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class C_Fragment : Fragment() {
    var webViewAlbum: WebViewAlbum? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shequWebView.settings.javaScriptEnabled = true
        shequWebView.settings.loadWithOverviewMode = true
        shequWebView.settings.domStorageEnabled = true
        shequWebView.settings.defaultTextEncodingName = "utf-8"
        shequWebView.addJavascriptInterface(this, "ANT")
        shequWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return true
            }
        }
        webViewAlbum = WebViewAlbum(activity!!)
        shequWebView.webChromeClient = webViewAlbum
        val ua = shequWebView.settings.userAgentString
        shequWebView.settings.userAgentString = "$ua/AntDapp"
        val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)

      //  shequWebView.loadUrl("http://192.168.31.211:8020/ant/src/transaction/CrowdFunding.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
       shequWebView.loadUrl(Retrofit_manager.WEBURL+"/src/transaction/CrowdFunding.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
    }

    @JavascriptInterface
    fun jumpSubpage(url: String) {
        val intent = Intent(activity, SubpageDealActivity::class.java)
        intent.putExtra("dealurl", url)
        startActivity(intent)
    }

    @JavascriptInterface
    fun getPrivatelyKey(): String {
        return MyApplication.keyAddressBeans.name
    }

    @JavascriptInterface
    fun getUserPwd(): String {
        return MyApplication.keyAddressBeans.userPrivatelyKey
    }

}
