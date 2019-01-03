package com.example.administrator.mode.Fragment.homeFragment

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.bumptech.glide.Glide
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.GetNodeMessage
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_allocation.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllocationActivity : BaseActivity() {
    var from = ""
    override fun getContentViewId(): Int {
        return R.layout.activity_allocation
    }
    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
             startActivity(Intent(this@AllocationActivity,CommunityFriendActivity::class.java))
                finish()
            }
        })
        tit_name.setText(R.string.namenode)
        node.setFocusable(true);
        node.setFocusableInTouchMode(true);
        node.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        node.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_DONE) {
                    hideSoftInput()
                }
                return false;
            }
        })
        confirmTheAllocation.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                confirm()
            }
        })
        getBack.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@AllocationActivity,CommunityFriendActivity::class.java))
                finish()
            }
        })
        n_Search.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                hideSoftInput()

            }
        })
    }

    override fun onStart() {
        super.onStart()
        from = intent.extras.getString("allocation")
        if (from.equals("manual")) {
        } else {
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            node.setText(intent.extras.getString("allocation"))
            getBack.visibility = View.VISIBLE
            node.setFocusable(false)
            uLin.visibility = View.VISIBLE
            sNodeMessage.visibility = View.VISIBLE
            confirmTheAllocation.visibility = View.GONE
            sAffirm.visibility = View.GONE
            n_Search.visibility = View.GONE
            hideSoftInput()
        }
    }


    fun confirm() {
        try {
            val nowtime= DateUtils.getdata()
            val uIdInput = intent.extras.getString("uid")
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().getUserlogin()
            val getNodeMessage = retrofit.create(GitHubService::class.java!!).antIntroduce(sp.getString("user_id", ""), sp.getString("user_token", ""), uIdInput, node.text.toString().trim(),"0",nowtime,PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "")+nowtime))
            getNodeMessage.enqueue(object : Callback<Common> {
                override fun onResponse(call: Call<Common>, response: Response<Common>) {
                    if (response.body()!!.code == 1) {
                        Toast.makeText(this@AllocationActivity, R.string.Allocation_success, Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@AllocationActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Common>, t: Throwable) {
                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(this@AllocationActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this)
        }
    }

    fun hideSoftInput() {
        if (node.text.toString().trim().length < 6) {
            Toast.makeText(this, R.string.Node_Error, Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val nowtime=DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().getUserlogin()
            val getNodeMessage = retrofit.create(MoneyService::class.java!!).getNodeMessage(sp.getString("user_id", ""), sp.getString("user_token", ""), node.text.toString().trim(),"0",nowtime,PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "")+nowtime))
            getNodeMessage.enqueue(object : Callback<GetNodeMessage> {
                override fun onResponse(call: Call<GetNodeMessage>, response: Response<GetNodeMessage>) {
                    if (response.body()!!.code == 1) {
                        StatService.onEvent(this@AllocationActivity, "DetailView.SearchNode", "xms", 1)
                        uName.setText(response.body()!!.data.username)
                        uPhone.setText(response.body()!!.data.phone)
                        uId.setText(response.body()!!.data.insertPointId.toString())
                        if (response.body()!!.data.avatar != null) {
                            Glide.with(this@AllocationActivity).load(response.body()!!.data.avatar).centerCrop().transform(GlideCircleTransform(this@AllocationActivity)).into(uhead)
                        }
                        uLin.visibility = View.VISIBLE
                        sNodeMessage.visibility = View.VISIBLE
                    } else {
                        Toast.makeText(this@AllocationActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<GetNodeMessage>, t: Throwable) {
                    if (t is DataResultException) {
                        val resultException = t as DataResultException
                        Toast.makeText(this@AllocationActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                        /*  Toast.makeText(this@LoginActivity, R.string.Login_error, Toast.LENGTH_SHORT).show()*/
                    }
                }
            })
        } catch (e: Exception) {
            abnormal(this)
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            startActivity(Intent(this@AllocationActivity,CommunityFriendActivity::class.java))
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
