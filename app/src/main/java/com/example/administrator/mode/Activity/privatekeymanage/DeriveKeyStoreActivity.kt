package com.example.administrator.mode.Activity.privatekeymanage

import android.view.View
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import com.example.administrator.mode.Utlis.VerifyUtlis
import com.example.administrator.mode.app.MyApplication
import kotlinx.android.synthetic.main.activity_derive_key_store.*
import kotlinx.android.synthetic.main.tit.*
class DeriveKeyStoreActivity : BaseActivity() {

    override fun getContentViewId(): Int {
        return R.layout.activity_derive_key_store
    }

    override fun init() {
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.text=getString(R.string.deriveKeystore)

 /*       var credentials = Credentials.create(MyApplication.keyAddressBeans.name)
        var web3j : Web3j;*/

        copyKeyStore.text=MyApplication.keyAddressBeans.keystore
        copyKeystore.setOnClickListener(object :ClickUtlis(){
            override fun onMultiClick(v: View?) {
                VerifyUtlis.copy(this@DeriveKeyStoreActivity, copyKeyStore.text.toString().trim())
            }
        })
    }
}
