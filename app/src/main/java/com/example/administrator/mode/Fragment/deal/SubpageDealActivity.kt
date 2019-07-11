package com.example.administrator.mode.Fragment.deal

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.KeyEvent
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.drawer.DownloadTask
import com.example.administrator.mode.Fragment.homeFragment.ANTStoreActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.PreferencesUtil
import com.example.administrator.mode.Utlis.VerifyUtlis
import com.example.administrator.mode.Utlis.WebViewAlbum
import com.example.administrator.mode.app.MyApplication
import com.github.shenyuanqing.zxingsimplify.zxing.Activity.CaptureActivity
import kotlinx.android.synthetic.main.activity_subpage_deal.*
import java.lang.Exception

class SubpageDealActivity : BaseActivity() {
    var xxxxxxxxx = ""
    override fun getContentViewId(): Int {
        return R.layout.activity_subpage_deal
    }

    @SuppressLint("JavascriptInterface")
    override fun init() {
        subpageDealWebView.settings.domStorageEnabled = true
        subpageDealWebView.settings.javaScriptEnabled = true
        subpageDealWebView.settings.allowFileAccess = true
        subpageDealWebView.settings.allowContentAccess = true
        subpageDealWebView.webViewClient = object : WebViewClient() {}
        subpageDealWebView.settings.loadWithOverviewMode = true
        subpageDealWebView.addJavascriptInterface(this, "ANT")
        val ua = subpageDealWebView.settings.userAgentString
        subpageDealWebView.settings.userAgentString = "$ua/AntDapp"
        val webViewAlbum = WebViewAlbum(this)
        subpageDealWebView.webChromeClient = webViewAlbum
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        subpageDealWebView.loadUrl(intent.extras.getString("dealurl") + "?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
    }

    fun goBackPage(): Boolean {
        if (subpageDealWebView.canGoBack()) {
            subpageDealWebView.goBack()
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
    fun goThisBackPage() {
        finish()
    }

    @JavascriptInterface
    fun jumpMarketOrder() {
        val intent = Intent(this@SubpageDealActivity, ANTStoreActivity::class.java)
        intent.putExtra("url", "http://47.91.22.21/s?route=account/order")
        startActivity(intent)
        finish()
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
        }catch (e:Exception){

        }

    }
    @JavascriptInterface
    fun isInstallApp(): Boolean {
        return VerifyUtlis.isInstallApp(this)
    }

    @JavascriptInterface
    fun openApp() {
        val intent = Intent()
        intent.data = Uri.parse("rubychainwallet://")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == 6666) {
                val result = data!!.getStringExtra("barCode")
                subpageDealWebView.loadUrl("javascript:ScanningCallBackFunc('$result')")
            }
        }catch (e:Exception){

        }

    }

    @JavascriptInterface
    fun downloadApp(url:String){
        Log.i("wdaw",url)
        try {
            if (PreferencesUtil.get("language", "").equals("zh")) {
                xxxxxxxxx = "正在下载....."

            } else if (PreferencesUtil.get("language", "").equals("en")) {
                xxxxxxxxx = "downloading....."
            }
            var progressDialog: ProgressDialog? = null
            progressDialog = ProgressDialog(this)
            progressDialog.setMessage(xxxxxxxxx)
            progressDialog.isIndeterminate = true
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progressDialog.setCancelable(true)
            val downloadTask: DownloadTask = DownloadTask(this, progressDialog)

            downloadTask.execute(url)
            progressDialog.setOnCancelListener {
                downloadTask.cancel(true)
            }
        }catch (e:Exception){

        }
    }
    @JavascriptInterface
    fun finishThis() {
        val intent = Intent(this@SubpageDealActivity, ANTStoreActivity::class.java)
        intent.putExtra("url", "http://47.91.22.21/s?route=account/order")
        startActivity(intent)
        finish()
    }
}
