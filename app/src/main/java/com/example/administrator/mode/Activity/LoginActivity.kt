package com.example.administrator.mode.Activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Pojo.Loginturn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap


class LoginActivity : BaseActivity() {
    var items = arrayOf("中文", "English")
    override fun getContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun onStart() {
        super.onStart()
        StatService.onPageStart(this@LoginActivity, "LoginModule.LoginView")
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        login_state2.text = sp.getString("user_crown", "86")
        if (sp.getString("language", "zh").equals("zh")) {
            loginconstate.text = sp.getString("user_Nation_CN", "中国") + " +"
        } else if (sp.getString("language", "").equals("en")) {
            loginconstate.text = sp.getString("user_Nation_EN", "China") + " +"
        }
    }

    override fun init() {
        super.init()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val edit = sp.edit()
        loginconstate.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@LoginActivity, CountryActivity::class.java))
            }
        })
        logininput.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (user_name.text.toString().trim() == "" || user_pwd.text.toString().trim() == "") {
                    Toast.makeText(this@LoginActivity, R.string.Login_userpwdError, Toast.LENGTH_SHORT).show()
                    return
                }
                if (user_name.text.toString().trim() == "" || user_name.text.toString().trim() == null) {
                    Toast.makeText(this@LoginActivity, R.string.Register_userNameerror, Toast.LENGTH_SHORT).show()
                    return
                }
                if (user_pwd.text.toString().trim() == "" || user_pwd.text.toString().trim() == null) {
                    Toast.makeText(this@LoginActivity, R.string.Login_pwdError, Toast.LENGTH_SHORT).show()
                    return
                }
                val nowtime = DateUtils.getdata()
                val retrofit = Retrofit_manager.getInstance().userlogin
                val login = retrofit.create(GitHubService::class.java!!).login(user_name.text.toString().trim(), user_pwd.text.toString().trim(), VerifyUtlis.getIMEI(this@LoginActivity), login_state2.text.toString().trim(), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(nowtime))
                login.enqueue(object : Callback<Loginturn> {
                    override fun onResponse(call: Call<Loginturn>, response: Response<Loginturn>) {
                        try {
                            if (response.body()!!.code == 1) {
                                edit.putString("user_id", response.body()!!.data!!.id.toString())
                                edit.putString("user_token", response.body()!!.data!!.token)
                                edit.putString("user_phone", response.body()!!.data!!.phone)
                                edit.putString("oldwcn", login_state2.text.toString().trim())
                                edit.putString("user_name", response.body()!!.data!!.username)
                                edit.putBoolean("first", false)
                                edit.putString("useravatar", response.body()!!.data!!.avatar)
                                edit.commit()
                                StatService.onEvent(this@LoginActivity, "LoginView.LoginSuccess", "xms", 1)
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.putExtra("upgrade", "aaa")
                                startActivity(intent)
                                StatService.onPageEnd(this@LoginActivity, "LoginModule.LoginView")
                                finish()
                            } else {
                                Toast.makeText(this@LoginActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            abnormal(this@LoginActivity)
                        }
                    }

                    override fun onFailure(call: Call<Loginturn>, t: Throwable) {
                        if (t is DataResultException) {
                            Toast.makeText(this@LoginActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                            val attributes = HashMap<String, String>()
                            attributes["UserName"] = user_name.text.toString().trim()
                            attributes["Message"] = t.message.toString()
                            StatService.onEvent(this@LoginActivity, "LoginView.LoginFailed", "xms", 1, attributes)
                            /*  Toast.makeText(this@LoginActivity, R.string.Login_error, Toast.LENGTH_SHORT).show()*/
                        }
                    }
                })

            }
        })
        loginregister.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                finish()
                StatService.onPageEnd(this@LoginActivity, "LoginModule.LoginView")
            }
        })
        findpwd.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@LoginActivity, FindpwdActivity::class.java))
                StatService.onPageEnd(this@LoginActivity, "LoginModule.LoginView")
                finish()
            }
        })
        cutlanguage.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val alertDialog = AlertDialog.Builder(this@LoginActivity)
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

    fun start() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        StatService.onPageEnd(this@LoginActivity, "LoginModule.LoginView")
        startActivity(intent)
    }
}

