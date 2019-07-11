package com.example.administrator.mode.Activity.privatekeymanage

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import com.example.administrator.mode.Utlis.GlideCircleTransform
import com.example.administrator.mode.Utlis.VerifyUtlis
import com.example.administrator.mode.app.MyApplication
import kotlinx.android.synthetic.main.activity_private_key_manage.*
import kotlinx.android.synthetic.main.tit.*
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.administrator.mode.Fragment.homeFragment.PayDialog
import com.example.administrator.mode.Pojo.KeyAddressBean
import com.example.administrator.mode.Utlis.SharedPreferencesUtil


class PrivateKeyManageActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_private_key_manage
    }

    override fun init() {
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        if (MyApplication.keyAddressBeans.mnemonic == "undefined" || MyApplication.keyAddressBeans.mnemonic == "" || MyApplication.keyAddressBeans.mnemonic == null) {
            deriveDSSCard.visibility = View.GONE
        }
        tit_name.text = getString(R.string.Home_anq)
        WalletName.text = MyApplication.keyAddressBeans.walletName.split(MyApplication.keyAddressBeans.userId)[0]
        WalletPrivateKey.text = MyApplication.keyAddressBeans.address.substring(0, 8) + "..." + MyApplication.keyAddressBeans.address.substring(MyApplication.keyAddressBeans.address.length - 8, MyApplication.keyAddressBeans.address.length)
        if (sp.getString("useravatar", "") != "") {
            Glide.with(this).load(sp.getString("useravatar", "")).apply(bitmapTransform( GlideCircleTransform(this))).into(manageHead)
        } else {
            val resource = R.drawable.antdefault
            Glide.with(this).load(resource).apply(bitmapTransform( GlideCircleTransform(this))).into(manageHead)
        }
        derivePrivateKey.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val payDialog = PayDialog(this@PrivateKeyManageActivity)
                payDialog.setPrivateKeyManageActivity(this@PrivateKeyManageActivity, getString(R.string.ExportThePrivateKey))
                payDialog.show()
            }
        })
        deriveDSSCard.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val payDialog = PayDialog(this@PrivateKeyManageActivity)
                payDialog.setPrivateKeyManageActivity(this@PrivateKeyManageActivity, getString(R.string.deriveDSSCard))
                payDialog.show()
            }
        })
        deriveKeystore.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val payDialog = PayDialog(this@PrivateKeyManageActivity)
                payDialog.setPrivateKeyManageActivity(this@PrivateKeyManageActivity, getString(R.string.deriveKeystore))
                payDialog.show()
            }
        })

        upWalletPwd.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@PrivateKeyManageActivity, NewUpPwdActivity::class.java))
            }
        })
        upWalletName.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val mAlertViewExt: AlertView
                //拓展窗口
                val extView = LayoutInflater.from(this@PrivateKeyManageActivity).inflate(R.layout.alertext_form, null) as ViewGroup
                val etName = extView.findViewById(R.id.etName) as EditText
                mAlertViewExt = AlertView(getString(R.string.nameOfWallet), "", getString(R.string.Welcome_error), null, arrayOf(getString(R.string.Register_ok)), this@PrivateKeyManageActivity, AlertView.Style.Alert,
                        OnItemClickListener { _, position ->
                            val etNameStr = etName.text.toString()
                            if (TextUtils.isEmpty(etNameStr)) {
                                return@OnItemClickListener
                            }
                            if (position == 0) {
                                val getHash = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java)
                                getHash.remove(MyApplication.keyAddressBeans.walletName + MyApplication.keyAddressBeans.userId)
                                MyApplication.keyAddressBeans.walletName = etNameStr
                                getHash[etNameStr + MyApplication.keyAddressBeans.userId] = MyApplication.keyAddressBeans
                                SharedPreferencesUtil.putHashMapData("keyAddress", getHash)
                                toast(getString(R.string.Property_suess))
                                WalletName.text = etNameStr
                                /*  abnormal22(this@PrivateKeyManageActivity)*/
                            }
                        })
                mAlertViewExt.show()
                mAlertViewExt.addExtView(extView)
            }
        })

        deleteWallet.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val payDialog = PayDialog(this@PrivateKeyManageActivity)
                payDialog.setPrivateKeyManageActivity(this@PrivateKeyManageActivity, getString(R.string.ToDeleteTheWallet))
                payDialog.show()
            }
        })
    }

    fun OnInputCopySuccess(trim: String) {
        VerifyUtlis.copy(this@PrivateKeyManageActivity, trim)
    }

    fun OnremoveSuccess(str: String) {
        if (MyApplication.keyAddressBeans.userPrivatelyKey != str.split(",")[0]) {
            Toast.makeText(this, R.string.pwdInputError, Toast.LENGTH_SHORT).show()
            return
        }
        if (str.split(",")[1] == getString(R.string.ToDeleteTheWallet)) {
            val getHash = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java)
            getHash.remove(MyApplication.keyAddressBeans.walletName + MyApplication.keyAddressBeans.userId)
            SharedPreferencesUtil.putHashMapData("keyAddress", getHash)
            abnormal22(this)
        }
        if (str.split(",")[1] == getString(R.string.deriveKeystore)) {
            startActivity(Intent(this, DeriveKeyStoreActivity::class.java))
        }
        if (str.split(",")[1] == getString(R.string.ExportThePrivateKey)) {
            val payDialog = DeriveThePrivatekeyActivity(this@PrivateKeyManageActivity)
            payDialog.setPrivateKeyManageActivity(this@PrivateKeyManageActivity, MyApplication.keyAddressBeans.name, getString(R.string.ExportThePrivateKey))
            payDialog.show()
        }
        if (str.split(",")[1] == getString(R.string.deriveDSSCard)) {
            val payDialog = DeriveThePrivatekeyActivity(this@PrivateKeyManageActivity)
            payDialog.setPrivateKeyManageActivity(this@PrivateKeyManageActivity, MyApplication.keyAddressBeans.mnemonic, getString(R.string.deriveDSSCard))
            payDialog.show()
        }

    }
}
