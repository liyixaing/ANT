package com.example.administrator.mode.Fragment.crowd

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Fragment.homeFragment.PayDialog
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.buyZhongChouBean
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.app.MyApplication
import com.example.administrator.mode.creatorprivatekey.MessageSignUtils
import kotlinx.android.synthetic.main.activity_crowd_particulars.*
import kotlinx.android.synthetic.main.tit.*
import org.web3j.crypto.Credentials
import org.web3j.crypto.ECKeyPair
import org.web3j.utils.Numeric
import java.text.DecimalFormat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrowdParticularsActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_crowd_particulars
    }

    @SuppressLint("SetTextI18n")
    override fun init() {
        super.init()
        tit_iv.setOnClickListener { finish() }
        tit_name.setText(R.string.projectDetails)
        crowdAmount.text = intent.extras.getString("successAmount")
        crowdParticipation.text = intent.extras.getString("successRatio")
        crawdSchedule.text = (intent.extras.getDouble("successRatio") * 100).toString() + "%"
        val xx = (intent.extras.getDouble("successRatio") * 100).toString().split(".")
        scheduleProgressBar.progress = xx[0].toInt()
        crowdLastDay.text = intent.extras.getString("releaseDays")
        crowdName.text = intent.extras.getString("crowdname")
        crowdDesc.text = VerifyUtlis.stringChang(intent.extras.getString("crowdDesc"))
        crawdSuccess.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (buyInput.text.toString().trim().isEmpty()) {
                    return
                }
                val payDialog = PayDialog(this@CrowdParticularsActivity)
                payDialog.setCrowdParticularsActivity(this@CrowdParticularsActivity, getString(R.string.AffirmDeal_tit))
                payDialog.show()
            }
        })
    }

    fun OnInputPayPasswrodSuccess(str: String) {
        if (MyApplication.keyAddressBeans.userPrivatelyKey != str) {
            Toast.makeText(this, R.string.pwdInputError, Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            var keyPair = ECKeyPair.create(Numeric.hexStringToByteArray(SharedPreferencesUtil.getData("userPrivatelyKey", "").toString()))
            val register = retrofit.create(MoneyService::class.java).buyZhongChou(sp.getString("user_id", ""), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0", intent.extras.getInt("crowdId").toString(), buyInput.text.toString().trim(), "", MessageSignUtils.Sign(Credentials.create(keyPair), Numeric.toHexString(VerifyUtlis.toHash(nowtime + sp.getString("user_token", ""))).substring(2, Numeric.toHexString(VerifyUtlis.toHash(nowtime + sp.getString("user_token", ""))).length)))
            register.enqueue(object : Callback<buyZhongChouBean> {
                override fun onResponse(call: Call<buyZhongChouBean>, response: Response<buyZhongChouBean>) {
                    if (response.body()!!.code == 1) {
                        Toast.makeText(this@CrowdParticularsActivity, R.string.deal_accomplish, Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                    }
                }

                override fun onFailure(call: Call<buyZhongChouBean>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@CrowdParticularsActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
        }
    }
}
