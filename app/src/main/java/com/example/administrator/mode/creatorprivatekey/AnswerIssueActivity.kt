package com.example.administrator.mode.creatorprivatekey

import android.content.Intent
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import kotlinx.android.synthetic.main.activity_answer_issue.*
import kotlinx.android.synthetic.main.tit.*

class AnswerIssueActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_answer_issue
    }

    override fun init() {
        initTit()
        initClick()
    }

    private fun initClick() {
        AgreementNext.setOnClickListener {
            if (success1.isChecked
                    && success2.isChecked
                    && success3.isChecked
                    && success4.isChecked
                    && success5.isChecked
                    && !error6.isChecked
                    && !error5.isChecked
                    && !error4.isChecked
                    && !error3.isChecked
                    && !error2.isChecked
                    && !error1.isChecked) {
                startActivity(Intent(this, AnswerLssueRightActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, AnswerLssueErrorActivity::class.java))
            }
        }
    }

    private fun initTit() {
        tit_iv.setOnClickListener { finish() }
        tit_name.setText(R.string.SafetyClass)
    }
}
