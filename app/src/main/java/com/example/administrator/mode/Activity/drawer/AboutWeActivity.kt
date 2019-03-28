package com.example.administrator.mode.Activity.drawer

import android.view.View
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import kotlinx.android.synthetic.main.tit.*

class AboutWeActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_about_we
    }

    override fun init() {
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.text = getString(R.string.Home_guanyuwo)
    }
}
