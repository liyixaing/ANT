package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import com.example.administrator.mode.Utlis.VerifyUtlis
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_merchant_qr_code.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.HashMap

class MerchantQrCodeActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_merchant_qr_code
    }

    @SuppressLint("SetTextI18n")
    override fun init() {
        super.init()
        val aa = intent.extras.getString("aa")
        val bb = intent.extras.getString("bb")
        val cc = intent.extras.getString("cc")
        val dd = intent.extras.getString("dd")
        val ee = intent.extras.getString("ee")
        savaPhone11.setBackgroundDrawable(resources.getDrawable(R.drawable.lmageview_frame))
        ewqe.setTextColor(Color.parseColor("#1d2d35"))
        xczxcz.setTextColor(Color.parseColor("#1d2d35"))
        phoneMall.setTextColor(Color.parseColor("#1d2d35"))
        phone11Mall.setTextColor(Color.parseColor("#1d2d35"))
        phoneMall.text = aa
        ewqe.text = bb
        fsaz.text = String.format("%.2f", cc.toDouble())
        phone11Mall.text = dd
        vxzcvz.text = "      $ee"

        /*      val bitmap = generateBitmap("$aa,$cc", mallCode)   */
        val logoBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ewewe)
        val bitmap = VerifyUtlis.createQRCodeBitmap("$aa,$cc", 800, 800, "UTF-8", "H", "1", Color.BLACK, Color.WHITE, logoBitmap, 0.2F)
        mallCode.setImageBitmap(bitmap)
        xzca.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        saveImage.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                var sss: Bitmap? = null
                if (intent.extras.getString("aaaaaa") == "cai") {
                    sss = viewConversionBitmap11(savaPhone)
                } else if (intent.extras.getString("aaaaaa") == "hei") {
                    sss = viewConversionBitmap(savaPhone)
                }
                saveImageToGallery(this@MerchantQrCodeActivity, sss!!)
            }
        })
    }

    private fun generateBitmap(content: String, view: View): Bitmap? {

        val width = view.width
        val height = view.height

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

    fun viewConversionBitmap(v: View): Bitmap {
        val w = v.width
        val h = v.height
        val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val c = Canvas(bmp)
        c.drawColor(Color.WHITE)
        v.layout(0, 0, w, h)
        v.draw(c)
        return bmp
    }

    fun viewConversionBitmap11(v: View): Bitmap {
        val w = v.width
        val h = v.height
        val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val c = Canvas(bmp)
        c.drawColor(resources.getColor(R.color.appbackground))
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
}
