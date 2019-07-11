package com.example.administrator.mode.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation
import android.widget.AdapterView
import android.widget.Toast
import com.baidu.mobstat.StatService
import com.example.administrator.mode.Activity.country.*
import com.example.administrator.mode.Interface.MoneyService
import com.example.administrator.mode.Pojo.CountryTurn
import com.example.administrator.mode.R
import com.example.administrator.mode.Utlis.*
import kotlinx.android.synthetic.main.activity_country.*
import kotlinx.android.synthetic.main.header_view.*
import kotlinx.android.synthetic.main.tit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CountryActivity : BaseActivity() {
    private var mAllCountryList: MutableList<CountrySortModel>? = null
    private var pinyinComparator: CountryComparator? = null
    private var countryChangeUtil: GetCountryNameSort? = null
    private var characterParserUtil: CharacterParserUtil? = null
    private var adapter: CountrySortAdapter? = null
    private var y: Float = 0.toFloat()
    override fun getContentViewId(): Int {
        return R.layout.activity_country
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //显示选中的字母
        tit_name.setText(R.string.nation)
        tit_iv.setOnClickListener(object : ClickUtlis() {
            override fun onMultiClick(v: View?) {
                finish()
            }
        })
        country_sidebar.setTextView(country_dialog)
    }

    override fun loadDatas() {
        mAllCountryList = ArrayList()
        pinyinComparator = CountryComparator()
        countryChangeUtil = GetCountryNameSort()
        characterParserUtil = CharacterParserUtil()
        // 将国家进行排序，按照A~Z的顺序
        Collections.sort(mAllCountryList, pinyinComparator)
        adapter = CountrySortAdapter(this, mAllCountryList)
        lv_countries.adapter = adapter
        setListener()
        getCountryList()
    }

    private fun getCountryList() {
        val nowtime = DateUtils.getdata()
        val retrofit = Retrofit_manager.getInstance().userlogin
        val login = retrofit.create(MoneyService::class.java).getWorldCodes("0", nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(nowtime))
        login.enqueue(object : Callback<CountryTurn> {
            override fun onResponse(call: Call<CountryTurn>, response: Response<CountryTurn>) {
                try {
                    if (response.body()!!.code == 1) {
                        var sideB = ArrayList<String>()
                        if (response.body()!!.data.size > 0 && response.body()!!.data != null) {
                            for (con in response.body()!!.data) {
                                val contryName = con.chineseName + "\n" + con.englishName
                                val contryNumber = con.countryCode
                                val countrySortKey = characterParserUtil!!.getSelling(contryName)
                                val countrySortModel = CountrySortModel(contryName, contryNumber, countrySortKey)
                                var sortLetter = countryChangeUtil!!.getSortLetterBySortKey(countrySortKey)
                                if (sortLetter == null) {
                                    sortLetter = countryChangeUtil!!.getSortLetterBySortKey(contryName)
                                }
                                sideB.add(sortLetter.toUpperCase())
                                countrySortModel.sortLetters = sortLetter
                                mAllCountryList!!.add(countrySortModel)
                            }
                            country_sidebar.setmSourceDatas(sideB).invalidate()//设置数据
                            Collections.sort(mAllCountryList, pinyinComparator)
                            adapter!!.updateListView(mAllCountryList)
                        }
                    } else {
                        Toast.makeText(this@CountryActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    abnormal(this@CountryActivity)
                }
            }

            override fun onFailure(call: Call<CountryTurn>, t: Throwable) {
                if (t is DataResultException) {
                    val resultException = t as DataResultException
                    Toast.makeText(this@CountryActivity, resultException.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    /****
     * 添加监听
     */
    private fun setListener() {

        // 右侧sideBar监听
        country_sidebar.setOnTouchingLetterChangedListener { s ->
            // 该字母首次出现的位置
            val position = adapter!!.getPositionForSection(s[0].toInt())
            if (position != -1) {
                lv_countries.setSelection(position)
            }
        }
        lv_countries.setOnItemClickListener { _, _, i, _ ->
            val countryName = mAllCountryList!![i].countryName
            val countryNumber = mAllCountryList!![i].countryNumber
            val country = countryName.split("\n")
            val sp = getSharedPreferences("USER", Context.MODE_PRIVATE)
            val edit = sp.edit()
            try {
                if (intent.extras.getString("wwwww") == "xxx11111x") {
                    edit.putString("user_crowndsdasdas", countryNumber)
                }
            } catch (e: java.lang.Exception) {
                edit.putString("user_crown", countryNumber)
                edit.putString("user_Nation_CN", country.get(0))
                edit.putString("user_Nation_EN", country.get(1))
            } finally {
                edit.commit()
                var attributes = HashMap<String, String>()
                attributes.put("CountryCode", countryNumber)
                attributes.put("CountryNameCH", country.get(0))
                attributes.put("CountryNameEN", country.get(1))
                StatService.onEvent(this@CountryActivity, "LoginView.SelectCounty", "xms", 1, attributes)
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val animation = TranslateAnimation(0f, 0f, -y, 0f)
        animation.duration = 500
        animation.fillAfter = true
        val alphaAnimation = AlphaAnimation(0f, 1f)
        alphaAnimation.duration = 500
        alphaAnimation.fillAfter = true
        country_view.startAnimation(animation)
        rl_head.startAnimation(alphaAnimation)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
