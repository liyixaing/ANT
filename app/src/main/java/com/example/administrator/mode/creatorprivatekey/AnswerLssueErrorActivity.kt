package com.example.administrator.mode.creatorprivatekey

import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import kotlinx.android.synthetic.main.activity_answer_lssue_error.*
import kotlinx.android.synthetic.main.tit.*

class AnswerLssueErrorActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_answer_lssue_error
    }

    override fun init() {
        tit_name.setText(R.string.CreateAPrivateKeyTit)
        ansuerError.setOnClickListener { finish() }
        tit_iv.setOnClickListener { finish() }
    }
}
