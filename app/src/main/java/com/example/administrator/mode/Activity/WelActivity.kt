package com.example.administrator.mode.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.drawer.VersionsActivity
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.CommonInt
import com.example.administrator.mode.Pojo.Versionsturn
import com.example.administrator.mode.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.administrator.mode.Pojo.SystemUPTurn
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.creatorprivatekey.ReadAgreementActivity
import java.util.*


class WelActivity : BaseActivity() {
    private var recLen = 2
    var timer = Timer()
    override fun getContentViewId(): Int {
        return R.layout.activity_wel
    }

    override fun onStart() {
        super.onStart()

    }

    override fun init() {
        super.init()
        val task = object : TimerTask() {
            @SuppressLint("ResourceType")
            override fun run() {
                runOnUiThread {
                    if (recLen == 0) {
                        StatService.setDebugOn(true)
                        StatService.start(this@WelActivity)
                        isSystem()
                    }
                    recLen--
                }
            }
        }
        timer.schedule(task, 1000, 1000)
        recLen = 2
    }

    private fun isSystem() {
        try {
            val retrofit = Retrofit_manager.getInstance().userlogin
            val transfer = retrofit.create(MoneyService::class.java).serverstate()
            transfer.enqueue(object : Callback<SystemUPTurn> {
                override fun onResponse(call: Call<SystemUPTurn>, response: Response<SystemUPTurn>) {
                    try {
                        if (response.body()!!.code == 1) {
                            if (response.body()!!.data.state == "stop") {
                                val intent = Intent(this@WelActivity, SystemUpActivity::class.java)
                                intent.putExtra("openingtime", response.body()!!.data.openingtime.toString())
                                intent.putExtra("openinginfo", response.body()!!.data.info.toString())
                                startActivity(intent)
                                finish()
                            } else {
                                load()
                            }
                        }
                    } catch (e: Exception) {
                        finish()
                    }
                }

                override fun onFailure(call: Call<SystemUPTurn>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@WelActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            finish()
        }
    }

    fun load() {
        //判断是否有权限
        val isAllGranted = checkPermissionAllGranted(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE)
        )
        // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
        ActivityCompat.requestPermissions(
                this@WelActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE), VerifyUtlis.MY_PERMISSION_REQUEST_CODE
        )
    }

    //判断是否有全部权限
    private fun checkPermissionAllGranted(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false
            }
        }
        return true
    }

    /**
     * 第 3 步: 申请权限结果返回处理
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == VerifyUtlis.MY_PERMISSION_REQUEST_CODE) {
            var isAllGranted = true

            // 判断是否所有的权限都已经授予了
            for (grant in grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false
                    break
                }
            }
            //如果有权限的话
            if (isAllGranted) {
                getVersions()
            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
                openAppDetails()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
            }
        }
    }

    fun getVersions() = try {
        val nowtime = DateUtils.getdata()
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val edit = sp.edit()
        val transfer = retrofit.create(MoneyService::class.java).getVersion("0", "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(nowtime))
        transfer.enqueue(object : Callback<Versionsturn> {
            override fun onResponse(call: Call<Versionsturn>, response: Response<Versionsturn>) {
                try {
                    if (response.body()!!.code == 1) {
                        edit.putString("versionwebUrl", response.body()!!.data!!.webUrl)
                        val versionsInput = VerifyUtlis.getAppVersionName(this@WelActivity)
                        Log.i("dadsa", response.body()!!.data!!.versionNumber.toString())
                        if (versionsInput != response.body()!!.data!!.versionNumber) {
                            if (response.body()!!.data!!.status == 1) {
                                val intent = Intent(this@WelActivity, VersionsActivity::class.java)
                                intent.putExtra("versions", "wel")
                                startActivity(intent)
                                finish()
                                return
                            } else {
                                edit.putBoolean("versions11", true)
                                abnormal22(this@WelActivity)
                            }
                        } else {
                            abnormal22(this@WelActivity)
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@WelActivity, R.string.System_error, Toast.LENGTH_SHORT).show()
                    finish()
                } finally {
                    edit.commit()
                }

            }

            override fun onFailure(call: Call<Versionsturn>, t: Throwable) {
                if (t is DataResultException) {
                    Toast.makeText(this@WelActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                } else {

                }
            }
        })
    } catch (e: Exception) {
        finish()
    }

    /**
     * 打开 APP 的详情设置
     */
    private fun openAppDetails() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.Welcome_hint)
        builder.setPositiveButton(R.string.Welcome_authority) { _, _ ->
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.data = Uri.parse("package:$packageName")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            startActivity(intent)
        }
        builder.setNegativeButton(R.string.Welcome_error, null)
        builder.show()
    }
}
