package com.example.administrator.mode.Fragment.red_packet

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.CountryActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Fragment.homeFragment.PayDialog
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.GetRedBagMessage
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.app.MyApplication
import com.example.administrator.mode.creatorprivatekey.MessageSignUtils
import kotlinx.android.synthetic.main.activity_ordinary_red_envelopes.*
import org.web3j.crypto.Credentials
import org.web3j.crypto.ECKeyPair
import org.web3j.utils.Numeric
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdinaryRedEnvelopesActivity : BaseActivity() {
    var redEnvelopeMax = 0
    var redEnvelopeMin = 0
    override fun getContentViewId(): Int {
        return R.layout.activity_ordinary_red_envelopes
    }

    override fun onStart() {
        super.onStart()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        if (intent.extras.getString("userFirendPhone") != "") {
            setRedBagPhone.setText(intent.extras.getString("userFirendPhone"))
        }

        redBagCrown.text = sp.getString("user_crowndsdasdas", "86")
    }

    override fun init() {
        initView()
        tit_iv.setOnClickListener { finish() }
        addFriend.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@OrdinaryRedEnvelopesActivity, RedFriendActivity::class.java))
                finish()
            }
        })
        setRedBagMoney.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(e: Editable?) {
                try {
                    redCardInput.text = String.format("%.2f", setRedBagMoney.text.toString().toDouble()) + getString(R.string.RedEnvelopeProperty)
                } catch (e: Exception) {
                    redCardInput.text = "00.00"
                }
            }
        })
        redCrownSelect.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(this@OrdinaryRedEnvelopesActivity, CountryActivity::class.java)
                intent.putExtra("wwwww", "xxx11111x")
                startActivity(intent)
            }
        })
        sendCommonBonuses.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (!setRedBagMoney.text.isNotEmpty() || !setRedBagPhone.text.isNotEmpty()) {
                    Toast.makeText(this@OrdinaryRedEnvelopesActivity, R.string.RedEnvelopeMoneyandFirendError, Toast.LENGTH_SHORT).show()
                    return
                }
                if (setRedBagMoney.text.toString().trim().toInt() > redEnvelopeMax || setRedBagMoney.text.toString().trim().toInt() < redEnvelopeMin) {
                    Toast.makeText(this@OrdinaryRedEnvelopesActivity, getString(R.string.interval_shall_be) + redEnvelopeMin + "--" + redEnvelopeMax, Toast.LENGTH_SHORT).show()
                    return
                }
                sendRedBag()
            }
        })
    }

    private fun initView() {
        try {
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(MoneyService::class.java).getEnvelopeConfigInfo(sp.getString("user_id", ""), "1", nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
            register.enqueue(object : Callback<GetRedBagMessage> {
                override fun onResponse(call: Call<GetRedBagMessage>, response: Response<GetRedBagMessage>) {
                    if (response.body()!!.code == 1) {
                        redEnvelopeMax = response.body()!!.data.ant_red_envolope_territory_score_max_value.toInt()
                        redEnvelopeMin = response.body()!!.data!!.ant_red_envolope_territoty_score_min_value.toInt()
                    } else {
                    }
                }

                override fun onFailure(call: Call<GetRedBagMessage>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@OrdinaryRedEnvelopesActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: java.lang.Exception) {

        }
    }

    private fun sendRedBag() {
        val payDialog = PayDialog(this@OrdinaryRedEnvelopesActivity)
        payDialog.setOrdinaryRedEnvelopesActivity(this@OrdinaryRedEnvelopesActivity, getString(R.string.AffirmDeal_tit))
        payDialog.show()
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
            val register = retrofit.create(MoneyService::class.java).sendSingleEnvelope(sp.getString("user_id", ""), "", "", setRedBagPhone.text.toString().trim(), redBagCrown.text.toString().trim(), "1", setRedBagMoney.text.toString().trim(), setRedBagmessage.text.toString().trim(), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0", MessageSignUtils.Sign(Credentials.create(keyPair), Numeric.toHexString(VerifyUtlis.toHash(nowtime + sp.getString("user_token", ""))).substring(2, Numeric.toHexString(VerifyUtlis.toHash(nowtime + sp.getString("user_token", ""))).length)))
            register.enqueue(object : Callback<Common> {
                override fun onResponse(call: Call<Common>, response: Response<Common>) {
                    if (response.body()!!.code == 1) {
                        Toast.makeText(this@OrdinaryRedEnvelopesActivity, R.string.SSM, Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                    }
                }

                override fun onFailure(call: Call<Common>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@OrdinaryRedEnvelopesActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: java.lang.Exception) {

        }
    }
}
