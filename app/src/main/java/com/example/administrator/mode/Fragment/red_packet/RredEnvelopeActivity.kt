package com.example.administrator.mode.Fragment.red_packet

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.example.administrator.mode.Activity.BaseActivity
import com.example.administrator.mode.Activity.DataResultException
import com.example.administrator.mode.Fragment.homeFragment.PayDialog
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.Common
import com.example.administrator.mode.Pojo.GetRedBagMessage
import com.example.administrator.mode.Pojo.GetState
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import com.example.administrator.mode.app.MyApplication
import com.example.administrator.mode.creatorprivatekey.MessageSignUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_rred_envelope.*
import org.json.JSONArray
import org.web3j.crypto.Credentials
import org.web3j.crypto.ECKeyPair
import org.web3j.utils.Numeric
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class RredEnvelopeActivity : BaseActivity() {
    var redEnvelopeMax = 0
    var redEnvelopeMin = 0
    var RedBagtype = 2
    var province: String? = ""
    var town: String? = ""
    var district: String? = ""
    var selectCode = HashMap<String, String>()
    private var options1Items: List<JsonBean> = ArrayList()
    private val options2Items = ArrayList<ArrayList<String>>()
    private val options3Items = ArrayList<ArrayList<ArrayList<String>>>()
    private var isLoaded = false
    private var thread: Thread? = null
    private val MSG_LOAD_DATA = 0x0001
    private val MSG_LOAD_SUCCESS = 0x0002
    private val MSG_LOAD_FAILED = 0x0003

    override fun getContentViewId(): Int {
        return R.layout.activity_rred_envelope
    }

    override fun init() {
        initView()
        mHandler.sendEmptyMessage(MSG_LOAD_DATA)
        getRegion.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (isLoaded) {
                    showPickerView()
                } else {
                    Toast.makeText(this@RredEnvelopeActivity, R.string.RedEnvelopeLoad, Toast.LENGTH_SHORT).show()
                }
            }
        })
        tit_iven.setOnClickListener { finish() }
        sendTerritory.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                if (!territoryRedBag.text.isNotEmpty() || !redEnvelopSize.text.isNotEmpty()) {
                    Toast.makeText(this@RredEnvelopeActivity, R.string.RedEnvelopeNumberError, Toast.LENGTH_SHORT).show()
                    return
                }
                if (territoryRedBag.text.toString().trim().toInt() > redEnvelopeMax || territoryRedBag.text.toString().trim().toInt() < redEnvelopeMin) {
                    Toast.makeText(this@RredEnvelopeActivity, getString(R.string.amounTintervalShall) + "$redEnvelopeMin--$redEnvelopeMax", Toast.LENGTH_SHORT).show()
                    return
                }
                if (isImmobilization.isChecked && isRandom.isChecked) {
                    Toast.makeText(this@RredEnvelopeActivity, R.string.RedEnvelopeTypeError, Toast.LENGTH_SHORT).show()
                    return
                }
                if (isImmobilization.isChecked) {
                    RedBagtype = 1
                }
                if (isRandom.isChecked) {
                    RedBagtype = 0
                }
                if (RedBagtype == 2) {
                    return
                }
                val payDialog = PayDialog(this@RredEnvelopeActivity)
                payDialog.setRredEnvelopeActivity(this@RredEnvelopeActivity, getString(R.string.AffirmDeal_tit))
                payDialog.show()
            }
        })
        territoryRedBag.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(e: Editable?) {
                try {
                    territoryRedBagInput.text = String.format("%.2f", territoryRedBag.text.toString().toDouble()) + getString(R.string.RedEnvelopeProperty)
                } catch (e: Exception) {
                    territoryRedBagInput.text = "00.00"
                }
            }
        })
    }


    private fun initView() {
        try {
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(MoneyService::class.java).getEnvelopeConfigInfo(sp.getString("user_id", ""), "5", nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
            register.enqueue(object : Callback<GetRedBagMessage> {
                override fun onResponse(call: Call<GetRedBagMessage>, response: Response<GetRedBagMessage>) {

                    if (response.body()!!.code == 1) {
                        redEnvelopeMax = response.body()!!.data.ant_red_envolope_territory_score_max_value.toInt()
                        redEnvelopeMin = response.body()!!.data!!.ant_red_envolope_territoty_score_min_value.toInt()
                    }
                }

                override fun onFailure(call: Call<GetRedBagMessage>, t: Throwable) {
                    Log.i("wdhawd", t.message)
                    val resultException = t as DataResultException
                    Toast.makeText(this@RredEnvelopeActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {

        }
    }

    fun OnInputPayPasswrodSuccess(str: String) {
        if (MyApplication.keyAddressBeans.userPrivatelyKey != str) {
            Toast.makeText(this, R.string.pwdInputError, Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            Log.i("wadaw",province+town+district)
            val keyPair = ECKeyPair.create(Numeric.hexStringToByteArray(SharedPreferencesUtil.getData("userPrivatelyKey", "").toString()))
            val register = retrofit.create(MoneyService::class.java).sendPeopleQuantityEnvelope(sp.getString("user_id", ""), "", RedBagtype.toString(), redEnvelopSize.text.toString().trim(), province, town, district, territoryRedBag.text.toString().trim(), redEnvelopMessage.text.toString().trim(), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0", MessageSignUtils.Sign(Credentials.create(keyPair), Numeric.toHexString(VerifyUtlis.toHash(nowtime + sp.getString("user_token", ""))).substring(2, Numeric.toHexString(VerifyUtlis.toHash(nowtime + sp.getString("user_token", ""))).length)))
            register.enqueue(object : Callback<Common> {
                override fun onResponse(call: Call<Common>, response: Response<Common>) {
                    if (response.body()!!.code == 1) {
                        Toast.makeText(this@RredEnvelopeActivity, R.string.SSM, Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<Common>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@RredEnvelopeActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {

        }
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_LOAD_DATA -> if (thread == null) {//如果已创建就不再重新创建子线程了
                    thread = Thread(Runnable {
                        // 子线程中解析省市区数据
                        initJsonData()
                    })
                    thread!!.start()
                }

                MSG_LOAD_SUCCESS -> {
                    isLoaded = true
                }

                MSG_LOAD_FAILED -> {

                }
            }
        }
    }

    fun parseData(result: String): ArrayList<JsonBean> {//Gson 解析
        val detail = ArrayList<JsonBean>()
        try {
            val data = JSONArray(result)
            val gson = Gson()
            for (i in 0 until data.length()) {
                val entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean::class.java)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED)
        }
        return detail
    }

    fun initJsonData1() {
        val JsonData: String
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         */
        if (PreferencesUtil.get("language", "") == "zh") {
            JsonData = GetJsonDataUtil().getJson(this, "province.json")//获取assets目录下的json文件数据
        } else if (PreferencesUtil.get("language", "") == "en") {
            JsonData = GetJsonDataUtil().getJson(this, "province_en.json")//获取assets目录下的json文件数据
        } else {
            JsonData = GetJsonDataUtil().getJson(this, "province.json")//获取assets目录下的json文件数据
        }
        val jsonBean = parseData(JsonData)//用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean

        for (i in jsonBean.indices) {//遍历省份
            val cityList = ArrayList<String>()//该省的城市列表（第二级）
            val province_AreaList = ArrayList<ArrayList<String>>()//该省的所有地区列表（第三极）

            for (c in 0 until jsonBean[i].cityList.size) {//遍历该省份的所有城市
                val cityName = jsonBean[i].cityList[c].name
                cityList.add(cityName)//添加城市
                val city_AreaList = ArrayList<String>()//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean[i].cityList[c].area)
                province_AreaList.add(city_AreaList)//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(cityList)

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList)
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS)
    }

    fun initJsonData() {
        try {
            val nowtime = DateUtils.getdata()
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val retrofit = Retrofit_manager.getInstance().userlogin
            val register = retrofit.create(MoneyService::class.java).getEnvelopeArea(sp.getString("user_id", ""), nowtime, sp.getString("user_token", ""), SignatureUtil.signtureByPrivateKey(sp.getString("user_token", "") + nowtime), PreferencesUtil.get("language", ""), "0")
            register.enqueue(object : Callback<GetState> {
                override fun onResponse(call: Call<GetState>, response: Response<GetState>) {
                    if (response.body()!!.code == 1) {
                        for (i in response.body()!!.data) {
                            selectCode[i.name] = i.countryCode
                        }
                        for (j in response.body()!!.data[0].provinces) {
                            selectCode[j.areaDesc] = j.areaCode
                        }
                        for (z in response.body()!!.data[0].provinces.size - 1 downTo 0) {
                            for (i in response.body()!!.data[0].provinces[z].citys) {
                                selectCode[i.areaDesc] = i.areaCode
                            }
                        }
                        initJsonData1()
                    }
                }

                override fun onFailure(call: Call<GetState>, t: Throwable) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@RredEnvelopeActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
        }
    }

    private fun showPickerView() {// 弹出选择器
        try {
            val pvOptions = OptionsPickerBuilderInpul(this, OnOptionsSelectListener { options1, options2, options3, v ->
                //返回的分别是三个级别的选中位置
                val opt1tx = if (options1Items.isNotEmpty())
                    options1Items[options1].pickerViewText
                else
                    ""
                val opt2tx = if (options2Items.size > 0 && options2Items[options1].size > 0)
                    options2Items[options1][options2]
                else
                    ""
                val opt3tx = if (options2Items.size > 0
                        && options3Items[options1].size > 0
                        && options3Items[options1][options2].size > 0)
                    options3Items[options1][options2][options3]
                else
                    ""
                val tx = opt1tx + opt2tx + opt3tx
                if (opt1tx == opt2tx || opt1tx == opt3tx) {
                    if (opt1tx == "中国") {
                        province = "100"
                    } else {
                        province = "200"
                    }
                } else {
                    if (opt2tx=="全地区"){
                        province="100"
                        town=""
                        district=""
                    }
                    if (opt3tx == "全部" || opt3tx == opt2tx) {
                        district = ""
                        town = selectCode[opt2tx]
                    } else {
                        if (opt1tx == "中国") {
                            province = "100"
                        } else {
                            province = "200"
                        }
                        town = selectCode[opt2tx]
                        district = selectCode[opt3tx]
                    }
                }
                if (tx.length > 7) {
                    inputRegion.text = tx.substring(0, 7) + "..."
                } else {
                    inputRegion.text = tx
                }
            }).setTitleText(getString(R.string.Cityselection))
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build()
            /*pvOptions.setPicker(options1Items);//一级选择器
            pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
            pvOptions.setPicker(options1Items, options2Items, options3Items)//三级选择器
            pvOptions.show()
        } catch (e: Exception) {

        }
    }
}
