package com.example.administrator.mode.Activity.drawer

import android.view.View
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import kotlinx.android.synthetic.main.activity_versions.*
import kotlinx.android.synthetic.main.tit.*
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Versionsturn
import com.example.administrator.mode.Utlis.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VersionsActivity : BaseActivity() {
    var versionsinput: String = ""
    var url = ""
    var xxxxxxxxx = ""
    override fun getContentViewId(): Int {
        return R.layout.activity_versions
    }

    override fun onStart() {
        super.onStart()
        versionsinput = intent.extras.getString("versions")
        if (versionsinput == "main") {

        }
        if (versionsinput == "wel") {
            versions_tit.visibility = View.GONE
            versions_no.visibility = View.GONE
        }
        loadversions()
    }

    fun loadversions() {

        try {
            if (PreferencesUtil.get("language", "").equals("zh")) {
                xxxxxxxxx = "正在下载....."

            } else if (PreferencesUtil.get("language", "").equals("en")) {
                xxxxxxxxx = "downloading....."
            }

            val nowtime = DateUtils.getdata()
            val retrofit = Retrofit_manager.getInstance().getUserlogin()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val transfer = retrofit.create(MoneyService::class.java!!).getVersion("0", "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(nowtime))
            transfer.enqueue(object : Callback<Versionsturn> {
                override fun onResponse(call: Call<Versionsturn>, response: Response<Versionsturn>) {
                    if (response.body()!!.code == 1) {
                        url = response.body()!!.data!!.versionUrl!!
                        affirmdeal_user.setText(response.body()!!.data!!.versionNumber)
                        affirmdeal_size.setText(response.body()!!.data!!.packetSize.toString() + response.body()!!.data!!.sizeUnit)
                        affirmdeal_date.setText(DateUtils.time(response.body()!!.data!!.createTime.toString()))
                        val no = response.body()!!.data!!.releaseNotes
                        Versions_lv.setText(VerifyUtlis.stringChang("\n" + no + "\n"))
                        val versionsInput = VerifyUtlis.getAppVersionName(this@VersionsActivity)
                        if (versionsInput != response.body()!!.data!!.versionNumber) {
                            Versns_new.visibility = View.VISIBLE
                        } else {
                            versions_ok.visibility = View.GONE
                            versions_no.setText(R.string.zuixing)
                        }
                        xx.setText("Versions" + versionsInput)
                    }
                }

                override fun onFailure(call: Call<Versionsturn>, t: Throwable) {
                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(this@VersionsActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this@VersionsActivity)
        }
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setText(R.string.Versions_update)
        versions_no.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        //立即跟新按钮
        versions_ok.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {

                try {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(url))
                    startActivity(intent)
                } catch (e: Exception) {
                    println("当前手机未安装浏览器")
                }

//                var progressDialog: ProgressDialog? = null
//                progressDialog = ProgressDialog(this@VersionsActivity)
//                progressDialog.setMessage(xxxxxxxxx)
//                progressDialog.isIndeterminate = true
//                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
//                progressDialog.setCancelable(true)
//                val downloadTask: DownloadTask = DownloadTask(this@VersionsActivity, progressDialog)
//                downloadTask.execute(url)
//                Log.e("xaiqoaing", url)
//                progressDialog.setOnCancelListener {
//                    downloadTask.cancel(true)
//                }
//                val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
//                //获得一个SharedPreferences编辑器
//                val edit = sp.edit()
//                edit.putBoolean("versions", false)
//                edit.commit()
            }
        })
    }
}
