package com.example.administrator.mode.Fragment.homeFragment

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_MEDIA_SCANNER_SCAN_FILE
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.ClickUtlis
import com.example.administrator.mode.Utlis.VerifyUtlis
import kotlinx.android.synthetic.main.activity_service.*
import kotlinx.android.synthetic.main.tit.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class ServiceActivity : BaseActivity() {


    override fun getContentViewId(): Int {
        return R.layout.activity_service
    }

    override fun init() {
        super.init()
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        tit_name.setText(R.string.Service_tit)
        noeService.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                VerifyUtlis.copy(this@ServiceActivity, "ANT-005")
            }
        })

        oneSaveImage.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                save()
            }
        })
        twoService.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                VerifyUtlis.copy(this@ServiceActivity, "gxganhh")
            }
        })
        twoSaveImage.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                save1()
            }
        })


    }

    fun save() {
        /* Code_code.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
         Code_code.layout(0, 0, Code_code.getMeasuredWidth(), Code_code.getMeasuredHeight());
         Code_code.buildDrawingCache();*/
        wcImageView.isDrawingCacheEnabled = true
        val obmp = Bitmap.createBitmap(wcImageView.drawingCache)
        wcImageView.isDrawingCacheEnabled = false
        saveImageToGallery(this@ServiceActivity, obmp!!)
    }

    fun save1() {
        /* Code_code.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
         Code_code.layout(0, 0, Code_code.getMeasuredWidth(), Code_code.getMeasuredHeight());
         Code_code.buildDrawingCache();*/

        qqImageView.isDrawingCacheEnabled = true
        val obmp = Bitmap.createBitmap(qqImageView.drawingCache)
        qqImageView.isDrawingCacheEnabled = false
        saveImageToGallery(this@ServiceActivity, obmp!!)
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
    }
}
