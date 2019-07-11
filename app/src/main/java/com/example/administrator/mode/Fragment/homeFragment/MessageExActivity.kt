package com.example.administrator.mode.Fragment.homeFragment

import android.view.View
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import kotlinx.android.synthetic.main.activity_message_ex.*
import kotlinx.android.synthetic.main.tit.*

class MessageExActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_message_ex
    }
    var xx = ""
    override fun init() {
        super.init()
        xx = intent.extras.getString("messagess")
        tit_iv.setOnClickListener(object :ClickUtlis(){
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setText(R.string.Dpos_tit)
        if (xx.equals("dpos"))
        {
            tit_name.setText(R.string.Dpos_tit)
            typeExplain.setText(R.string.Dpos_explain)
            explainMagess.setText(R.string.Dpos_meagess)
        }
        if (xx.equals("pos"))
        {
            tit_name.setText(R.string.Post_tit)
            typeExplain.setText(R.string.Post_explain)
            explainMagess.setText(R.string.Pos_meagess)
        }
        if (xx.equals("node"))
        {
            typeExplain.setText(R.string.Node_explain)
            tit_name.setText(R.string.Node_tit)
            explainMagess.setText(R.string.Node_meagess)
        }
    }

}

