package com.example.administrator.mode.creatorprivatekey

import android.content.Intent
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import kotlinx.android.synthetic.main.activity_answer_lssue_right.*
import kotlinx.android.synthetic.main.tit.*

class AnswerLssueRightActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_answer_lssue_right
    }

    override fun init() {
        tit_iv.setOnClickListener {
            startActivity(Intent(this, CreatorPrivateKeyActivity::class.java))
            finish()
        }
        tit_name.setText(R.string.CreateAPrivateKeyTit)
        startCreatorKey.setOnClickListener {
            startActivity(Intent(this, CreatorPrivateKeyActivity::class.java))
            finish()
        }
    }
}
