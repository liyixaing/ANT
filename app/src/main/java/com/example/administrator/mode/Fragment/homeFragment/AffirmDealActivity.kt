package com.example.administrator.mode.Fragment.homeFragment

import android.content.Intent
import android.view.View
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.drawer.SecurityFindPaypwdActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import kotlinx.android.synthetic.main.activity_affirm_deal.*
import kotlinx.android.synthetic.main.tit.*

class AffirmDealActivity : BaseActivity() {

    override fun getContentViewId(): Int {
        return R.layout.activity_affirm_deal
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setText(R.string.AffirmDeal_tit)
        affirm_ok.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val payDialog = PayDialog(this@AffirmDealActivity)
                payDialog.setAffirmDealActivity(this@AffirmDealActivity)
                payDialog.show()
            }
        })
        affirm_no.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@AffirmDealActivity, TransferAccountsActivity::class.java))
                finish()
            }
        })
        affirmdeal_user.setText(intent.extras.getString("toPhone"))
        affirmdeal_username.setText(intent.extras.getString("toUsername"))
        affirmdeal_mon.setText(intent.extras.getString("amount"))
        affirmdeal_service.setText(intent.extras.getString("fees"))
        affirmdeal_anticipation.setText(R.string.ciri)
        affirmdeal_practical.setText("  -  " + intent.extras.getString("actualAmount") + "   ")
    }

    fun tiaozhuan() {
        startActivity(Intent(this@AffirmDealActivity, SecurityFindPaypwdActivity::class.java))
    }

    //自定义支付密码框回掉
    fun OnInputPayPasswrodSuccess(inputPwd: String) {
        var tran = intent.extras.getString("transferid")
        val intent = Intent(this@AffirmDealActivity, ParticularsActivity::class.java)
        intent.putExtra("transferid", tran);
        intent.putExtra("paypwd", inputPwd);
        startActivity(intent);
        finish()
    }
}
