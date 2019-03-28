package com.example.administrator.mode.creatorprivatekey

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.webkit.JavascriptInterface
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Activity.MainActivity
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.KeyAddressBean
import com.example.administrator.mode.Pojo.Loginturn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.app.MyApplication
import kotlinx.android.synthetic.main.activity_lead_key.*
import org.web3j.crypto.Credentials
import org.web3j.crypto.ECKeyPair
import org.web3j.utils.Numeric
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeadKeyActivity : BaseActivity() {

    var webViewAlbum: WebViewAlbum? = null
    override fun getContentViewId(): Int {
        return R.layout.activity_lead_key
    }

    @SuppressLint("JavascriptInterface")
    override fun init() {
        leadKeyWebView.settings.domStorageEnabled = true
        leadKeyWebView.settings.javaScriptEnabled = true
        leadKeyWebView.settings.allowFileAccess = true
        leadKeyWebView.settings.allowContentAccess = true
        leadKeyWebView.webViewClient = object : WebViewClient() {}
        leadKeyWebView.settings.loadWithOverviewMode = true
        leadKeyWebView.addJavascriptInterface(this, "ANT")
        val ua = leadKeyWebView.settings.userAgentString
        leadKeyWebView.settings.userAgentString = "$ua/AntDapp"
        webViewAlbum = WebViewAlbum(this)
        leadKeyWebView.webChromeClient = webViewAlbum
        if (intent.getStringExtra("leadKeyUrl") == "11") {
            val sp = this.getSharedPreferences("USER", Context.MODE_PRIVATE)
              leadKeyWebView.loadUrl("http://ipfs.fuyer.com/ipns/Qma5JwPPYmHEGSdxwvF8dQDrFxe4z2uHUSBZB4WAdv5Crc/wallet/src/importWallet.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
           /* leadKeyWebView.loadUrl("http://192.168.31.211:8020/ant/wallet/src/importWallet.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")*/
        }else{
            val sp = this.getSharedPreferences("USER", Context.MODE_PRIVATE)
              leadKeyWebView.loadUrl("http://ipfs.fuyer.com/ipns/Qma5JwPPYmHEGSdxwvF8dQDrFxe4z2uHUSBZB4WAdv5Crc/wallet/src/importWallet.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
            /*leadKeyWebView.loadUrl("http://192.168.31.211:8020/ant/wallet/src/importWallet.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android&LoginPrivateKey="+intent.getStringExtra("leadKeyUrl"))*/
        }
    }

    @JavascriptInterface
    fun goThisBackPage() {
        finish()
    }

    @JavascriptInterface
    fun webViewLeadKeyCallBack(walletName: String, myAddress: String, myMnemonic: String, keyName: String, keystore: String, userPrivatelyKey: String, UserPwd: String) {
        val getHash = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java)
        try {
            var xx = "0"
            val keyPair = ECKeyPair.create(Numeric.hexStringToByteArray(userPrivatelyKey))
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(MoneyService::class.java).walletLogin(nowtime, MessageSignUtils.Sign(Credentials.create(keyPair), nowtime.toString()), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
            register.enqueue(object : Callback<Loginturn> {
                override fun onResponse(call: Call<Loginturn>, response: Response<Loginturn>) {
                    if (response.body()!!.code == 1) {
                        for (i in getHash) {
                            /*    if (i.key == walletName + response.body()!!.data!!.id.toString()){
                                   toast(getString(R.string.havewaell))
                                    xx="1"
                                    return
                                }*/
                            if (i.value.address == myAddress) {
                                toast(getString(R.string.havewaell))
                                xx = "1"
                                return
                            }
                        }
                        if (xx == "0") {
                            val keyAddressMap = HashMap<String, KeyAddressBean>()
                            val keyAddressBeanInput = KeyAddressBean()
                            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                            val edit = sp.edit()
                            edit.putString("user_id", response.body()!!.data!!.id.toString())
                            edit.putString("user_token", response.body()!!.data!!.token)
                            edit.putString("user_phone", response.body()!!.data!!.phone)
                            edit.putString("oldwcn", response.body()!!.data!!.worldCode)
                            edit.putString("user_name", response.body()!!.data!!.username)
                            edit.putBoolean("first", false)
                            edit.putString("useravatar", response.body()!!.data!!.avatar)
                            edit.commit()
                            if (getHash.size > 0) {
                                keyAddressBeanInput.name = userPrivatelyKey
                                keyAddressBeanInput.address = myAddress
                                keyAddressBeanInput.keystore = keystore
                                keyAddressBeanInput.mnemonic = myMnemonic
                                keyAddressBeanInput.userHead = response.body()!!.data!!.avatar
                                keyAddressBeanInput.walletName = walletName
                                keyAddressBeanInput.userPrivatelyKey = UserPwd
                                keyAddressBeanInput.userId = response.body()!!.data!!.id.toString()
                                keyAddressBeanInput.userName = response.body()!!.data!!.username
                                keyAddressMap[walletName + response.body()!!.data!!.id.toString()] = keyAddressBeanInput
                                for (i in getHash) {
                                    keyAddressMap[i.key] = i.value
                                }
                                KeyUtlis.HaveKey(keyAddressMap)

                            } else {
                                keyAddressBeanInput.name = userPrivatelyKey
                                keyAddressBeanInput.address = myAddress
                                keyAddressBeanInput.keystore = keystore
                                keyAddressBeanInput.mnemonic = myMnemonic
                                keyAddressBeanInput.walletName = walletName
                                keyAddressBeanInput.userHead = response.body()!!.data!!.avatar
                                keyAddressBeanInput.userId = response.body()!!.data!!.id.toString()
                                keyAddressBeanInput.userName = response.body()!!.data!!.username
                                keyAddressBeanInput.userPrivatelyKey = UserPwd
                                keyAddressMap[walletName + response.body()!!.data!!.id.toString()] = keyAddressBeanInput
                                KeyUtlis.HaveKey(keyAddressMap)
                            }
                            MyApplication.keyAddressBeans = keyAddressBeanInput
                            startActivity(Intent(this@LeadKeyActivity, MainActivity::class.java))
                            finish()
                        }
                    } else {
                    }
                }

                override fun onFailure(call: Call<Loginturn>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@LeadKeyActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) { }
    }
}
