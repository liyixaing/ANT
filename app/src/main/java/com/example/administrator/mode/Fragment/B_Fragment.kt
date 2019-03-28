package com.example.administrator.mode.Fragment


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.drawer.DownloadTask
import com.example.administrator.mode.Fragment.deal.SubpageDealActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import com.example.administrator.mode.Utlis.PreferencesUtil
import com.example.administrator.mode.Utlis.VerifyUtlis
import com.example.administrator.mode.Utlis.WebViewAlbum
import com.example.administrator.mode.app.MyApplication
import kotlinx.android.synthetic.main.fragment_b.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class B_Fragment : Fragment() {
    var xxxxxxxxx = ""
    var webViewAlbum: WebViewAlbum? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    @SuppressLint("JavascriptInterface")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dealWebView.settings.javaScriptEnabled = true
        dealWebView.settings.loadWithOverviewMode = true
        dealWebView.settings.domStorageEnabled = true
        dealWebView.settings.defaultTextEncodingName = "utf-8"
        dealWebView.addJavascriptInterface(this, "ANT")
        dealWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return true
            }
        }
        webViewAlbum = WebViewAlbum(activity!!)
        dealWebView.webChromeClient = webViewAlbum
        val ua = dealWebView.settings.userAgentString
        dealWebView.settings.userAgentString = "$ua/AntDapp"
        val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
        dealWebView.loadUrl("http://192.168.31.211:8020/ant/wallet/home.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
        /* dealWebView.loadUrl("http://ipfs.fuyer.com/ipns/Qma5JwPPYmHEGSdxwvF8dQDrFxe4z2uHUSBZB4WAdv5Crc/wallet/home.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")*/
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

    @JavascriptInterface
    fun clipboardSet(message: String) {
        VerifyUtlis.copy(activity, message)
    }

    @JavascriptInterface
    fun isInstallApp(): Boolean {
        return VerifyUtlis.isInstallApp(activity)
    }

    @JavascriptInterface
    fun openApp() {
        val intent = Intent()
        intent.data = Uri.parse("rubychainwallet://")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    @JavascriptInterface
    fun downloadApp(url:String){
        if (PreferencesUtil.get("language", "").equals("zh")) {
            xxxxxxxxx = "正在下载....."

        } else if (PreferencesUtil.get("language", "").equals("en")) {
            xxxxxxxxx = "downloading....."
        }
        var progressDialog: ProgressDialog? = null
        progressDialog = ProgressDialog(activity)
        progressDialog.setMessage(xxxxxxxxx)
        progressDialog.isIndeterminate = true
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        progressDialog.setCancelable(true)
        val downloadTask: DownloadTask = DownloadTask(activity, progressDialog)
        downloadTask.execute(url)
        progressDialog.setOnCancelListener {
            downloadTask.cancel(true)
        }
    }
}
