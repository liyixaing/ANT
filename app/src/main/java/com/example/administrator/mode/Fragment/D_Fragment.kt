package com.example.administrator.mode.Fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.mode.Adapter.BannerAdapter
import com.example.administrator.mode.Adapter.SmoothLinearLayoutManager
import com.example.administrator.mode.Fragment.homeFragment.ANTStoreActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import com.example.administrator.mode.Utlis.Encryption
import kotlinx.android.synthetic.main.fragment_d.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class D_Fragment : Fragment() {
    var image = arrayListOf<Int>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_d, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)
        good.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(activity, ANTStoreActivity::class.java)
                /* intent.putExtra("url", "http://www.ttxhwlx.com:8087/index.html#/index/?user_id=" + sp.getString("user_id", "") + "&user_token=" + Encryption.generateFakeTokenToShop(sp.getString("user_token", "")) + "&mall_key=b64ab4b8124e2c5d43d52d9c05a6f992")*/
                intent.putExtra("url", "http://mall.fcsap.com/?user_id=" + sp.getString("user_id", "") + "&user_token=" + Encryption.generateFakeTokenToShop(sp.getString("user_token", "")) + "&mall_key=b64ab4b8124e2c5d43d52d9c05a6f992")
                startActivity(intent)
            }
        })
        blak_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(activity, ANTStoreActivity::class.java)
                intent.putExtra("url", "http://mall.fcsap.com/?user_id=" + sp.getString("user_id", "") + "&user_token=" + Encryption.generateFakeTokenToShop(sp.getString("user_token", "")) + "&mall_key=b64ab4b8124e2c5d43d52d9c05a6f992")
                startActivity(intent)
            }
        })
        allType.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(activity, ANTStoreActivity::class.java)
                intent.putExtra("url", "http://mall.fcsap.com/?user_id=" + sp.getString("user_id", "") + "&user_token=" + Encryption.generateFakeTokenToShop(sp.getString("user_token", "")) + "&mall_key=b64ab4b8124e2c5d43d52d9c05a6f992")
                startActivity(intent)
            }
        })
        member.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(activity, ANTStoreActivity::class.java)
                intent.putExtra("url", "http://mall.fcsap.com/?user_id=" + sp.getString("user_id", "") + "&user_token=" + Encryption.generateFakeTokenToShop(sp.getString("user_token", "")) + "&mall_key=b64ab4b8124e2c5d43d52d9c05a6f992")
                startActivity(intent)
            }
        })
        image.add(R.drawable.mallbanner1)
        image.add(R.drawable.mallbanner2)
        image.add(R.drawable.mallbanner3)
        val adapter = BannerAdapter(activity, image)
        val layoutManager = SmoothLinearLayoutManager(activity, SmoothLinearLayoutManager.HORIZONTAL, false)
        d_recycler.adapter = adapter
        d_recycler.layoutManager = layoutManager
        d_recycler.setHasFixedSize(true)
        d_recycler.scrollToPosition(image.size * 10)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(d_recycler)
        val scheduledExecutorService = Executors.newScheduledThreadPool(1)
        scheduledExecutorService.scheduleAtFixedRate({ d_recycler.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1); }, 5000, 5000, TimeUnit.MILLISECONDS)
        refresh.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(activity, ANTStoreActivity::class.java)
                intent.putExtra("url", refreshUrl.text.toString().trim() + "/?user_id=" + sp.getString("user_id", "") + "&user_token=" + Encryption.generateFakeTokenToShop(sp.getString("user_token", "")) + "&mall_key=b64ab4b8124e2c5d43d52d9c05a6f992")
                startActivity(intent)
            }
        })
    }

}
