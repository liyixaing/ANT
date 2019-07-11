package com.example.administrator.mode.creatorprivatekey

import android.content.Intent
import android.view.View
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import kotlinx.android.synthetic.main.activity_read_agreement.*
import kotlinx.android.synthetic.main.tit.*

class ReadAgreementActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_read_agreement
    }

    override fun init() {
        initTit()
        initClick()
    }

    private fun initClick() {
        AgreementNext.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@ReadAgreementActivity, AnswerIssueActivity::class.java))
                finish()
            }
        })
    }

    private fun initTit() {
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setText(R.string.PrivateKeyUsageProtoco)

    }
}
