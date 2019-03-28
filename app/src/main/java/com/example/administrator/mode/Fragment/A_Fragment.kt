package com.example.administrator.mode.Fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.administrator.mode.Activity.AnnouncementActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Activity.drawer.InviteActivity
import com.example.administrator.mode.Adapter.BannerAdapter
import com.example.administrator.mode.Adapter.SmoothLinearLayoutManager
import com.example.administrator.mode.Fragment.homeFragment.*
import com.example.administrator.mode.Fragment.red_packet.RedPacketGetActivity
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.LatestNoticeTurn
import com.example.administrator.mode.Pojo.RedPacket
import com.example.administrator.mode.Pojo.prturn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.creatorprivatekey.NewLoginActivity
import com.github.shenyuanqing.zxingsimplify.zxing.Activity.CaptureActivity
import kotlinx.android.synthetic.main.fragment_a.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class A_Fragment : Fragment() {
    var image = arrayListOf<Int>()
    var num = arrayListOf<String>()
    private var siz = ""
    private var redId = 0
    private var mix = 0.0
    private var score = 0.0
    var xx: String = ""
    var zz: String = ""
    var aa: String = ""
    var bb: String = ""
    var aaaqq = ""
    var xxaa = ""
    var itemsinpit = arrayOf(PreferencesUtil.get("collection", ""), PreferencesUtil.get("envelope", ""))


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
        if (sp.getString("language", "") == "zh") {
            xx = "直接转账"
            zz = "扫码转账"
            aaaqq = "请选择转账方式"
            xxaa = "您已领取"
        } else if (sp.getString("language", "") == "en") {
            xx = "Direct transfer"
            zz = "Scan to transfer"
            aaaqq = "Please select transfer method"
            xxaa = "You have to receive"
        } else {
            xx = "直接转账"
            zz = "扫码转账"
            aaaqq = "请选择转账方式"
            xxaa = "您已领取"
        }
        val items = arrayOf(xx, zz)
        contract.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, GameActivity::class.java))
            }
        })
        refuel.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, dialActivity::class.java))
            }
        })
        book.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, MerchantActivity::class.java))
            }
        })

        telephone.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
                val intent = Intent(activity, ANTStoreActivity::class.java)
                intent.putExtra("url", "http://mall.fcsap.com/?user_id=" + sp.getString("user_id", "") + "&user_token=" + Encryption.generateFakeTokenToShop(sp.getString("user_token", "")) + "&mall_key=b64ab4b8124e2c5d43d52d9c05a6f992")
                startActivity(intent)
                activity!!.finish()

            }
        })

        taking.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(activity, ConsultActivity::class.java)
                intent.putExtra("webUrl", "purchasing")
                startActivity(intent)
            }
        })
        game.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(activity, ConsultActivity::class.java)
                intent.putExtra("webUrl", "mountain")
                startActivity(intent)
            }
        })
        take.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(activity, ConsultActivity::class.java)
                intent.putExtra("webUrl", "funding")
                startActivity(intent)
            }
        })
        information.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(activity, ConsultActivity::class.java)
                intent.putExtra("webUrl", "https://m.jinse.com/member/209230")
                startActivity(intent)
            }
        })
        more1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                unopened()
            }
        })
        image.add(R.drawable.banner6)
        image.add(R.drawable.banner4)
        image.add(R.drawable.banner2)
        image.add(R.drawable.banner)
        image.add(R.drawable.banner8)
        val adapter = BannerAdapter(activity, image)
        val layoutManager = SmoothLinearLayoutManager(activity, SmoothLinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager
        recycler.setHasFixedSize(true)
        recycler.scrollToPosition(image.size * 10)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recycler)
        val scheduledExecutorService = Executors.newScheduledThreadPool(1)
        scheduledExecutorService.scheduleAtFixedRate({ recycler.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1); }, 5000, 5000, TimeUnit.MILLISECONDS)
        //扫一下
        sao.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val alertDialog = AlertDialog.Builder(activity)
                        .setTitle(aaaqq)
                        .setItems(items) { _, i ->
                            //添加列表giro
                            if (items[i] == "直接转账") {
                                val intent = Intent(activity, TransferAccountsActivity::class.java)
                                intent.putExtra("userkey", "nokey")
                                startActivity(intent)
                            } else if (items[i] == "Direct transfer") {
                                val intent = Intent(activity, TransferAccountsActivity::class.java)
                                intent.putExtra("userkey", "nokey")
                                startActivity(intent)
                            } else if (items[i] == "Scan to transfer") {
                                try {
                                    val intent = Intent(activity, CaptureActivity::class.java)
                                    startActivityForResult(intent, 9527)
                                } catch (e: Exception) {
                                }
                            } else if (items[i] == "扫码转账") {
                                try {
                                    val intent = Intent(activity, CaptureActivity::class.java)
                                    startActivityForResult(intent, 9527)
                                } catch (e: Exception) {
                                }
                            }
                        }
                        .create()
                alertDialog.show()
            }
        })
        //加速
        jiashu.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, RedPacketGetActivity::class.java))
            }
        })
        //收款
        shou.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, CodeActivity::class.java))
            }
        })
        //余额
        home_balance.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, PropertyActivity::class.java))
            }
        })
        //资产
        home_card.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(activity, ConsultActivity::class.java)
                intent.putExtra("webUrl", "CustomerService")
                startActivity(intent)
    /*            startActivity(Intent(activity, ServiceActivity::class.java))*/
            }
        })
        //兑换资产
        conversion.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, ConversionActivity::class.java))
            }
        })
        //显示收益
        relative_comm.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, MyNodeActivity::class.java))
            }
        })
        zhi.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, PosActivity::class.java))
            }
        })
        relative_comm1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, DpostActivity::class.java))
            }
        })
        T2.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, InviteActivity::class.java))
            }
        })
        friend.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(activity, CommunityFriendActivity::class.java))
            }
        })
        pull.setOnRefreshListener {
            loadpr()
            pull.setRefreshing(false)
        }
    }

    fun loadpr() {
        val retrofit = Retrofit_manager.getInstance().getUserlogin()
        val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val token = sp.getString("user_token", "")
        val id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java!!).paroper(id, token, "0", nowtime, PreferencesUtil.get("language", "ch"), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<prturn> {
                override fun onResponse(call: Call<prturn>, response: Response<prturn>) {
                    try {
                        if (response.body()!!.code == 1) {
                            ant.text = String.format("%.0f", response.body()!!.data!!.user_ant!!.toDouble())
                            score = response.body()!!.data!!.user_score!!.toDouble()
                            integral.text = String.format("%.0f", response.body()!!.data!!.user_score!!.toDouble())
                            if (response.body()!!.data!!.envelopeCount!! == "0") {
                                redDo.visibility = View.GONE
                            } else {
                                redDo.visibility = View.VISIBLE
                            }

                            if (response.body()!!.data!!.redPacketCount.equals("0")) {
                            } else {
                                jiashu.isClickable = true
                                jiashuinput()
                            }
                            initAnnouncement()
                        } else {
                            Toast.makeText(activity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                    }
                }

                override fun onFailure(call: Call<prturn>, t: Throwable) {
                    val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
                    //获得一个SharedPreferences编辑器
                    Toast.makeText(activity, R.string.guoqi, Toast.LENGTH_SHORT).show()
                    val edit = sp.edit()
                    edit.putBoolean("first", true)
                    edit.commit()
                    startActivity(Intent(activity, NewLoginActivity::class.java))
                    activity!!.finish()
                }
            })
        } catch (e: Exception) {

        }
    }

    fun initAnnouncement() {
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val edit = sp.edit()
        val token = sp.getString("user_token", "")
        val id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java).getLatestNotice(id, token, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<LatestNoticeTurn> {
                override fun onResponse(call: Call<LatestNoticeTurn>, response: Response<LatestNoticeTurn>) {
                    try {
                        if (response.body()!!.code == 1) {
                            if (response.body()!!.data.content == "") {
                                return
                            }
                            if (sp.getString("announcementTime", "noTime") == response.body()!!.data.createTime.toString()) {
                                return
                            } else {
                                val payDialog = AnnouncementActivity(activity!!)
                                payDialog.setaFragment(this@A_Fragment, response.body()!!.data.content)
                                payDialog.show()
                                edit.putString("announcementTime", response.body()!!.data.createTime.toString())
                                edit.commit()
                            }
                        } else {
                            Toast.makeText(activity!!, response.message().toString(), Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                    }
                }

                override fun onFailure(call: Call<LatestNoticeTurn>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(activity!!, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {

        }
    }

    fun jiashuinput() {
        val payDialog = RedPacketActivity(activity)
        payDialog.setaFragment(this)
        payDialog.show()
    }

    override fun onStart() {
        super.onStart()
        pull.setColor(R.color.essential)
        pull.setRefreshing(true)
        loadpr()
        pull.setRefreshing(false)
    }

    //回掉
    fun next() {
        try {
            getRedPacket()
        } catch (e: Exception) {
        }
    }

    //回掉
    fun next1() {
        try {
            val payDialog = RedPacket2Activity(activity)
            payDialog.setaFragment(this, siz)
            payDialog.show()
        } catch (e: Exception) {

        }

    }

    fun getRedPacket() {
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val token = sp.getString("user_token", "")
        val id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java).getEveryDayRed(id, token, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<RedPacket> {
                override fun onResponse(call: Call<RedPacket>, response: Response<RedPacket>) {
                    try {
                        if (response.body()!!.code == 1) {
                            siz = String.format("%.2f", response.body()!!.data.un_claim_amount)
                            redId = response.body()!!.data.id
                            mix = response.body()!!.data.current_personal_score
                            val payDialog = RedPacket1Activity(activity)
                            payDialog.setaFragment(this@A_Fragment)
                            payDialog.show()
                        } else {
                            Toast.makeText(activity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                    }
                }

                override fun onFailure(call: Call<RedPacket>, t: Throwable) {
                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(activity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {

        }
    }

    //回掉
    fun next2() {
        if (siz.toDouble() > mix) {
            val alertDialog1 = AlertDialog.Builder(activity)
                    .setTitle(PreferencesUtil.get("enough", "") + "${siz.toDouble() - mix}" + PreferencesUtil.get("earnings", ""))
                    .setItems(itemsinpit) { _, i ->
                        //添加列表
                        if (itemsinpit[i] == PreferencesUtil.get("collection", "")) {
                            try {
                                val nowtime = DateUtils.getdata()
                                val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
                                val retrofit = Retrofit_manager.getInstance().userlogin
                                val register = retrofit.create(GitHubService::class.java).redPacketUp(sp.getString("user_id", ""), redId.toString(), sp.getString("user_token", ""), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
                                register.enqueue(object : Callback<Common> {
                                    override fun onResponse(call: Call<Common>, response: Response<Common>) {
                                        try {
                                            if (response.body()!!.code == 1) {
                                                Toast.makeText(activity, xxaa + response.body()!!.data + "ANT", Toast.LENGTH_SHORT).show()
                                                loadpr()
                                            } else {
                                                Toast.makeText(activity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                            }
                                        } catch (e: Exception) {
                                        }
                                    }

                                    override fun onFailure(call: Call<Common>, t: Throwable) {
                                        val resultException = t as DataResultException
                                        Toast.makeText(activity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                                    }
                                })

                            } catch (e: Exception) {
                            }

                        } else if (itemsinpit[i] == PreferencesUtil.get("envelope", "")) {
                        }
                    }
                    .create()
            alertDialog1.show()
        } else {
            try {
                val nowtime = DateUtils.getdata()
                val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
                val retrofit = Retrofit_manager.getInstance().userlogin
                val register = retrofit.create(GitHubService::class.java!!).redPacketUp(sp.getString("user_id", ""), redId.toString(), sp.getString("user_token", ""), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
                register.enqueue(object : Callback<Common> {
                    override fun onResponse(call: Call<Common>, response: Response<Common>) {
                        try {
                            if (response.body()!!.code == 1) {
                                Toast.makeText(activity, PreferencesUtil.get("receive", "") + response.body()!!.data + "ANT", Toast.LENGTH_SHORT).show()
                                loadpr()
                            } else {
                                Toast.makeText(activity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                        }
                    }

                    override fun onFailure(call: Call<Common>, t: Throwable) {
                        if (t is DataResultException) {
                            Toast.makeText(activity, t.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            } catch (e: Exception) {
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            75063 -> try {
                val result = data!!.getStringExtra("barCode")
                val intent = Intent(activity, TransferAccountsActivity::class.java)
                intent.putExtra("userkey", result.toString())
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
    }

    fun unopened() {
        Toast.makeText(activity, R.string.jianshe, Toast.LENGTH_SHORT).show()
    }
}
