package com.example.administrator.mode.Activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.support.v4.content.ContextCompat
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.eightbitlab.rxbus.Bus
import com.example.administrator.mode.Activity.drawer.*
import com.example.administrator.mode.Fragment.A_Fragment
import com.example.administrator.mode.Fragment.B_Fragment
import com.example.administrator.mode.Fragment.C_Fragment
import com.example.administrator.mode.Fragment.D_Fragment
import com.example.administrator.mode.Fragment.homeFragment.TransferAccountsActivity
import com.example.administrator.mode.Interface.GitHubService
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.KeyAddressBean
import com.example.administrator.mode.Pojo.ResponseBodytu
import com.example.administrator.mode.Pojo.prturn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.app.MyApplication
import com.example.administrator.mode.event.UserHeadEvent
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*

class MainActivity : BaseActivity() {

    var username = ""
    private var xiangpiandizhi = ""
    private var tempFile: File? = null
    private var uritempFile: Uri? = null
    private var image: Bitmap? = null
    var s = ""
    var num = 1
    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    private var mExitTime: Long = 0
    override fun init() {
        super.init()
        StatService.onPageStart(this@MainActivity, "MainModule.HomeView")
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        adaptive.setContainer(R.id.sumframelayout)
                .setTitleBeforeAndAfterColor("#999999", "#00B07C")
                .addItem(A_Fragment::class.java, PreferencesUtil.get("home", ""), R.mipmap.souye, R.mipmap.lvv)
                .addItem(B_Fragment::class.java, PreferencesUtil.get("wallet", ""), R.mipmap.qianbaoicon, R.mipmap.lvl)
                .addItem(C_Fragment::class.java, PreferencesUtil.get("deal", ""), R.mipmap.chat_maininput, R.mipmap.chat_im)
                .addItem(D_Fragment::class.java, PreferencesUtil.get("mall", ""), R.mipmap.shangdianicon, R.mipmap.luluuu)
                .build()
        //小红点
        /*  val badgeView = BadgeView(this)
          badgeView.setTargetView(choutizhanshi)
          badgeView.setBadgeCount(2)*/
        announcementShow.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                initAnnouncement()
            }
        })
        val versionsInput = VerifyUtlis.getAppVersionName(this@MainActivity)
    /*    choutizhanshi.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                drlayout.openDrawer(drawer_content)
            }
        })*/
    }


    fun loadpr() {
        val retrofit = Retrofit_manager.getInstance().userlogin
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val token = sp.getString("user_token", "")
        val id = sp.getString("user_id", "")
        val nowtime = DateUtils.getdata()
        try {
            val login = retrofit.create(MoneyService::class.java).paroper(id, token, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            login.enqueue(object : Callback<prturn> {
                override fun onResponse(call: Call<prturn>, response: Response<prturn>) {
                    try {
                        MyApplication.isVip=response.body()!!.data!!.isVip
                        MyApplication.isMerchant=response.body()!!.data!!.isMerchant
                    } catch (e: Exception) {
                    }
                }

                override fun onFailure(call: Call<prturn>, t: Throwable) {

                }
            })
        } catch (e: Exception) {

        }
    }

    override fun onStart() {
        super.onStart()
        loadpr()
    }

    fun initAnnouncement() {
        //这里要改
        startActivity(Intent(this@MainActivity, MessageListActivity::class.java))
    }

    private fun checkPermissionAllGranted(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == VerifyUtlis.MY_PERMISSION_REQUEST_CODE) {
            var isAllGranted = true
            for (grant in grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false
                    break
                }
            }
            //如果有权限的话
            if (isAllGranted) {
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                startActivityForResult(photoPickerIntent, VerifyUtlis.ALBUM_REQUEST_CODE)
            } else {
                openAppDetails()
            }
        }
    }

    private fun openAppDetails() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.Welcome_hint)
        builder.setPositiveButton(R.string.Welcome_authority) { _, _ ->
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.data = Uri.parse("package:$packageName")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            startActivity(intent)
        }
        builder.setNegativeButton(R.string.Welcome_error, null)
        builder.show()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                Toast.makeText(this, R.string.gobark, Toast.LENGTH_SHORT).show()
                StatService.onPageEnd(this@MainActivity, "MainModule.HomeView")
                mExitTime = System.currentTimeMillis()
            } else {
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode > 10) {
            val aa = requestCode - 65536 * num
            if (aa == 9527) {
                try {
                    val result = intent!!.getStringExtra("barCode")
                    val intent = Intent(this@MainActivity, TransferAccountsActivity::class.java)
                    intent.putExtra("userkey", result.toString())
                    startActivity(intent)
                } catch (e: Exception) {
                } finally {
                    num++
                }
            }
        }
        when (requestCode) {
            VerifyUtlis.ALBUM_REQUEST_CODE
            -> if (resultCode == RESULT_OK) try {
                val uri = intent!!.data
                val proj = arrayOf(MediaStore.Images.Media.DATA)
                val actualimagecursor = managedQuery(uri, proj, null, null, null)
                val actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                actualimagecursor.moveToFirst()
                val img_path = actualimagecursor.getString(actual_image_column_index)
                tempFile = File(img_path)

                cropPhoto(uri)
            } catch (e: Exception) {

            }
            VerifyUtlis.CROP_REQUEST_CODE -> try {

                val bundle = intent!!.extras
                if (bundle != null) {
                    if (resultCode == RESULT_OK) {
                        image = bundle.getParcelable("data")
                        val getimage = getimage(tempFile!!.path)
                        s = this.saveImage(tempFile!!.name, getimage)!!
                        val f = File(s)
                        val imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), f)
                        val builder = MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("avatar", f.name, imageBody)
                        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                        val retrofit = Retrofit_manager.getInstance().userlogin
                        val parts = builder.build().parts()
                        val nowtime = DateUtils.getdata()
                        val responseBodytuCall = retrofit.create(GitHubService::class.java).hadeup(sp.getString("user_id", ""), sp.getString("user_token", ""), parts, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
                        responseBodytuCall.enqueue(object : Callback<ResponseBodytu> {
                            override fun onResponse(call: Call<ResponseBodytu>, response: Response<ResponseBodytu>) {
                                if (response.body()!!.code == 1) {
                                    xiangpiandizhi = response.body()!!.data.toString()
                                    headup()
                                }
                            }

                            override fun onFailure(call: Call<ResponseBodytu>, t: Throwable) {
                                val resultException = t as DataResultException
                                Toast.makeText(this@MainActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        })
                    } else {
                        Toast.makeText(this, R.string.Welcome_error, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    if (resultCode == RESULT_OK) {
                        image = BitmapFactory.decodeStream(contentResolver.openInputStream(uritempFile))
                        val getimage = getimage(tempFile!!.getPath())
                        s = saveBitmapByQuality(getimage, 80)
                        val f = File(s)
                        val imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), f)
                        val builder = MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("avatar", f.getName(), imageBody)
                        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
                        val retrofit = Retrofit_manager.getInstance().getUserlogin()
                        val nowtime = DateUtils.getdata()
                        val parts = builder.build().parts()
                        val responseBodytuCall = retrofit.create(GitHubService::class.java!!).hadeup(sp.getString("user_id", ""), sp.getString("user_token", ""), parts, "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
                        responseBodytuCall.enqueue(object : Callback<ResponseBodytu> {
                            override fun onResponse(call: Call<ResponseBodytu>, response: Response<ResponseBodytu>) {
                                if (response.body()!!.getCode() == 1) {
                                    xiangpiandizhi = response.body()!!.getData().toString()
                                    headup()
                                }
                            }

                            override fun onFailure(call: Call<ResponseBodytu>, t: Throwable) {
                                val resultException = t as DataResultException
                                Toast.makeText(this@MainActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        })
                    } else {
                        Toast.makeText(this, R.string.Welcome_error, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this, R.string.Welcome_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveBitmapByQuality(bm: Bitmap, quality: Int): String {
        var croppath = ""
        try {
            val f = File(FileUtil.generateImgePathInStoragePath())
            croppath = f.path
            if (f.exists()) {
                f.delete()
            }
            val out = FileOutputStream(f)
            bm.compress(Bitmap.CompressFormat.JPEG, quality, out)
            out.flush()
            out.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return croppath
    }

    fun headup() {
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        try {
            val nowtime = DateUtils.getdata()
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(GitHubService::class.java).userup(sp.getString("user_id", ""), xiangpiandizhi, "", sp.getString("user_token", ""), "0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime))
            register.enqueue(object : Callback<Common> {
                override fun onResponse(call: Call<Common>, response: Response<Common>) {
                    if (response.body()!!.code == 1) {
                        val edit = sp.edit()
                        edit.putString("useravatar", xiangpiandizhi)
                        edit.commit()
                        val getHash = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java)
                        getHash.remove(MyApplication.keyAddressBeans.walletName + MyApplication.keyAddressBeans.userId)
                        MyApplication.keyAddressBeans.userHead = xiangpiandizhi
                        getHash[MyApplication.keyAddressBeans.walletName + MyApplication.keyAddressBeans.userId] = MyApplication.keyAddressBeans
                        Bus.send(UserHeadEvent(xiangpiandizhi))
                        SharedPreferencesUtil.putHashMapData("keyAddress", getHash)
                        StatService.onEvent(this@MainActivity, "View.ModifyAvata", "xms", 1)
                        Toast.makeText(this@MainActivity, R.string.Property_suess, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Common>, t: Throwable) {
                    if (t is DataResultException) {
                        Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
        } finally {
        }

    }

    private fun cropPhoto(uri: Uri) {
        val intent = Intent("com.android.camera.action.CROP")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.setDataAndType(uri, "image/*")
        intent.putExtra("crop", "true")
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        intent.putExtra("outputX", 50)
        intent.putExtra("outputY", 50)
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
        intent.putExtra("return-data", true)
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().path + "/" + "ant.jpg")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile)
        startActivityForResult(intent, VerifyUtlis.CROP_REQUEST_CODE)
    }

    private fun getimage(srcPath: String): Bitmap {
        val newOpts = BitmapFactory.Options()
        newOpts.inJustDecodeBounds = true
        var bitmap = BitmapFactory.decodeFile(srcPath, newOpts)//此时返回bm为空
        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        val hh = 200f
        val ww = 200f
        var be = 1//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (newOpts.outWidth / ww).toInt()
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (newOpts.outHeight / hh).toInt()
        }
        if (be <= 0)
            be = 1
        newOpts.inSampleSize = be//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts)
        return compressImage(bitmap)//压缩好比例大小后再进行质量压缩
    }

    private fun compressImage(image: Bitmap): Bitmap {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 50, baos)//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 50
        while (baos.toByteArray().size / 1024 > 50) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset()//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos)//这里压缩options%，把压缩后的数据存放到baos中
            options -= 50//每次都减少10
        }
        val isBm = ByteArrayInputStream(baos.toByteArray())//把压缩后的数据baos存放到ByteArrayInputStream中
        val bitmap = BitmapFactory.decodeStream(isBm, null, null)//把ByteArrayInputStream数据生成图片
        return bitmap
    }

    fun saveImage(name: String, bmp: Bitmap): String? {
        val appDir = File(Environment.getExternalStorageDirectory().path)
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val fileName = name
        val file = File(appDir, fileName)
        try {
            val fos = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.flush()
            fos.close()
            return file.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }
}
