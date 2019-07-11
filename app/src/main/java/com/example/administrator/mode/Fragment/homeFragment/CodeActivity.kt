package com.example.administrator.mode.Fragment.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Environment
import android.view.View
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import kotlinx.android.synthetic.main.activity_code.*
import kotlinx.android.synthetic.main.tit.*
import android.content.Intent.ACTION_MEDIA_SCANNER_SCAN_FILE
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Utlis.PreferencesUtil
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.HashMap


class CodeActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_code
    }

    override fun init() {
        StatService.onPageEnd(this@CodeActivity, "MainModule.HomeView.ReceiptView")
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setText(R.string.Code_tit)
        code_save.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                save()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        if (PreferencesUtil.get("language", "") == "zh") {
            //实例化一个SharedPreferences对象
            code_username.text = "昵称:" + sp.getString("user_name", "")
            code_userphone.text = "账号:" + sp.getString("user_phone", "")
            iddd.text = "ID:" + sp.getString("user_id", "")
        } else if (PreferencesUtil.get("language", "") == "en") {
            //实例化一个SharedPreferences对象
            code_username.text = "Name:" + sp.getString("user_name", "")
            code_userphone.text = "Phone:" + sp.getString("user_phone", "")
            iddd.text = "ID:" + sp.getString("user_id", "")
        } else {
            //实例化一个SharedPreferences对象
            code_username.text = "昵称:" + sp.getString("user_name", "")
            code_userphone.text = "账号:" + sp.getString("user_phone", "")
            iddd.text = "ID:" + sp.getString("user_id", "")
        }

        val bitmap = generateBitmap(sp.getString("user_phone", ""), 900, 900)
        Code_code.setImageBitmap(bitmap)
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

    fun save() {
        /* Code_code.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
         Code_code.layout(0, 0, Code_code.getMeasuredWidth(), Code_code.getMeasuredHeight());
         Code_code.buildDrawingCache();*/

        Code_code.isDrawingCacheEnabled = true
        val obmp = Bitmap.createBitmap(Code_code.drawingCache)
        Code_code.isDrawingCacheEnabled = false
        saveImageToGallery(this@CodeActivity, obmp!!)
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

        val intent = Intent(ACTION_MEDIA_SCANNER_SCAN_FILE)
        val uri = Uri.fromFile(file)
        intent.data = uri
        context.sendBroadcast(intent)
        Toast.makeText(this, R.string.Code_ok, Toast.LENGTH_SHORT).show()
        finish()
    }
}
