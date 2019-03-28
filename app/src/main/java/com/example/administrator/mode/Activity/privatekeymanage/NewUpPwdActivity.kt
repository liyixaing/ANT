package com.example.administrator.mode.Activity.privatekeymanage

import android.view.View
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Pojo.KeyAddressBean
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import com.example.administrator.mode.Utlis.SharedPreferencesUtil
import com.example.administrator.mode.app.MyApplication
import kotlinx.android.synthetic.main.activity_new_up_pwd.*

class NewUpPwdActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_new_up_pwd
    }

    override fun init() {
        newUpPwdTit.setOnClickListener(object :ClickUtlis(){
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        newUpPwdTit1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (thisPwd.text.toString().trim() != MyApplication.keyAddressBeans.userPrivatelyKey) {
                    toast(getString(R.string.pwdInputError))
                    return
                }
                if (newPwd.text.toString().trim() != newLoginPwd.text.toString()) {
                    toast(getString(R.string.UserfindPwd_pwdError))
                    return
                }
                if (newPwd.text.toString().trim().length < 8) {
                    toast(getString(R.string.Register_userPaymentpwdError))
                    return
                }
                val getHash = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java)
                MyApplication.keyAddressBeans.userPrivatelyKey = newPwd.text.toString().trim()
                getHash.remove(MyApplication.keyAddressBeans.walletName + MyApplication.keyAddressBeans.userId)
                getHash[MyApplication.keyAddressBeans.walletName + MyApplication.keyAddressBeans.userId] = MyApplication.keyAddressBeans
                SharedPreferencesUtil.putHashMapData("keyAddress", getHash)
                toast(getString(R.string.Property_suess))
                finish()
            }
        })
    }
}
