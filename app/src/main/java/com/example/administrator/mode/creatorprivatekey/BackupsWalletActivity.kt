package com.example.administrator.mode.creatorprivatekey

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Activity.MainActivity
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.KeyAddressBean
import com.example.administrator.mode.Pojo.Loginturn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.app.MyApplication
import kotlinx.android.synthetic.main.activity_backups_wallet.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BackupsWalletActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_backups_wallet
    }

    override fun init() {
        fuzhi.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                VerifyUtlis.copy(this@BackupsWalletActivity, keyFileInput.text.toString().trim())
            }
        })
        fuzhi1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                VerifyUtlis.copy(this@BackupsWalletActivity, keyFileInput.text.toString().trim())
            }
        })
        tit_iv.setOnClickListener { finish() }
        tit_name.setText(R.string.BackupsWalletActivityTit)
        keyNameInput.text = intent.extras.getString("readNameInput")
        keyAddress.text = intent.extras.getString("readaddress")
        keyFileInput.text = intent.extras.getString("readMnemonic")

        backupWallet.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                /*      if (!PreferencesUtil.get("isChis", false)) {
                          startActivity()
                          return
                      }*/
                AlertView(getString(R.string.hint), getString(R.string.hint11), null, arrayOf(getString(R.string.Welcome_succeed), getString(R.string.Welcome_error)), null, this@BackupsWalletActivity, AlertView.Style.Alert, object : OnItemClickListener {
                    override fun onItemClick(o: Any?, position: Int) {
                        if (position == 0) {
                            if (MyApplication.userPhone == "") {
                                saveKey()
                                return
                            }
                            try {
                                val nowtime = DateUtils.getdata()
                                val retrofit = Retrofit_manager.getInstance().userlogin
                                val register = retrofit.create(GitHubService::class.java).registertwo(MyApplication.userPhone, "123456", MyApplication.userWc, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(nowtime))
                                register.enqueue(object : Callback<Common> {
                                    override fun onResponse(call: Call<Common>, response: Response<Common>) {
                                        try {
                                            if (response.body()!!.code == 1) {
                                                try {
                                                    val nowtime = DateUtils.getdata()
                                                    val retrofit = Retrofit_manager.getInstance().userlogin
                                                    val register = retrofit.create(GitHubService::class.java).registerthree(MyApplication.userPhone, "123456", MyApplication.userWc, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(nowtime))
                                                    register.enqueue(object : Callback<Common> {
                                                        override fun onResponse(call: Call<Common>, response: Response<Common>) {
                                                            try {
                                                                if (response.body()!!.code == 1) {
                                                                    creatorLogin()
                                                                } else {
                                                                    toast(response.body()!!.message)
                                                                }
                                                            } catch (e: Exception) {
                                                                abnormal(this@BackupsWalletActivity)
                                                            }
                                                        }

                                                        override fun onFailure(call: Call<Common>, t: Throwable) {
                                                            if (t is DataResultException) {
                                                                val attributes = HashMap<String, String>()
                                                                attributes["UserName"] = MyApplication.userPhone
                                                                StatService.onEvent(this@BackupsWalletActivity, "RegisterView.SendVerifyCode", "[请填写事件标签名]", 1, attributes)
                                                                Toast.makeText(this@BackupsWalletActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                                                            }
                                                        }
                                                    })
                                                } catch (e: Exception) {
                                                    abnormal(this@BackupsWalletActivity)
                                                }
                                            } else {
                                                Toast.makeText(this@BackupsWalletActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                            }
                                        } catch (e: Exception) {
                                            abnormal(this@BackupsWalletActivity)
                                        }
                                    }

                                    override fun onFailure(call: Call<Common>, t: Throwable) {
                                        val resultException = t as DataResultException
                                        Toast.makeText(this@BackupsWalletActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                                    }
                                })
                            } catch (e: Exception) {
                                abnormal(this@BackupsWalletActivity)
                            }
                        }
                    }
                }).show()
            }
        })
    }

    private fun creatorLogin() {
        val nowtime = DateUtils.getdata()
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val edit = sp.edit()
        val login = retrofit.create(GitHubService::class.java).login(MyApplication.userPhone, "123456", VerifyUtlis.getIMEI(this@BackupsWalletActivity), MyApplication.userWc, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(nowtime))
        login.enqueue(object : Callback<Loginturn> {
            override fun onResponse(call: Call<Loginturn>, response: Response<Loginturn>) {
                try {
                    if (response.body()!!.code == 1) {
                        StatService.onEvent(this@BackupsWalletActivity, "LoginView.LoginSuccess", "xms", 1)
                        StatService.onPageEnd(this@BackupsWalletActivity, "LoginModule.LoginView")
                        edit.putString("user_id", response.body()!!.data!!.id.toString())
                        edit.putString("user_token", response.body()!!.data!!.token)
                        edit.putString("user_phone", response.body()!!.data!!.phone)
                        edit.putString("oldwcn", response.body()!!.data!!.worldCode)
                        edit.putString("user_name", response.body()!!.data!!.username)
                        edit.putBoolean("first", false)
                        edit.putString("useravatar", response.body()!!.data!!.avatar)
                        edit.commit()
                        saveKey()
                    } else {
                        Toast.makeText(this@BackupsWalletActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    abnormal(this@BackupsWalletActivity)
                }
            }

            override fun onFailure(call: Call<Loginturn>, t: Throwable) {
                if (t is DataResultException) {
                    Toast.makeText(this@BackupsWalletActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    val attributes = HashMap<String, String>()
                    StatService.onEvent(this@BackupsWalletActivity, "LoginView.LoginFailed", "xms", 1, attributes)
                    /*  Toast.makeText(this@LoginActivity, R.string.Login_error, Toast.LENGTH_SHORT).show()*/
                }
            }
        })

    }

    private fun saveKey() {
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        try {
            val nowtime = DateUtils.getdata()
            val retrofit = Retrofit_manager.getInstance().userlogin
            val key = AESCipher.aesEncryptString(intent.extras.getString("readFileName"))
            Log.i("dadas", key)
            val register = retrofit.create(MoneyService::class.java).createWallet(sp.getString("user_id", ""), intent.extras.getString("readNameInput"), intent.extras.getString("readaddress"), "", "", nowtime, "", sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
            register.enqueue(object : Callback<Common> {
                override fun onResponse(call: Call<Common>, response: Response<Common>) {
                    if (response.body()!!.code == 1) {
                        val getHash = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java)
                        val keyAddressMap = HashMap<String, KeyAddressBean>()
                        val keyAddressBeanInput = KeyAddressBean()
                        if (getHash.size > 0) {
                            keyAddressBeanInput.name = intent.extras.getString("readFileName")
                            keyAddressBeanInput.address = intent.extras.getString("readaddress")
                            keyAddressBeanInput.keystore = intent.extras.getString("readFileInput")
                            keyAddressBeanInput.mnemonic = intent.extras.getString("readMnemonic")
                            keyAddressBeanInput.walletName = intent.extras.getString("walletName")
                            keyAddressBeanInput.userPrivatelyKey = intent.extras.getString("UserPwd")
                            keyAddressBeanInput.userHead = sp.getString("useravatar", "")
                            keyAddressBeanInput.userId = sp.getString("user_id", "")
                            keyAddressBeanInput.userName = sp.getString("user_name", "")
                            keyAddressMap[intent.extras.getString("walletName") + sp.getString("user_id", "")] = keyAddressBeanInput
                            for (i in getHash) {
                                keyAddressMap[i.key] = i.value
                            }
                            KeyUtlis.HaveKey(keyAddressMap)

                        } else {
                            keyAddressBeanInput.name = intent.extras.getString("readFileName")
                            keyAddressBeanInput.address = intent.extras.getString("readaddress")
                            keyAddressBeanInput.keystore = intent.extras.getString("readFileInput")
                            keyAddressBeanInput.mnemonic = intent.extras.getString("readMnemonic")
                            keyAddressBeanInput.walletName = intent.extras.getString("walletName")
                            keyAddressBeanInput.userHead = sp.getString("useravatar", "")
                            keyAddressBeanInput.userId = sp.getString("user_id", "")
                            keyAddressBeanInput.userName = sp.getString("user_name", "")
                            keyAddressBeanInput.userPrivatelyKey = intent.extras.getString("UserPwd")
                            keyAddressMap[intent.extras.getString("walletName") + sp.getString("user_id", "")] = keyAddressBeanInput
                            KeyUtlis.HaveKey(keyAddressMap)
                        }
                        MyApplication.keyAddressBeans = keyAddressBeanInput
                        startActivity(Intent(this@BackupsWalletActivity, MainActivity::class.java))
                        MyApplication.userPhone == ""
                        finish()
                    } else {
                        Toast.makeText(this@BackupsWalletActivity, R.string.KeyError, Toast.LENGTH_SHORT).show()
                        val getHash = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java)
                        getHash.remove(intent.extras.getString("walletName") + sp.getString("user_id", ""))
                    }
                }

                override fun onFailure(call: Call<Common>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@BackupsWalletActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    val getHash = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java)
                    getHash.remove(intent.extras.getString("walletName") + sp.getString("user_id", ""))
                }
            })
        } catch (e: Exception) {
            val getHash = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java)
            getHash.remove(intent.extras.getString("walletName") + sp.getString("user_id", ""))
        }


    }

}
