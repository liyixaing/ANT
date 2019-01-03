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
import kotlinx.android.synthetic.main.fragment_b.*
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class B_Fragment : Fragment() {
    var xx = 1
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zaa.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (xx == 1) {
                    zaa.setImageResource(R.drawable.eyeclose)
                    monet.text = "****"
                    xx++
                    return
                }
                if (xx == 2) {
                    zaa.setImageResource(R.drawable.eyeopen)
                    /*        monet.setText(String.format("%.2f", postCardInput))*/
                    monet.text = "00.0000"
                    xx--
                    return
                }
            }
        })

        top.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                unopened()
            }
        })
        withdraw.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                unopened()
            }
        })
        detail.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                unopened()
            }
        })
    }

    fun unopened() {
        val attributes = HashMap<String, String>()
        attributes.put("ClickItemName", "1")
        StatService.onEvent(activity, "WalletView.Click", "xms", 1, attributes)
        Toast.makeText(activity, R.string.jianshe, Toast.LENGTH_SHORT).show()
    }
}
