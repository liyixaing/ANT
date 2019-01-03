package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.CountryActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Paritiesturn
import com.example.administrator.mode.Pojo.Transferturn
import com.example.administrator.mode.Pojo.prturn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_transfer_accounts.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransferAccountsActivity : BaseActivity() {

    var num = 0.00
    private var huilv: Double = 0.toDouble()
    private var mix: Double = 0.toDouble()
    private var max: Double = 0.toDouble()
    override fun getContentViewId(): Int {
        return R.layout.activity_transfer_accounts
    }

    override fun onStart() {
        super.onStart()
        loadtransfer()
        loadpr()
        StatService.onPageStart(this@TransferAccountsActivity, "MainModule.HomeView.TransferView")
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        goguanhao.text = sp.getString("user_crowndsdasdas", "86")
    }

    override fun init() {
        super.init()
        tit_name.setText(R.string.Transfer_tit1)
        transfer_name.isFocusable = true
        transfer_name.isFocusableInTouchMode = true
        transfer_name.requestFocus()
        if (intent.extras.getString("userkey") == "nokey") {
        } else {
            val xx = intent.extras.getString("userkey").contains(",")
            if (xx) {
                val xx = intent.extras.getString("userkey").split(",")
                if (xx.size == 2) {
                    transfer_name.setText(xx[0])
                    transfer_quantity.setText(xx[1])
                } else {
                    transfer_name.setText(intent.extras.getString("userkey"))
                }
            } else {
                transfer_name.setText(intent.extras.getString("userkey"))
            }

        }

/*        if (intent.extras.getString("froma") == "xx") {
            var userphoneinput = intent.extras.getString("userkey")
            transfer_name.setText(userphoneinput)
        }*/
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        goguanhao.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(this@TransferAccountsActivity, CountryActivity::class.java)
                intent.putExtra("wwwww", "xxx11111x")
                startActivity(intent)
            }
        })
        qqqqqq.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(this@TransferAccountsActivity, CountryActivity::class.java)
                intent.putExtra("wwwww", "xxx11111x")
                startActivity(intent)
            }
        })
        //监听
        remark.addTextChangedListener(object : TextWatcher {
            val xx = remark.text.toString()
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(e: Editable?) = try {

                if (!remark.text.toString().isNotEmpty()) {
                } else {
                    xianzhi.text = remark.text.toString().length.toString() + "/30"
                }
            } catch (e: Exception) {
            }
        })
        transfer_quantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(e: Editable?) {
                try {
                    if (num.toDouble() < transfer_quantity.text.toString().toDouble()) {
                        transfer_quantity.setText(num.toString().substring(0, num.toString().length - 3))
                        transfer_quantity.setSelection(transfer_quantity.text.toString().trim().length);
                        return
                    }
                    val a = java.lang.Double.parseDouble(transfer_quantity.text.toString().trim())
                    transfer_service.setText((a * huilv).toString())
                    moneyinput.text = "  -  " + String.format("%.8f", ((a * huilv) + transfer_quantity.text.toString().toDouble())) + "  "
                } catch (e: Exception) {
                    transfer_quantity.hint = "0.00"
                }
            }
        })
        transfer_next.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (remark.text.toString().trim().length > 30) {
                    Toast.makeText(this@TransferAccountsActivity, R.string.Transfer_Notelong, Toast.LENGTH_SHORT).show()
                    return
                }
                if ("" == transfer_name.text.toString().trim()) {
                    Toast.makeText(this@TransferAccountsActivity, R.string.Transfer_nameint, Toast.LENGTH_SHORT).show()
                    return
                }

                if ("" == transfer_quantity.text.toString().trim()) {
                    Toast.makeText(this@TransferAccountsActivity, R.string.Transfer_numbermoney, Toast.LENGTH_SHORT).show()
                    return
                }
                val c = java.lang.Double.parseDouble(transfer_quantity.text.toString().trim())

                if (mix > c) {
                    Toast.makeText(this@TransferAccountsActivity, "最小输入$mix", Toast.LENGTH_SHORT).show()
                    return
                }

                if (max < c) {
                    Toast.makeText(this@TransferAccountsActivity, "最大输入$max", Toast.LENGTH_SHORT).show()
                    return
                }
                try {
                    val nowtime = DateUtils.getdata()
                    val retrofit = Retrofit_manager.getInstance().getUserlogin()
                    val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                    val transfer = retrofit.create(MoneyService::class.java!!).transfer(sp.getString("user_id", ""), transfer_name.text.toString().trim(), goguanhao.text.toString().trim(), sp.getString("user_token", ""), transfer_quantity.text.toString().trim(), "", remark.text.toString().trim(), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
                    transfer.enqueue(object : Callback<Transferturn> {
                        override fun onResponse(call: Call<Transferturn>, response: Response<Transferturn>) {
                            try {
                                if (response.body()!!.code == 1) {
                                    val intent = Intent(this@TransferAccountsActivity, AffirmDealActivity::class.java)
                                    intent.putExtra("transferid", response.body()!!.data!!.tradeId.toString())
                                    intent.putExtra("toPhone", response.body()!!.data!!.toPhone.toString())
                                    intent.putExtra("toUsername", response.body()!!.data!!.toUsername.toString())
                                    intent.putExtra("amount", String.format("%.8f", response.body()!!.data!!.amount))
                                    intent.putExtra("actualAmount", String.format("%.8f", response.body()!!.data!!.actualAmount))
                                    intent.putExtra("transfer_time", response.body()!!.data!!.transfer_time.toString())
                                    intent.putExtra("fees", response.body()!!.data!!.fees.toString())
                                    startActivity(intent)
                                    StatService.onPageEnd(this@TransferAccountsActivity, "MainModule.HomeView.TransferView")
                                }
                            } catch (e: Exception) {
                                abnormal(this@TransferAccountsActivity)
                            }
                        }

                        override fun onFailure(call: Call<Transferturn>, t: Throwable) {
                            if (t is DataResultException) {
                                val resultException = t as DataResultException
                                Toast.makeText(this@TransferAccountsActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                } catch (e: Exception) {
                    abnormal(this@TransferAccountsActivity)
                }
            }
        })
    }

    fun loadtransfer() {
        try {
            val nowtime = DateUtils.getdata()
            val retrofit = Retrofit_manager.getInstance().getUserlogin()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val transfer = retrofit.create(MoneyService::class.java!!).getParities(sp.getString("user_id", ""), sp.getString("user_token", ""), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            transfer.enqueue(object : Callback<Paritiesturn> {
                override fun onResponse(call: Call<Paritiesturn>, response: Response<Paritiesturn>) {
                    try {
                        if (response.body()!!.code == 1) {
                            huilv = response.body()!!.data!!.ant_transfer_rate!!.toDouble()
                            mix = response.body()!!.data!!.ant_transfer_min_value!!.toDouble()
                            max = response.body()!!.data!!.ant_transfer_max_value!!.toDouble()
                            transfer_serviceCharge.setText((response.body()!!.data!!.ant_transfer_rate!!.toDouble() * 100).toString() + "%")
                        }
                    } catch (e: Exception) {
                        abnormal(this@TransferAccountsActivity)
                    }
                }

                override fun onFailure(call: Call<Paritiesturn>, t: Throwable) {
                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(this@TransferAccountsActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this@TransferAccountsActivity)
        }
    }

    var Available2 = ""
    fun loadpr() {
        if (PreferencesUtil.get("language", "").equals("zh")) {
            Available2 = "可用通证"
        } else if (PreferencesUtil.get("language", "").equals("en")) {
            Available2 = "Available through the"
        } else {
            Available2 = "可用通证"
        }

        val retrofit = Retrofit_manager.getInstance().getUserlogin()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        var token = sp.getString("user_token", "")
        val nowtime = DateUtils.getdata()
        var id = sp.getString("user_id", "")
        try {
            val login = retrofit.create(MoneyService::class.java!!).paroper(id, token, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<prturn> {
                override fun onResponse(call: Call<prturn>, response: Response<prturn>) {
                    try {
                        if (response.body()!!.code == 1) {
                            num = response.body()!!.data!!.user_ant!!.toDouble()
                            car_transfer.setText(Available2 + num.toString())
                        } else {
                            Toast.makeText(this@TransferAccountsActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                    }
                }

                override fun onFailure(call: Call<prturn>, t: Throwable) {
                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(this@TransferAccountsActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })

        } catch (e: Exception) {
        }
    }

}

