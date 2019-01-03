package com.example.administrator.mode.Activity.drawer

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import kotlinx.android.synthetic.main.activity_security.*
import kotlinx.android.synthetic.main.tit.*

class SecurityActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_security
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setText(R.string.Property_tit)
        security_youxiang.setOnClickListener(object :ClickUtlis(){
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        security_pwdup.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@SecurityActivity, SecurityLoginUpActivity::class.java))
            }
        })
        security_payup.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@SecurityActivity, SecurityPayUpActivity::class.java))
            }
        })
        security_phoneup.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@SecurityActivity, SecurityPhoneUpActivity::class.java))
            }
        })
        security_findpay.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@SecurityActivity, SecurityFindPaypwdActivity::class.java))
            }
        })
    }

    fun onchid() {
        Toast.makeText(this,  R.string.jianshe, Toast.LENGTH_SHORT).show()
    }
}
