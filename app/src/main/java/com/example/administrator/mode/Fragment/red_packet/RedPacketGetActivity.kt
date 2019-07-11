package com.example.administrator.mode.Fragment.red_packet

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.baidu.location.BDLocation
import com.example.administrator.mode.Activity.Announcement
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.DrawSingleEnvelope
import com.example.administrator.mode.Pojo.GetRedBag
import com.example.administrator.mode.Pojo.geSingleEnvelopeList
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_redpacket.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.util.ArrayList


class RedPacketGetActivity : BaseActivity(), SensorEventListener {
    val num = 0
    var isPlayer = true
    var xxaa = false
    override fun getContentViewId(): Int {
        return R.layout.activity_redpacket
    }

    override fun init() {
        initView()
        getRedRecord()
        redRule.setOnClickListener {
            val payDialog = Announcement(this)
            payDialog.redPacketGetActivity = this@RedPacketGetActivity
            payDialog.show()
        }
        val animation = AnimationUtils.loadAnimation(this, R.anim.whirl)
        whirl.startAnimation(animation)
        tit_iv1.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@RedPacketGetActivity, RedBagDetailActivity::class.java))
            }
        })
        tit_iv11.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        ordinaryRed.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                val intent = Intent(this@RedPacketGetActivity, OrdinaryRedEnvelopesActivity::class.java)
                intent.putExtra("userFirendPhone", "")
                startActivity(intent)
            }
        })
        regionRed.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                startActivity(Intent(this@RedPacketGetActivity, RredEnvelopeActivity::class.java))
            }
        })
    }

    private fun getRedRecord() {
        val nowtime = DateUtils.getdata()
        val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val retrofit = Retrofit_manager.getInstance().userlogin
        val login = retrofit.create(MoneyService::class.java).geSingleEnvelopeList(sp.getString("user_id", ""), "0", num.toString(), "10", nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
        login.enqueue(object : Callback<geSingleEnvelopeList> {
            override fun onResponse(call: Call<geSingleEnvelopeList>, response: Response<geSingleEnvelopeList>) {
                if (response.body()!!.code == 1) {
                    val zz = arrayListOf<geSingleEnvelopeList.DataBean>()
                    val fe: ArrayList<geSingleEnvelopeList.DataBean>? = response.body()!!.data as ArrayList<geSingleEnvelopeList.DataBean>?
                    for (xx in fe!!) {
                        if (xx.status == 0) {
                            zz.add(xx)
                        }
                    }
                    if (zz.size > 0) {

                        if (PreferencesUtil.get("language", "") == "en") {
                             dasd.setImageResource(R.mipmap.sdadazxdasdzx)
                        }
                        dasd.visibility = View.VISIBLE
                        dasd.setOnClickListener { showCardGet2(zz[0].amountScore, zz[0].id, zz[0].memo) }

                    } else {
                        xxaa = true
                    }
                } else {
                    xxaa = true
                    Toast.makeText(this@RedPacketGetActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<geSingleEnvelopeList>, t: Throwable) {
                if (t is DataResultException) {
                    Toast.makeText(this@RedPacketGetActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    xxaa = true
                }
            }
        })
    }

    private fun drawRed(id: Int) {
        try {
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(MoneyService::class.java).drawSingleEnvelope(sp.getString("user_id", ""), id.toString(), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
            register.enqueue(object : Callback<DrawSingleEnvelope> {
                override fun onResponse(call: Call<DrawSingleEnvelope>, response: Response<DrawSingleEnvelope>) {
                    if (response.body()!!.code == 1) {
                        getRedRecord()
                        Toast.makeText(this@RedPacketGetActivity, getString(R.string.Toreceivethe) + response.body()!!.data.score.toString() + "资产", Toast.LENGTH_SHORT).show()
                    } else {
                    }
                }

                override fun onFailure(call: Call<DrawSingleEnvelope>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@RedPacketGetActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: java.lang.Exception) {

        }
    }

    private fun initView() {
        //加速度传感器
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


    override fun onSensorChanged(event: SensorEvent?) {
        try {
            if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                if (num != 0) {
                    return
                }
                val x = event.values[0]//x轴的加速度
                val y = event.values[1]//y轴的加速度
                val z = event.values[2]//z轴的加速度

                //静止状态下加速度为9.8,当加速度>15的时候就表示在摇晃手机
                if (x > 20 || y > 20 || z > 20) {
                    if (!xxaa) {
                        return
                    }

                    val animation = RotateAnimation(-10f, 10f, 1, 0.5f, 1, 0.5f)
                    animation.duration = 200
                    animation.repeatCount = 4//动画的重复次数
                    animation.repeatMode = Animation.REVERSE
                    animation.setAnimationListener(object : Animation.AnimationListener {

                        override fun onAnimationStart(animation: Animation) {
                            try {
                                if (isPlayer) {
                                    //播放 assets/a2.mp3 音乐文件
                                    val fd = assets.openFd("5018.mp3")
                                    val mediaPlayer = MediaPlayer()
                                    mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
                                    mediaPlayer.prepare()
                                    mediaPlayer.start()
                                    isPlayer = false
                                }
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }

                        override fun onAnimationEnd(animation: Animation) {

                            isShowGetRed()
                        }

                        override fun onAnimationRepeat(animation: Animation) {
                        }
                    })
                    ant_phone.startAnimation(animation)//开始动画
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, R.string.DoNotOperatefrequently, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showCardGet2(score: Double, id: Int, memo: String) {
        /*ant_phone.setImageResource(R.drawable.ant_phone)*/


        if (PreferencesUtil.get("language", "") == "en") {
            xzczeew.setBackgroundResource(R.mipmap.fdsdawe)
        }
        dasd.visibility = View.GONE
        xzczeew.visibility = View.VISIBLE
        getCard.text = String.format("%.2f", score)
        getLeave.text = memo
        /* ant_phone.setImageResource(R.drawable.red_get)*/
        xzczeew.setOnClickListener {
            drawRed(id)
            xzczeew.visibility = View.GONE
        }
    }

    private fun showCardGet(score: Double, memo: String) {
        getCard.text = String.format("%.2f", score)
        if (PreferencesUtil.get("language", "") == "en") {
            xzczeew.setBackgroundResource(R.mipmap.fdsdawe)
        }
        dasd.visibility = View.GONE
        xzczeew.visibility = View.VISIBLE
        getLeave.text = memo
        xzczeew.setOnClickListener {
            Toast.makeText(this@RedPacketGetActivity, getString(R.string.Toreceivethe) + String.format("%.2f", score), Toast.LENGTH_SHORT).show()
            xzczeew.visibility = View.GONE
        }
    }

    private fun isShowGetRed() {
        try {
            if (!isLocationEnabled()) {
                Toast.makeText(this@RedPacketGetActivity, R.string.RedEnvelopeService, Toast.LENGTH_SHORT).show()
                return
            }
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val baiduLocationUtil = BaiduLocationUtil(this, object : BaiduLocationUtil.IGetLocation {
                override fun getLocationSuccess(bdLocation: BDLocation?) {
                    val register = retrofit.create(MoneyService::class.java).drawPeopleEnvelope(sp.getString("user_id", ""), bdLocation!!.longitude.toString(), bdLocation.latitude.toString(), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
                    register.enqueue(object : Callback<GetRedBag> {
                        override fun onResponse(call: Call<GetRedBag>, response: Response<GetRedBag>) {
                            if (response.body()!!.code == 1) {
                                /*    ant_phone.setImageResource(R.drawable.red_open_card)*/

                                if (PreferencesUtil.get("language", "") == "en") {
                                    dasd.setImageResource(R.mipmap.sdadazxdasdzx)
                                }
                                dasd.visibility = View.VISIBLE
                                dasd.setOnClickListener { showCardGet(response.body()!!.data.score,response.body()!!.data.memo) }
                            } else {
                            }
                        }

                        override fun onFailure(call: Call<GetRedBag>, t: Throwable) {
                            val resultException = t as DataResultException
                            Toast.makeText(this@RedPacketGetActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    })
                }

                override fun getLocationError() {
                    Toast.makeText(this@RedPacketGetActivity, R.string.RedEnvelopeService, Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
        } finally {
            isPlayer = true
        }
    }

    fun isLocationEnabled(): Boolean {
        val locationMode: Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(contentResolver, Settings.Secure.LOCATION_MODE)
            } catch (e: Exception) {
                return false
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF
        } else {
            val locationProviders = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders)
        }
    }
}
