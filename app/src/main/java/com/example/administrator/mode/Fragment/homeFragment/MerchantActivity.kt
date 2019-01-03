package com.example.administrator.mode.Fragment.homeFragment

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import kotlinx.android.synthetic.main.activity_merchant.*
import kotlinx.android.synthetic.main.tit.*

class MerchantActivity : BaseActivity() {

    override fun getContentViewId(): Int {
        return R.layout.activity_merchant
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setText(R.string.iii)
        hei.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (aa.text.toString().trim().isNotEmpty() ||
                        bb.text.toString().trim().isNotEmpty() ||
                        cc.text.toString().trim().isNotEmpty() ||
                        dd.text.toString().trim().isNotEmpty() ||
                        ee.text.toString().trim().isNotEmpty()) {
                    if (ee.text.toString().trim().length > 50) {
                        Toast.makeText(this@MerchantActivity, R.string.mmm, Toast.LENGTH_SHORT).show()
                        return
                    }
                    val intent = Intent(this@MerchantActivity, MerchantQrCodeActivity::class.java)
                    intent.putExtra("aa", aa.text.toString().trim())
                    intent.putExtra("bb", bb.text.toString().trim())
                    intent.putExtra("cc", cc.text.toString().trim())
                    intent.putExtra("dd", dd.text.toString().trim())
                    intent.putExtra("ee", ee.text.toString().trim())
                    intent.putExtra("aaaaaa", "hei")
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@MerchantActivity, R.string.lll, Toast.LENGTH_SHORT).show()
                }

            }
        })
        cai.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (aa.text.toString().trim().isNotEmpty() ||
                        bb.text.toString().trim().isNotEmpty() ||
                        cc.text.toString().trim().isNotEmpty() ||
                        dd.text.toString().trim().isNotEmpty() ||
                        ee.text.toString().trim().isNotEmpty()) {
                    if (ee.text.toString().trim().length > 50) {
                        Toast.makeText(this@MerchantActivity, R.string.mmm, Toast.LENGTH_SHORT).show()
                        return
                    }
                    val intent = Intent(this@MerchantActivity, MerchantQrCodeActivity::class.java)
                    intent.putExtra("aa", aa.text.toString().trim())
                    intent.putExtra("bb", bb.text.toString().trim())
                    intent.putExtra("cc", cc.text.toString().trim())
                    intent.putExtra("dd", dd.text.toString().trim())
                    intent.putExtra("ee", ee.text.toString().trim())
                    intent.putExtra("aaaaaa", "cai")
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@MerchantActivity, R.string.lll, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
