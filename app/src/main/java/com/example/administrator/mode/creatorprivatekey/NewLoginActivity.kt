package com.example.administrator.mode.creatorprivatekey

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.bumptech.glide.Glide
import com.example.administrator.mode.Activity.*
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.KeyAddressBean
import com.example.administrator.mode.Pojo.Loginturn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.app.MyApplication
import com.netease.nimlib.sdk.NIMClient
import com.netease.nimlib.sdk.RequestCallback
import com.netease.nimlib.sdk.auth.AuthService
import com.netease.nimlib.sdk.auth.LoginInfo
import kotlinx.android.synthetic.main.activity_new_login.*
import org.web3j.crypto.Credentials
import org.web3j.crypto.ECKeyPair
import org.web3j.utils.Numeric
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewLoginActivity : BaseActivity() {

    private var baseadaptr: FenAdater<*>? = null
    var list = arrayListOf<KeyAddressBean>()
    var items = arrayOf("中文", "English")
    var addr = ""
    var userPrivatelyKey = ""

    override fun getContentViewId(): Int {
        return R.layout.activity_new_login
    }

    @SuppressLint("SetTextI18n")
    override fun init() {
        initData()
        newLoginList.onItemClickListener = AdapterView.OnItemClickListener { _, _, p2, _ ->
            addr = list[p2].address.toString()
            userPrivatelyKey = list[p2].name
            Log.i("dawd", list[p2].userPrivatelyKey)
            SharedPreferencesUtil.putData("userPrivatelyKey", userPrivatelyKey)
            SharedPreferencesUtil.putData("userPrivatelyKeyInput", list[p2].userPrivatelyKey)
            MyApplication.keyAddressBeans = list[p2]
            newLoginInput()
        }
        newLoginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        jumpReadAgreement.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(this@NewLoginActivity, LeadKeyActivity::class.java)
                intent.putExtra("leadKeyUrl", "11")
                startActivity(intent)
            }
        })
        newLoginPwdAndAccount.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@NewLoginActivity, LoginActivity::class.java))
                finish()
            }
        })
        newLoginlanguage.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val alertDialog = AlertDialog.Builder(this@NewLoginActivity)
                        .setTitle(PreferencesUtil.get("Pleaselanguage", ""))
                        .setItems(items) { _, i ->
                            //添加列表
                            if (items[i] == "中文") {
                                val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                                val edit = sp.edit()
                                edit.putString("language", "zh")
                                PreferencesUtil.put("language", "zh")
                                edit.commit()
                                start()
                            } else if (items[i] == "English") {
                                val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                                val edit = sp.edit()
                                edit.putString("language", "en")
                                edit.commit()
                                PreferencesUtil.put("language", "en")
                                start()
                            }
                        }
                        .create()
                alertDialog.show()
            }
        })
    }

    private fun initData() {
        if (KeyUtlis.isHaveKey()) {
            val getHash = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java)
            for (i in getHash) {
                list.add(i.value)
            }
        }
        baseadaptr = object : FenAdater<KeyAddressBean>(this@NewLoginActivity, list, R.layout.new_login_titlayout) {
            override fun convert(holder: ViewHolder?, item: KeyAddressBean?) {
                holder!!.setText(R.id.userNewName, item!!.userName)
                holder.setText(R.id.userNewId, item.userId)
                holder.setText(R.id.userNewIdAddress, item.address.substring(0, 8) + "..." + item.address.substring(item.address.length - 8, item.address.length))
                if (item.userHead != null && item.userHead != "") {
                    Log.i("", "11" + item.userHead)
                    holder.setImageByUrl(R.id.userNewLogin, item.userHead)
                }
            }
        }
        newLoginList.adapter = baseadaptr
    }

    fun newLoginInput() {
        try {
            val keyPair = ECKeyPair.create(Numeric.hexStringToByteArray(userPrivatelyKey))
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(MoneyService::class.java).walletLogin(nowtime, MessageSignUtils.Sign(Credentials.create(keyPair), nowtime.toString()), nowtime, "", SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
            register.enqueue(object : Callback<Loginturn> {
                override fun onResponse(call: Call<Loginturn>, response: Response<Loginturn>) {
                    if (response.body()!!.code == 1) {
                        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                        val edit = sp.edit()
                        edit.putString("user_id", response.body()!!.data!!.id.toString())
                        edit.putString("user_token", response.body()!!.data!!.token)
                        Log.e("登录时的token", response.body()!!.data!!.token)
                        edit.putString("user_phone", response.body()!!.data!!.phone)
                        edit.putString("oldwcn", response.body()!!.data!!.worldCode)
                        edit.putString("user_name", response.body()!!.data!!.username)
                        edit.putBoolean("first", false)
                        edit.putString("useravatar", response.body()!!.data!!.avatar)
                        edit.commit()
                        startActivity(Intent(this@NewLoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                    }
                }

                override fun onFailure(call: Call<Loginturn>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@NewLoginActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {

        }
    }

    fun start() {
        val intent = Intent(this, NewLoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        StatService.onPageEnd(this@NewLoginActivity, "NewLogin.View")
        startActivity(intent)
    }

    fun doLogin() {
        val info = LoginInfo("123123", "11111111") // config...
        val callback = object : RequestCallback<LoginInfo> {
            override fun onSuccess(loginInfo: LoginInfo) {
                MyApplication.loginInfo = loginInfo
                startActivity(Intent(this@NewLoginActivity, MainActivity::class.java))
                finish()
            }

            override fun onFailed(i: Int) {
                Log.i("wdada", i.toString())
            }

            override fun onException(throwable: Throwable) {
                Log.i("wdada", throwable.message)
            }
            // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
        }
        NIMClient.getService(AuthService::class.java).login(info)
                .setCallback(callback)
    }
}
