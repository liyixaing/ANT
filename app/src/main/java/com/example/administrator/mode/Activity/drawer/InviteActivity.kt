package com.example.administrator.mode.Activity.drawer

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import com.example.administrator.mode.Utlis.VerifyUtlis
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_invite.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.HashMap

class InviteActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_invite
    }

    override fun init() {
        super.init()
        StatService.onPageStart(this,"MainModule.HomeView.InvitingCardView")
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        //获得一个SharedPreferences编辑器
        Invite_yard.text = sp.getString("user_id", "")
        tit_iv22.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                StatService.onPageEnd(this@InviteActivity,"MainModule.HomeView.InvitingCardView")
                finish()
            }
        })
        fasdacxz.setOnClickListener(object :ClickUtlis(){
            override fun onMultiClick(v: View?) {
                VerifyUtlis.copy(this@InviteActivity, sp.getString("user_id", ""))
            }
        })
        tit_name22.setText(R.string.Invite_tit)
        val bitmap = generateBitmap(sp.getString("versionwebUrl", ""), 300, 300)
        invitation.setImageBitmap(bitmap)
        tit_iv3211.setOnClickListener(object :ClickUtlis(){
            override fun onMultiClick(v: View?) {
                val  sss = viewConversionBitmap11(czc)
                saveImageToGallery(this@InviteActivity, sss)
            }
        })
    }

    fun viewConversionBitmap11(v: View): Bitmap {
        val w = v.width
        val h = v.height
        val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val c = Canvas(bmp)
        v.layout(0, 0, w, h)
        v.draw(c)
        return bmp
    }

    fun saveImageToGallery(context: Context, bmp: Bitmap) {
        // 首先保存图片
        val appDir = File(Environment.getExternalStorageDirectory(), "ANT")
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val fileName = "sign_" + System.currentTimeMillis() + ".jpg"
        val file = File(appDir, fileName)
        try {
            val fos = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val path = file.absolutePath
        try {
            MediaStore.Images.Media.insertImage(context.contentResolver, path, fileName, null)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val uri = Uri.fromFile(file)
        intent.data = uri
        context.sendBroadcast(intent)
        Toast.makeText(this, R.string.Code_ok, Toast.LENGTH_SHORT).show()
        finish()
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
