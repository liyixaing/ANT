package com.example.administrator.mode.Fragment


import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.eightbitlab.rxbus.Bus
import com.example.administrator.mode.Activity.*
import com.example.administrator.mode.Activity.drawer.AboutWeActivity
import com.example.administrator.mode.Activity.drawer.VersionsActivity
import com.example.administrator.mode.Activity.privatekeymanage.PrivateKeyManageActivity
import com.example.administrator.mode.Fragment.my.MyCenterActivity
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.KeyAddressBean
import com.example.administrator.mode.Pojo.QueryMerchantApplyStatus
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.app.MyApplication
import com.example.administrator.mode.creatorprivatekey.NewLoginActivity
import com.example.administrator.mode.event.UserHeadEvent
import kotlinx.android.synthetic.main.fragment_d.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var cacheSize: String = ""

class D_Fragment : Fragment() {
    override fun onStart() {
        super.onStart()
        initShot()
    }

    var items = arrayOf("中文", "English")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_d, container, false)
    }

    override fun onResume() {
        cacheSize = GetFileSize.FormetFileSize(GetFileSize.getFileSize(activity!!.cacheDir))
        tv_cache.setText(cacheSize)
        super.onResume()
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val edit = sp.edit()
        cacheSize = GetFileSize.FormetFileSize(GetFileSize.getFileSize(activity!!.cacheDir))
        tv_cache.setText(cacheSize)
        if (MyApplication.isVip != "0") {
            isVip.visibility = View.VISIBLE
            eee.visibility = View.VISIBLE
            mainCrown.visibility = View.VISIBLE
        }
        if (MyApplication.isMerchant != "0") {
            isMerchant.text = "LV" + MyApplication.isMerchant
            isMerchant.visibility = View.VISIBLE
        }
        userNameD.text = sp.getString("user_name", "")
        phoneD.text = sp.getString("user_phone", "")
        idD.text = sp.getString("user_id", "")
        val s = sp.getBoolean("versions11", false)
        if (s) {

        } else {
            iv_ban.visibility = View.VISIBLE
        }
        if (MyApplication.keyAddressBeans.userHead != "" && MyApplication.keyAddressBeans.userHead != null) {

            Glide.with(activity!!).load(MyApplication.keyAddressBeans.userHead).apply(bitmapTransform(GlideCircleTransform(activity))).into(mainhead)
        } else {
            val resource = R.drawable.antdefault

            Glide.with(activity!!).load(resource).apply(bitmapTransform(GlideCircleTransform(activity))).into(mainhead)
        }

        Bus.observe<UserHeadEvent>().subscribe { t: UserHeadEvent ->
            kotlin.run {
                try {
                    if (t.isLong != "" && t.isLong != null) {
                        Glide.with(activity!!).load(t.isLong).apply(bitmapTransform(GlideCircleTransform(activity))).into(mainhead)
                    } else {
                    }
                } catch (e: Exception) {

                }
            }
        }

        mainhead.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                try {
                    val isAllGranted = checkPermissionAllGranted(
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE)
                    )
                    ActivityCompat.requestPermissions(
                            activity!!, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE), VerifyUtlis.MY_PERMISSION_REQUEST_CODE
                    )
                } catch (e: Exception) {
                    Toast.makeText(activity, R.string.System_in_hurry, Toast.LENGTH_SHORT).show()
                }
            }
        })

        re_anquan.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, PrivateKeyManageActivity::class.java))
            }
        })
        re_banben.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(activity, VersionsActivity::class.java)
                intent.putExtra("versions", "main")
                startActivity(intent)
            }
        })
        re_guanyu.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, AboutWeActivity::class.java))
            }
        })
        //清除缓存
        re_cache.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                DataCleanManager.cleanInternalCache(activity)
                cacheSize = GetFileSize.FormetFileSize(GetFileSize.getFileSize(activity!!.cacheDir))
                tv_cache.setText(cacheSize)
                Toast.makeText(activity, R.string.Clean_up_the_cache1, Toast.LENGTH_SHORT).show()
            }
        })
        bu_tuichu.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val alertDialog2 = AlertDialog.Builder(activity)
                        .setTitle(PreferencesUtil.get("hint", ""))
                        .setMessage(PreferencesUtil.get("Whether", ""))
                        .setPositiveButton(PreferencesUtil.get("confirm", "")) { _, _ ->
                            //添加"Yes"按钮
                            edit.putBoolean("first", true)
                            edit.putString("user_id", "")
                            edit.putString("user_token", "")
                            edit.putString("user_phone", "")
                            edit.putString("user_name", "")
                            edit.putString("useravatar", "")
                            edit.commit()
                            MyApplication.keyAddressBeans = KeyAddressBean()
                            val siz = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java).size
                            if (siz > 0) {
                                startActivity(Intent(context, NewLoginActivity::class.java))
                                activity!!.finish()
                            } else {
                                startActivity(Intent(context, LoginActivity::class.java))
                                activity!!.finish()
                            }
                        }
                        .setNegativeButton(PreferencesUtil.get("cancel", "")) { _, _
                            ->
                        }
                        .create()
                alertDialog2.show()
            }
        })

        re_banben1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val alertDialog = AlertDialog.Builder(activity)
                        .setTitle(PreferencesUtil.get("Pleaselanguage", ""))
                        .setItems(items) { _, i ->
                            //添加列表
                            if (items[i] == "中文") {
                                edit.putString("language", "zh")
                                PreferencesUtil.put("language", "zh")
                                edit.commit()
                                start()
                            } else if (items[i] == "English") {
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
        czxcased.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val mAlertViewExt: AlertView
                //拓展窗口
                val extView = LayoutInflater.from(activity).inflate(R.layout.alertext_form, null) as ViewGroup
                val etName = extView.findViewById(R.id.etName) as EditText
                mAlertViewExt = AlertView(getString(R.string.nameOfWallet), "", getString(R.string.Welcome_error), null, arrayOf(getString(R.string.Register_ok)), activity, AlertView.Style.Alert,
                        OnItemClickListener { _, position ->
                            val etNameStr = etName.text.toString()
                            if (TextUtils.isEmpty(etNameStr)) {
                                return@OnItemClickListener
                            }
                            if (position == 0) {
                                if (etNameStr.isEmpty()) {
                                    Toast.makeText(activity, R.string.nameup, Toast.LENGTH_SHORT).show()
                                }
                                if (etNameStr.trim().length > 6) {
                                    Toast.makeText(activity, R.string.nameuptwo, Toast.LENGTH_SHORT).show()
                                    return@OnItemClickListener
                                }
                                try {
                                    val nowtime = DateUtils.getdata()
                                    val retrofit = Retrofit_manager.getInstance().userlogin
                                    val register = retrofit.create(GitHubService::class.java!!).userup(sp.getString("user_id", ""), "", etNameStr, sp.getString("user_token", ""), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
                                    register.enqueue(object : Callback<Common> {
                                        override fun onResponse(call: Call<Common>, response: Response<Common>) {
                                            try {
                                                if (response.body()!!.code == 1) {
                                                    userNameD.text = etNameStr
                                                    Toast.makeText(activity, R.string.Property_suess, Toast.LENGTH_SHORT).show()
                                                }
                                            } catch (e: Exception) {
                                            } finally {
                                            }
                                        }

                                        override fun onFailure(call: Call<Common>, t: Throwable) {
                                            if (t is DataResultException) {
                                                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    })
                                } catch (e: Exception) {

                                }
                                /*  override fun onStart() {
                                      super.onStart()
                                      val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
                                      val intent = Intent(activity, ANTStoreActivity::class.java)
                                      intent.putExtra("url", "http://mall.fcsap.com/?user_id=" + sp.getString("user_id","") + "&user_token=" + Encryption.generateFakeTokenToShop(sp.getString("user_token", "")) + "&mall_key=b64ab4b8124e2c5d43d52d9c05a6f992")
                                      startActivity(intent)
                                      activity!!.finish()
                                  }*/
                            }
                        })
                mAlertViewExt.show()
                mAlertViewExt.addExtView(extView)
            }
        })
    }

    private fun initShot() {
        val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val nowtime = DateUtils.getdata()
        val retrofit = Retrofit_manager.getInstance().userlogin
        val login = retrofit.create(GitHubService::class.java).queryMerchantApplyStatus(sp.getString("user_id", ""), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
        login.enqueue(object : Callback<QueryMerchantApplyStatus> {
            override fun onResponse(call: Call<QueryMerchantApplyStatus>, response: Response<QueryMerchantApplyStatus>) {
                if (response.body()!!.code == 1) {
                    rubSelect.text = response.body()!!.data.approvalDesc
                    if (response.body()!!.data.approvalStatus != 0) {
                        re_shiming.setOnClickListener(object : ClickUtlis() {
                            override fun onMultiClick(v: View?) {
                                val intent = Intent(activity, MyCenterActivity::class.java)
                                intent.putExtra("approvalDesc", response.body()!!.data.approvalStatus.toString())
                                startActivity(intent)
                            }
                        })
                    }
                } else {
                    Toast.makeText(activity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QueryMerchantApplyStatus>, t: Throwable) {
                if (t is DataResultException) {
                    Toast.makeText(activity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun start() {
        val intent = Intent(activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        StatService.onPageEnd(activity, "LoginModule.LoginView")
        startActivity(intent)
    }

    private fun checkPermissionAllGranted(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(activity!!, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}
