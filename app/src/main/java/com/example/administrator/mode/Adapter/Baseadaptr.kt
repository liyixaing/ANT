package com.example.administrator.kotlin.Adaptr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.administrator.mode.Pojo.Friendtrun
import com.example.administrator.mode.R

class Baseadaptr(context: Context, mList: List<Friendtrun.DataBean.ListBean>) : BaseAdapter() {
    //适配器原型
    private var mList: List<Friendtrun.DataBean.ListBean>? = null
    private var context: Context? = null

    init {
        this.mList = mList
        this.context = context
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var holder: MyViewHolder? = null
        var v: View
        if (p1 == null) {
            holder = MyViewHolder()
            v = LayoutInflater.from(context).inflate(R.layout.friendlayout, p2, false)
            holder.textView = v.findViewById(R.id.friend_name) as TextView
            holder.textView1 = v.findViewById(R.id.friend_phone) as TextView
            holder.textView2 = v.findViewById(R.id.friend_card) as TextView
            v.tag = holder
        } else {
            v = p1
            holder = v.tag as MyViewHolder
        }
        holder.textView.text = mList!![p0].username
        val bb = mList!![p0].phone
        holder.textView1.text = bb
        if (mList!![p0].parentNode.toString().equals("0")) {
            holder.textView2.text = "未激活"
        } else {
            holder.textView2.text = "已激活"
        }
        return v
    }

    override fun getItem(p0: Int): Any {
        return mList!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return mList!!.size
    }

    class MyViewHolder {
        lateinit var textView: TextView
        lateinit var textView1: TextView
        lateinit var textView2: TextView
    }
}


