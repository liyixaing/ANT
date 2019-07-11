package com.example.administrator.mode.Activity

import android.view.View
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import kotlinx.android.synthetic.main.tit.*

class TermsOfServiceActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_terms_of_service
    }

    override fun init() {
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setText(R.string.termsOfService)
    }

}
