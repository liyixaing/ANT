package com.example.administrator.mode.Fragment.homeFragment

import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import kotlinx.android.synthetic.main.tit.*

class CrowdPreheatActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_crowd_preheat
    }

    override fun init() {
     /*   crowdfunding_layout*/
        tit_iv.setOnClickListener { finish() }
        /*tit_name.setText(R.string.yyy)*/
    }
}
