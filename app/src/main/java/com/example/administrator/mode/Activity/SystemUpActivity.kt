package com.example.administrator.mode.Activity

import android.annotation.SuppressLint
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.DateUtils
import kotlinx.android.synthetic.main.activity_steam_up.*

class SystemUpActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_steam_up
    }

    @SuppressLint("SetTextI18n")
    override fun init() {
        super.init()
        systemOverTime.text = "系统预计开放时间"+ DateUtils.timet(intent.extras.getString("openingtime"))
    }
}
