package com.example.administrator.mode.Fragment.homeFragment

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.drawer.SecurityFindPaypwdActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import com.example.administrator.mode.app.MyApplication
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
                if (intent.extras.getString("rateInput") == "0.5") {
                    AlertView(getString(R.string.hint), getString(R.string.hint122) + "\n \n "+getString(R.string.Thecombustion) + intent.extras.getString("fees") + getString(R.string.Property_yue), null, arrayOf(getString(R.string.Welcome_succeed), getString(R.string.Welcome_abandon)), null, this@AffirmDealActivity, AlertView.Style.Alert, object : OnItemClickListener {
                        override fun onItemClick(o: Any?, position: Int) {
                            if (position == 0) {
                                val payDialog = PayDialog(this@AffirmDealActivity)
                                payDialog.setAffirmDealActivity(this@AffirmDealActivity, getString(R.string.AffirmDeal_tit))
                                payDialog.show()
                            }
                        }
                    }).show()
                } else {
                    val payDialog = PayDialog(this@AffirmDealActivity)
                    payDialog.setAffirmDealActivity(this@AffirmDealActivity, getString(R.string.AffirmDeal_tit))
                    payDialog.show()
                }
            }
        })
        affirm_no.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        affirmdeal_user.text = intent.extras.getString("toPhone")
        affirmdeal_username.text = intent.extras.getString("toUsername")
        affirmdeal_mon.text = intent.extras.getString("amount")
        affirmdeal_service.text = intent.extras.getString("fees")
        affirmdeal_anticipation.setText(R.string.ciri)
        affirmdeal_practical.text = "  -  " + intent.extras.getString("actualAmount") + "   "
    }

    //自定义支付密码框回掉
    fun OnInputPayPasswrodSuccess(inputPwd: String) {
        if (MyApplication.keyAddressBeans.userPrivatelyKey != inputPwd) {
            Toast.makeText(this, R.string.pwdInputError, Toast.LENGTH_SHORT).show()
            return
        }
        val tran = intent.extras.getString("transferid")
        val intent = Intent(this@AffirmDealActivity, ParticularsActivity::class.java)
        intent.putExtra("transferid", tran)
        intent.putExtra("paypwd", inputPwd)
        startActivity(intent);
        finish()
    }
}
