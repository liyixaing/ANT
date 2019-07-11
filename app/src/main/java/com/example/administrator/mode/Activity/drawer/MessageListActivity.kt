package com.example.administrator.mode.Activity.drawer

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Adapter.FenAdater
import com.example.administrator.mode.Adapter.ViewHolder
import com.example.administrator.mode.Fragment.homeFragment.LoadMoreListView
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.GetNoticeByTop
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_message_list.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MessageListActivity : BaseActivity() {
    var messageList = arrayListOf<GetNoticeByTop.DataBean>()
    var num = 0
    private var recLen = 2
    var timer = Timer()
    private var baseadaptr: FenAdater<*>? = null
    override fun getContentViewId(): Int {
        return R.layout.activity_message_list
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setText(R.string.ttt)
        initData()
        messageListView.setONLoadMoreListener {
            val task = object : TimerTask() {
                @SuppressLint("ResourceType")
                override fun run() {
                    runOnUiThread {
                        if (recLen == 0) {
                            num += 10
                            initData()
                            messageListView.setLoadCompleted()
                            baseadaptr!!.notifyDataSetChanged()
                        }
                        recLen--
                    }
                }
            }
            timer.schedule(task, 1000, 1000)
            recLen = 2
        }
        messageListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, p2, _ ->
            if (messageList[p2].noticeId != null || messageList[p2].noticeId != "") {
                val jumpMessageImpt = Intent(this, MesageImpuActivity::class.java)
                jumpMessageImpt.putExtra("jumpMessageImptaa", messageList[p2].noticeId)
                startActivity(jumpMessageImpt)
                finish()
            }
        }
    }

    private fun initData() {
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val retrofit = Retrofit_manager.getInstance().userlogin
        val nowtime = DateUtils.getdata()
        val login = retrofit.create(MoneyService::class.java).getNoticeByTop(sp.getString("user_id", ""), sp.getString("user_token", ""), num.toString(), "10", "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
        login.enqueue(object : Callback<GetNoticeByTop> {
            override fun onResponse(call: Call<GetNoticeByTop>, response: Response<GetNoticeByTop>) {
                try {
                    if (response.body()!!.code == 1) {
                        val message = response.body()!!.data
                        messageList.addAll(message)
                        baseadaptr = object : FenAdater<GetNoticeByTop.DataBean>(this@MessageListActivity, messageList, R.layout.messagelayout) {
                            override fun convert(holder: ViewHolder?, item: GetNoticeByTop.DataBean?) {
                                holder!!.setText(R.id.messageTit, item!!.title)
                                holder.setText(R.id.messageContent, item.content)
                                holder.setText(R.id.messageTime, DateUtils.timeslash(item!!.createTime.toString()))
                            }
                        }
                        messageListView.adapter = baseadaptr
                    }
                } catch (e: Exception) {
                }
            }

            override fun onFailure(call: Call<GetNoticeByTop>, t: Throwable) {
                if (t is DataResultException) {
                    Toast.makeText(this@MessageListActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
