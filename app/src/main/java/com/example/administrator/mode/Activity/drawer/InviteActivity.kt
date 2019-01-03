package com.example.administrator.mode.Activity.drawer

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_invite.*
import kotlinx.android.synthetic.main.tit.*
import java.util.HashMap

class InviteActivity : BaseActivity() {
    var inviteinput: String = ""
    override fun getContentViewId(): Int {
        return R.layout.activity_invite
    }

    override fun onStart() {
        super.onStart()
    }

    override fun init() {
        super.init()
        StatService.onPageStart(this,"MainModule.HomeView.InvitingCardView")
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        //获得一个SharedPreferences编辑器
        val edit = sp.edit()
        Invite_yard.setText(sp.getString("user_id", ""))
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                StatService.onPageEnd(this@InviteActivity,"MainModule.HomeView.InvitingCardView")
                finish()
            }
        })

        tit_name.setText(R.string.Invite_tit)
        val bitmap = generateBitmap(sp.getString("versionwebUrl", ""), 380, 380)
        invitation.setImageBitmap(bitmap)
    }

    private fun generateBitmap(content: String, width: Int, height: Int): Bitmap? {
        val qrCodeWriter = QRCodeWriter()
        val hints = HashMap<EncodeHintType, String>()
        hints[EncodeHintType.CHARACTER_SET] = "utf-8"
        try {
            val encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints)
            val pixels = IntArray(width * height)
            for (i in 0 until height) {
                for (j in 0 until width) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000
                    } else {
                        pixels[i * width + j] = -0x1
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return null
    }
}
