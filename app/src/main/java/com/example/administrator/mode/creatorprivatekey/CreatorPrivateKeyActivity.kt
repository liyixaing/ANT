package com.example.administrator.mode.creatorprivatekey

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import kotlinx.android.synthetic.main.activity_creator_private_key.*
import android.webkit.JavascriptInterface
import android.webkit.WebViewClient
import com.example.administrator.mode.Utlis.*
class CreatorPrivateKeyActivity : BaseActivity() {

    var webViewAlbum: WebViewAlbum? = null
    override fun getContentViewId(): Int {
        return R.layout.activity_creator_private_key
    }

    override fun init() {
        initTit()
    }

    @SuppressLint("JavascriptInterface")
    private fun initTit() {
        CreatorPrivateKeyWebView.settings.domStorageEnabled = true
        CreatorPrivateKeyWebView.settings.javaScriptEnabled = true
        CreatorPrivateKeyWebView.settings.allowFileAccess = true
        CreatorPrivateKeyWebView.settings.allowContentAccess = true
        CreatorPrivateKeyWebView.webViewClient = object : WebViewClient() {}
        CreatorPrivateKeyWebView.settings.loadWithOverviewMode = true
        CreatorPrivateKeyWebView.addJavascriptInterface(this, "ANT")
        val ua = CreatorPrivateKeyWebView.settings.userAgentString
        CreatorPrivateKeyWebView.settings.userAgentString = "$ua/AntDapp"
        webViewAlbum = WebViewAlbum(this)
        CreatorPrivateKeyWebView.webChromeClient = webViewAlbum
        val sp = this.getSharedPreferences("USER", Context.MODE_PRIVATE)
        CreatorPrivateKeyWebView.loadUrl("http://ipfs.fuyer.com/ipns/Qma5JwPPYmHEGSdxwvF8dQDrFxe4z2uHUSBZB4WAdv5Crc/src/login/CreateWallet.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
       /* CreatorPrivateKeyWebView.loadUrl("http://192.168.31.211:8020/ant/src/login/CreateWallet.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")*/
    }

    @JavascriptInterface
    fun getAddress(getAddress: String) {
        toast(getAddress)
    }

    @JavascriptInterface
    fun finishThis() {
        finish()
    }

    @JavascriptInterface
    fun getCreatorKeyMessageCallBack(walletName: String, myAddress: String, myMnemonic: String, keyName: String, keystore: String, userPrivatelyKey: String, UserPwd: String) {
            val intent = Intent(this@CreatorPrivateKeyActivity, BackupsWalletActivity::class.java)
            intent.putExtra("readFileInput", keystore)
            intent.putExtra("walletName", walletName)
            intent.putExtra("readFileName", userPrivatelyKey)
            intent.putExtra("readNameInput", keyName)
            intent.putExtra("readaddress", myAddress)
            intent.putExtra("readMnemonic", myMnemonic)
            intent.putExtra("UserPwd", UserPwd)
            startActivity(intent)
            finish()
    }
}
/*  private fun createCredentials() {
      val REQUEST_EXTERNAL_STORAGE = 1
      val PERMISSIONS_STORAGE = arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
      val permission = ActivityCompat.checkSelfPermission(this@CreatorPrivateKeyActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
      if (permission != PackageManager.PERMISSION_GRANTED) {
          // We don't have permission so prompt the user
          ActivityCompat.requestPermissions(
                  this,
                  PERMISSIONS_STORAGE,
                  REQUEST_EXTERNAL_STORAGE
          )
      }
      val filePath = Environment.getExternalStorageDirectory().toString()
      val myBip = WalletUtils.generateBip39Wallet(keyPwd.text.toString().trim(), File(filePath))
      val fileName = myBip.filename
      val myMnemonic = myBip.mnemonic
      val credentials = WalletUtils.loadCredentials(keyPwd.text.toString().trim(), "$filePath/$fileName")
      val myAddress = credentials.address
      var hash256 = BytesUtils.hexStringToBytes("0x63c1dd951ffedf6f7fd968ad4efa39b8ed584f162f46e715114ee184f8de9201")
      val ecd = credentials.ecKeyPair.sign(hash256)
      var rstr = BytesUtils.bytesToHexString(ecd.r.toByteArray())
      var sstr = BytesUtils.bytesToHexString(ecd.s.toByteArray())
      var con = rstr + sstr
      Log.i("Ret", con)
      if (readFile(fileName) != "" && readFile(fileName) != "") {
          val intent = Intent(this, BackupsWalletActivity::class.java)
          intent.putExtra("readFileInput", readFile(fileName))
          intent.putExtra("readFileName", fileName)
          intent.putExtra("readNameInput", keyName.text.toString().trim())
          intent.putExtra("readaddress", myAddress)
          intent.putExtra("readMnemonic", myMnemonic)
          startActivity(intent)
          finish()
      }
  }

  fun readFile(fileName: String): String {
      var res: String
      val stringBuilder = StringBuilder()
      try {
          val bufferedReader = BufferedReader(InputStreamReader(FileInputStream(Environment.getExternalStorageDirectory().toString() + "/" + fileName)))
          val arr = CharArray(2048)
          var read: Int
          do {
              read = bufferedReader.read(arr, 0, arr.size)
              if (read < 0) {
                  break
              }
              stringBuilder.append(String(arr, 0, read))
          } while (bufferedReader.readLine() != null)
      } catch (e: Exception) {
          Log.i("dadada", e.toString())
      } finally {
          res = stringBuilder.toString()
      }
      return res
  }*/

