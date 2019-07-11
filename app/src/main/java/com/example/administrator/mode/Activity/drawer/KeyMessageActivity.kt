package com.example.administrator.mode.Activity.drawer
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.app.MyApplication
import kotlinx.android.synthetic.main.activity_key_message.*
import kotlinx.android.synthetic.main.tit.*

class KeyMessageActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_key_message
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener { finish() }
        tit_name.text=getString(R.string.Resettheprivatekey)
        xzceeqw.setOnClickListener {
            MyApplication.isaaa=""
            finish() }
        zxczrfd.setOnClickListener {
            MyApplication.isaaa="dd"
            finish() }
    }
}
