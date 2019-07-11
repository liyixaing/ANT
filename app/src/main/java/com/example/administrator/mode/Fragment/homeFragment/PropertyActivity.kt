package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.util.Log

import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Bantrun
import com.example.administrator.mode.Pojo.prpertyturn
import com.example.administrator.mode.Pojo.prturn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_property.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PropertyActivity : BaseActivity() {
    var list = arrayListOf<prpertyturn.DataBean>()
    var listInput = arrayListOf<prpertyturn.DataBean>()
    var list1 = arrayListOf<Bantrun.DataBean>()
    var list1Input = arrayListOf<Bantrun.DataBean>()
    var prnum = 10
    var bannum = 10
    var num1 = 1
    var num = 1
    private var recLen = 2
    var timer = Timer()
    private var recLen1 = 2
    var timer1 = Timer()
    private var baseadaptr: FenAdater<*>? = null
    override fun getContentViewId(): Int {
        return R.layout.activity_property
    }

    override fun onStart() {
        super.onStart()
        StatService.onPageStart(this@PropertyActivity, "MainModule.HomeView.TokenDetailView")
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    fun loadpr() {
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val token = sp.getString("user_token", "")
        val id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java).paroper(id, token, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<prturn> {
                override fun onResponse(call: Call<prturn>, response: Response<prturn>) {
                    try {
                        if (response.body()!!.code == 1) {
                            property_mon.text = String.format("%.8f", response.body()!!.data!!.user_ant!!.toDouble())
                            property_ton.text = String.format("%.8f", response.body()!!.data!!.user_score!!.toDouble())
                        } else {
                            Toast.makeText(this@PropertyActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                    }
                }
                override fun onFailure(call: Call<prturn>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@PropertyActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
        }
    }

    private fun propertyload(num: Int) {
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val token = sp.getString("user_token", "")
        val id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java).getban(id, token, num.toString(), bannum.toString(), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<Bantrun> {
                override fun onResponse(call: Call<Bantrun>, response: Response<Bantrun>) {
                    if (response.body()!!.code == 1) {
                        list1 = response.body()!!.data as ArrayList<Bantrun.DataBean>
                        list1Input.addAll(list1)
                        baseadaptr!!.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@PropertyActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<Bantrun>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@PropertyActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this@PropertyActivity)
        }
    }

    private fun cardload(num1: Int) {
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val token = sp.getString("user_token", "")
        val id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java).getproperty(id, token, num1.toString(), prnum.toString(), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<prpertyturn> {
                override fun onResponse(call: Call<prpertyturn>, response: Response<prpertyturn>) {
                    try {
                        if (response.body()!!.code == 1) {
                            list = response.body()!!.data as ArrayList<prpertyturn.DataBean>
                            listInput.addAll(list)
                            baseadaptr!!.notifyDataSetChanged()
                        } else {
                            Toast.makeText(this@PropertyActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        abnormal(this@PropertyActivity)
                    }
                }
                override fun onFailure(call: Call<prpertyturn>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@PropertyActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this@PropertyActivity)
        }
    }

    override fun init() {
        super.init()
        tong1()
        loadpr()
        tit_name.setText(R.string.Property_tit1)
        zichan.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                try {
                    zichanload()
                    list1Input.clear()
                    num = 1
                    property_lv.visibility = View.GONE
                    card_lv.visibility = View.VISIBLE
                    view1.visibility = View.GONE
                    view2.visibility = View.VISIBLE
                } catch (e: Exception) {
                }
            }
        })
        tong.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                try {
                    tong1()
                    num1 = 1
                    listInput.clear()
                    property_lv.visibility = View.VISIBLE
                    card_lv.visibility = View.GONE
                    view2.visibility = View.GONE
                    view1.visibility = View.VISIBLE
                } catch (e: Exception) {
                }
            }
        })
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        property_con.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@PropertyActivity, ConversionActivity::class.java))
            }
        })
        antout.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(this@PropertyActivity, TransferAccountsActivity::class.java)
                intent.putExtra("userkey", "nokey")
                startActivity(intent)
            }
        })
        antget.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@PropertyActivity, CodeActivity::class.java))
            }
        })
        card_lv.setONLoadMoreListener {
            val task = object : TimerTask() {
                @SuppressLint("ResourceType")
                override fun run() {
                    runOnUiThread {
                        if (recLen1 == 0) {
                            num1++
                            cardload(num1)
                            card_lv.setLoadCompleted()
                        }
                        recLen1--
                    }
                }
            }
            timer1.schedule(task, 1000, 1000)
            recLen1 = 2
        }
        property_lv.setONLoadMoreListener {
            val task = object : TimerTask() {
                @SuppressLint("ResourceType")
                override fun run() {
                    runOnUiThread {
                        if (recLen == 0) {
                            num++
                            propertyload(num)
                            property_lv.setLoadCompleted()
                        }
                        recLen--
                    }
                }
            }
            timer.schedule(task, 1000, 1000)
            recLen = 2
        }
        property_lv.onItemClickListener = AdapterView.OnItemClickListener { _, _, p2, _ ->
            if (list1Input[p2].changeType == 1 || list1Input[p2].changeType == 2) {
                orderper(list1Input[p2].id, "tong")
            } else {
                orderLoadstm(list1Input[p2].id, "tong")
            }
        }
        card_lv.onItemClickListener = AdapterView.OnItemClickListener { _, _, p2, _ ->
            if (listInput[p2].changeType == 1 || listInput[p2].changeType == 2) {
                orderper(listInput[p2].id, "property")
            } else {
                orderLoadstm(listInput[p2].id, "property")
            }
        }
    }

    fun tong1() {
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val token = sp.getString("user_token", "")
        val id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java).getban(id, token, "1", bannum.toString(), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<Bantrun> {
                override fun onResponse(call: Call<Bantrun>, response: Response<Bantrun>) {
                    if (response.body()!!.code == 1) {
                        list1 = response.body()!!.data as ArrayList<Bantrun.DataBean>
                        list1Input.addAll(list1)
                        baseadaptr = object : FenAdater<Bantrun.DataBean>(this@PropertyActivity, list1Input, R.layout.propertylayout) {
                            override fun convert(holder: ViewHolder?, item: Bantrun.DataBean?) {
                                holder!!.setText(R.id.property_type, item!!.changeDesc)
                                holder.setText(R.id.property_date, DateUtils.timeslash(item.changeTime.toString()))
                                if (item.changeAmount.toString().indexOf("-") != -1) {
                                    holder.setText(R.id.property_card, String.format("%.2f", item.changeAmount))
                                } else {
                                    holder.setText(R.id.property_card, "+" + String.format("%.2f", item.changeAmount))
                                }
                            }
                        }
                        property_lv.adapter = baseadaptr
                    } else {
                        Toast.makeText(this@PropertyActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<Bantrun>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@PropertyActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })

        } catch (e: Exception) {
            abnormal(this@PropertyActivity)
        }
    }

    fun zichanload() {
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val token = sp.getString("user_token", "")
        val id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java).getproperty(id, token, "1", prnum.toString(), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<prpertyturn> {
                override fun onResponse(call: Call<prpertyturn>, response: Response<prpertyturn>) {
                    try {
                        if (response.body()!!.code == 1) {
                            list = response.body()!!.data as ArrayList<prpertyturn.DataBean>
                            listInput.addAll(list)
                            baseadaptr = object : FenAdater<prpertyturn.DataBean>(this@PropertyActivity, listInput, R.layout.propertylayout) {
                                override fun convert(holder: ViewHolder?, item: prpertyturn.DataBean?) {
                                    holder!!.setText(R.id.property_type, item!!.changeDesc)
                                    holder.setText(R.id.property_date, DateUtils.timeslash(item.changeTime.toString()))
                                    if (item.changeAmount.toString().indexOf("-") != -1) {
                                        holder.setText(R.id.property_card, String.format("%.2f", item.changeAmount))
                                    } else {
                                        holder.setText(R.id.property_card, "+" + String.format("%.2f", item.changeAmount))
                                    }
                                }
                            }
                            card_lv.adapter = baseadaptr
                        } else {
                            Toast.makeText(this@PropertyActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        abnormal(this@PropertyActivity)
                    }
                }

                override fun onFailure(call: Call<prpertyturn>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@PropertyActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })

        } catch (e: Exception) {
            abnormal(this@PropertyActivity)
        }
    }


    fun orderLoadstm(order: Int, type: String) {
        val intent = Intent(this@PropertyActivity, PropertyDetailActivity::class.java)
        intent.putExtra("order", order.toString())
        intent.putExtra("propertytype", type)
        startActivity(intent)
        StatService.onPageEnd(this@PropertyActivity, "MainModule.HomeView.TokenDetailView")
        finish()
    }

    fun orderper(order: Int, type: String) {
        val intent = Intent(this@PropertyActivity, PersonToPersonDetailActivity::class.java)
        intent.putExtra("order", order.toString())
        intent.putExtra("propertytype", type)
        startActivity(intent)
        StatService.onPageEnd(this@PropertyActivity, "MainModule.HomeView.TokenDetailView")
        finish()
    }
}
