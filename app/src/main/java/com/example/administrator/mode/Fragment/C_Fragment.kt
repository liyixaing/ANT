package com.example.administrator.mode.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import kotlinx.android.synthetic.main.fragment_c.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class C_Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        friend.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        relative_comm.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        T2.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        home_card.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        refuel.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        book.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        telephone.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        taking.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        game.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        take.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        information.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
        more1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                onchid()
            }
        })
    }
    fun onchid() {
        val attributes = HashMap<String, String>()
        attributes.put("ClickItemName", "1")
        StatService.onEvent(activity, "C2CView.Click", "xms",1, attributes)
        Toast.makeText(activity,  R.string.jianshe, Toast.LENGTH_SHORT).show()
    }
}
