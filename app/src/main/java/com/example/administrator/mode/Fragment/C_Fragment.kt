package com.example.administrator.mode.Fragment


import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import com.example.administrator.mode.Fragment.crowd.*
import com.example.administrator.mode.R
import java.util.ArrayList



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class C_Fragment : Fragment() {
    private var viewPager: ViewPager? = null                            // 页面内容
    private var imageView: ImageView? = null                            // 滑动的图片
    private var voiceAnswer: TextView? = null
    private var healthPedia: TextView? = null
    private var crowdRecord: TextView? = null
    private var pDected: TextView? = null     // 选项名称
    private var fragments: MutableList<Fragment>? = null                       //标题列表
    private var offset = 0                                 // 动画图片偏移量
    private var currIndex = 0                              // 当前页卡编号
    private var bmpW: Int = 0                                       // 动画图片宽度
    private var selectedColor: Int = 0
    private var unSelectedColor: Int = 0
    private val pageSize = 4
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("JavascriptInterface")
    private fun initView() {
    /*    crowdWebView.settings.domStorageEnabled = true
        crowdWebView.settings.javaScriptEnabled = true
        crowdWebView.settings.allowFileAccess = true
        crowdWebView.settings.allowContentAccess = true
        crowdWebView.webViewClient = object : WebViewClient() {}
        crowdWebView.settings.loadWithOverviewMode = true
        crowdWebView.addJavascriptInterface(this, "ANT")
        val ua = crowdWebView.settings.userAgentString
        crowdWebView.settings.userAgentString = "$ua/AntDapp"
        webViewAlbum = WebViewAlbum(activity!!)
        crowdWebView.webChromeClient = webViewAlbum
        val sp = activity!!.getSharedPreferences("USER", Context.MODE_PRIVATE)

         crowdWebView.loadUrl("http://ipfs.fuyer.com/ipns/Qma5JwPPYmHEGSdxwvF8dQDrFxe4z2uHUSBZB4WAdv5Crc/src/transaction/CrowdFunding.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")
    */   /* crowdWebView.loadUrl("http://192.168.31.211:8020/ant/src/transaction/CrowdFunding.html?user_id=" + sp.getString("user_id", "") + "&user_token=" + sp.getString("user_token", "") + "&language=" + sp.getString("language", "") + "&loginType=android")*/
        /*
        selectedColor = resources.getColor(R.color.afragment)
            unSelectedColor = resources.getColor(R.color.editText11)
            InitImageView()
            InitTextView()
            InitViewPager()*/
    }

    /**
     * 初始化Viewpager页
     */
    private fun InitViewPager() {
        viewPager = activity!!.findViewById(R.id.id_viewpager)
        fragments = ArrayList()
        fragments!!.add(CrowdPreheat())
        fragments!!.add(CrowdIng())
        fragments!!.add(CrowdEnd())
        fragments!!.add(CrowdRecord())
        viewPager!!.adapter = myPagerAdapter(activity!!.supportFragmentManager, fragments)
        viewPager!!.currentItem = 0
        viewPager!!.setOnPageChangeListener(this.MyOnPageChangeListener())
    }

    /**
     * 初始化顶部三个字体按钮
     */
    private fun InitTextView() {
        voiceAnswer = activity!!.findViewById(R.id.id_information)
        healthPedia = activity!!.findViewById(R.id.id_share)
        pDected = activity!!.findViewById(R.id.id_blog)
        crowdRecord = activity!!.findViewById(R.id.crowdRecord)

        voiceAnswer!!.setTextColor(selectedColor)
        healthPedia!!.setTextColor(unSelectedColor)
        pDected!!.setTextColor(unSelectedColor)
        crowdRecord!!.setTextColor(unSelectedColor)

        voiceAnswer!!.text = getString(R.string.InThePreheating)
        healthPedia!!.text = getString(R.string.InTheIng)
        pDected!!.text = getString(R.string.InThefinished)
        crowdRecord!!.text = getString(R.string.MyRecord)

        voiceAnswer!!.setOnClickListener(MyOnClickListener(0))
        healthPedia!!.setOnClickListener(MyOnClickListener(1))
        pDected!!.setOnClickListener(MyOnClickListener(2))
        crowdRecord!!.setOnClickListener(MyOnClickListener(3))

        voiceAnswer!!.setTextColor(selectedColor)
        val drawable = resources.getDrawable(R.drawable.record_preheat_in)
        drawable.setBounds(0, 0, drawable.minimumWidth / 3, drawable.minimumHeight / 3)// 设置边界
        voiceAnswer!!.setCompoundDrawables(null, drawable, null, null)

        healthPedia!!.setTextColor(unSelectedColor)
        val drawable1 = resources.getDrawable(R.drawable.record_ing)
        drawable1.setBounds(0, 0, drawable1.minimumWidth / 3, drawable1.minimumHeight / 3)// 设置边界
        healthPedia!!.setCompoundDrawables(null, drawable1, null, null)

        pDected!!.setTextColor(unSelectedColor)
        val drawable2 = resources.getDrawable(R.drawable.record_end)
        drawable2.setBounds(0, 0, drawable2.minimumWidth / 3, drawable2.minimumHeight / 3)// 设置边界
        pDected!!.setCompoundDrawables(null, drawable2, null, null)

        crowdRecord!!.setTextColor(unSelectedColor)
        val drawable3 = resources.getDrawable(R.drawable.crwrod_record)
        drawable3.setBounds(0, 0, drawable3.minimumWidth / 3, drawable3.minimumHeight / 3)// 设置边界
        crowdRecord!!.setCompoundDrawables(null, drawable3, null, null)

    }

    /**
     * 为选项卡绑定监听器
     */
    inner class MyOnPageChangeListener : ViewPager.OnPageChangeListener {

        internal var one = offset * 2 + bmpW// 页面1 -> 页面2 偏移量
        internal var two = one * 2// 页面1 -> 页面3 偏移量

        override fun onPageScrollStateChanged(index: Int) {}

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}

        override fun onPageSelected(index: Int) {
            val animation = TranslateAnimation((one * currIndex).toFloat(), (one * index).toFloat(), 0f, 0f)// 显然这个比较简洁，只有一行代码。
            currIndex = index
            animation.fillAfter = true// True:图片停在动画结束位置
            animation.duration = 300
            imageView!!.startAnimation(animation)

            when (index) {
                0 -> {
                    voiceAnswer!!.setTextColor(selectedColor)
                    val drawable = resources.getDrawable(R.drawable.record_preheat_in)
                    drawable.setBounds(0, 0, drawable.minimumWidth / 3, drawable.minimumHeight / 3)// 设置边界
                    voiceAnswer!!.setCompoundDrawables(null, drawable, null, null)

                    healthPedia!!.setTextColor(unSelectedColor)
                    val drawable1 = resources.getDrawable(R.drawable.record_ing)
                    drawable1.setBounds(0, 0, drawable1.minimumWidth / 3, drawable1.minimumHeight / 3)// 设置边界
                    healthPedia!!.setCompoundDrawables(null, drawable1, null, null)

                    pDected!!.setTextColor(unSelectedColor)
                    val drawable2 = resources.getDrawable(R.drawable.record_end)
                    drawable2.setBounds(0, 0, drawable2.minimumWidth / 3, drawable2.minimumHeight / 3)// 设置边界
                    pDected!!.setCompoundDrawables(null, drawable2, null, null)

                    crowdRecord!!.setTextColor(unSelectedColor)
                    val drawable3 = resources.getDrawable(R.drawable.crwrod_record)
                    drawable3.setBounds(0, 0, drawable3.minimumWidth / 3, drawable3.minimumHeight / 3)// 设置边界
                    crowdRecord!!.setCompoundDrawables(null, drawable3, null, null)


                }
                1 -> {
                    healthPedia!!.setTextColor(selectedColor)
                    val drawable1 = resources.getDrawable(R.drawable.record_ing_in)
                    drawable1.setBounds(0, 0, drawable1.minimumWidth / 3, drawable1.minimumHeight / 3)// 设置边界
                    healthPedia!!.setCompoundDrawables(null, drawable1, null, null)


                    voiceAnswer!!.setTextColor(unSelectedColor)
                    val drawable = resources.getDrawable(R.drawable.record_preheat)
                    drawable.setBounds(0, 0, drawable.minimumWidth / 3, drawable.minimumHeight / 3)// 设置边界
                    voiceAnswer!!.setCompoundDrawables(null, drawable, null, null)

                    pDected!!.setTextColor(unSelectedColor)
                    val drawable2 = resources.getDrawable(R.drawable.record_end)
                    drawable2.setBounds(0, 0, drawable2.minimumWidth / 3, drawable2.minimumHeight / 3)// 设置边界
                    pDected!!.setCompoundDrawables(null, drawable2, null, null)

                    crowdRecord!!.setTextColor(unSelectedColor)
                    val drawable3 = resources.getDrawable(R.drawable.crwrod_record)
                    drawable3.setBounds(0, 0, drawable3.minimumWidth / 3, drawable3.minimumHeight / 3)// 设置边界
                    crowdRecord!!.setCompoundDrawables(null, drawable3, null, null)

                }
                2 -> {
                    pDected!!.setTextColor(selectedColor)
                    val drawable2 = resources.getDrawable(R.drawable.record_end_in)
                    drawable2.setBounds(0, 0, drawable2.minimumWidth / 3, drawable2.minimumHeight / 3)// 设置边界
                    pDected!!.setCompoundDrawables(null, drawable2, null, null)

                    voiceAnswer!!.setTextColor(unSelectedColor)
                    val drawable = resources.getDrawable(R.drawable.record_preheat)
                    drawable.setBounds(0, 0, drawable.minimumWidth / 3, drawable.minimumHeight / 3)// 设置边界
                    voiceAnswer!!.setCompoundDrawables(null, drawable, null, null)

                    healthPedia!!.setTextColor(unSelectedColor)
                    val drawable1 = resources.getDrawable(R.drawable.record_ing)
                    drawable1.setBounds(0, 0, drawable1.minimumWidth / 3, drawable1.minimumHeight / 3)// 设置边界
                    healthPedia!!.setCompoundDrawables(null, drawable1, null, null)


                    crowdRecord!!.setTextColor(unSelectedColor)
                    val drawable3 = resources.getDrawable(R.drawable.crwrod_record)
                    drawable3.setBounds(0, 0, drawable3.minimumWidth / 3, drawable3.minimumHeight / 3)// 设置边界
                    crowdRecord!!.setCompoundDrawables(null, drawable3, null, null)
                }
                3 -> {
                    crowdRecord!!.setTextColor(selectedColor)
                    val drawable3 = resources.getDrawable(R.drawable.crwrod_record_in)
                    drawable3.setBounds(0, 0, drawable3.minimumWidth / 3, drawable3.minimumHeight / 3)// 设置边界
                    crowdRecord!!.setCompoundDrawables(null, drawable3, null, null)

                    pDected!!.setTextColor(unSelectedColor)
                    val drawable2 = resources.getDrawable(R.drawable.record_end)
                    drawable2.setBounds(0, 0, drawable2.minimumWidth / 3, drawable2.minimumHeight / 3)// 设置边界
                    pDected!!.setCompoundDrawables(null, drawable2, null, null)

                    voiceAnswer!!.setTextColor(unSelectedColor)
                    val drawable = resources.getDrawable(R.drawable.record_preheat)
                    drawable.setBounds(0, 0, drawable.minimumWidth / 3, drawable.minimumHeight / 3)// 设置边界
                    voiceAnswer!!.setCompoundDrawables(null, drawable, null, null)

                    healthPedia!!.setTextColor(unSelectedColor)
                    val drawable1 = resources.getDrawable(R.drawable.record_ing)
                    drawable1.setBounds(0, 0, drawable1.minimumWidth / 3, drawable1.minimumHeight / 3)// 设置边界
                    healthPedia!!.setCompoundDrawables(null, drawable1, null, null)

                }
            }
        }
    }

    /**
     * 初始化动画，这个就是页面滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
     */

    private fun InitImageView() {
        imageView = activity!!.findViewById<ImageView>(R.id.cursor)
        bmpW = BitmapFactory.decodeResource(resources, R.mipmap.newloginbackgroundbai).width + 1// 获取图片宽度
        val dm = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(dm)
        val screenW = dm.widthPixels// 获取分辨率宽度
        offset = (screenW / pageSize - bmpW) / 2 // 计算偏移量--(屏幕宽度/页卡总数-图片实际宽度)/2
        // = 偏移量
        val matrix = Matrix()
        matrix.postTranslate(offset.toFloat(), 0f)
        imageView!!.imageMatrix = matrix// 设置动画初始位置
    }

    /**
     * 定义内部类适配器
     */
    private inner class myPagerAdapter(fm: FragmentManager, private val fragmentList: List<Fragment>?) : FragmentPagerAdapter(fm) {

        /**
         * 得到每个页面
         */
        override fun getItem(arg0: Int): Fragment? {
            return if (fragmentList == null || fragmentList.isEmpty())
                null
            else
                fragmentList[arg0]
        }

        /**
         * 每个页面的title
         */
        override fun getPageTitle(position: Int): CharSequence? {
            return null
        }

        /**
         * 页面的总个数
         */
        override fun getCount(): Int {
            return fragmentList?.size ?: 0
        }
    }

    /**
     * 定义内部类三个文字点击按钮监听
     */
    private inner class MyOnClickListener(i: Int) : View.OnClickListener {
        private var index = 0

        init {
            index = i
        }

        override fun onClick(v: View) {

            when (index) {
                0 -> {
                    voiceAnswer!!.setTextColor(selectedColor)
                    val drawable = resources.getDrawable(R.drawable.record_preheat_in)
                    drawable.setBounds(0, 0, drawable.minimumWidth / 3, drawable.minimumHeight / 3)// 设置边界
                    voiceAnswer!!.setCompoundDrawables(null, drawable, null, null)

                    healthPedia!!.setTextColor(unSelectedColor)
                    val drawable1 = resources.getDrawable(R.drawable.record_ing)
                    drawable1.setBounds(0, 0, drawable1.minimumWidth / 3, drawable1.minimumHeight / 3)// 设置边界
                    healthPedia!!.setCompoundDrawables(null, drawable1, null, null)

                    pDected!!.setTextColor(unSelectedColor)
                    val drawable2 = resources.getDrawable(R.drawable.record_end)
                    drawable2.setBounds(0, 0, drawable2.minimumWidth / 3, drawable2.minimumHeight / 3)// 设置边界
                    pDected!!.setCompoundDrawables(null, drawable2, null, null)

                    crowdRecord!!.setTextColor(unSelectedColor)
                    val drawable3 = resources.getDrawable(R.drawable.crwrod_record)
                    drawable3.setBounds(0, 0, drawable3.minimumWidth / 3, drawable3.minimumHeight / 3)// 设置边界
                    crowdRecord!!.setCompoundDrawables(null, drawable3, null, null)


                }
                1 -> {
                    healthPedia!!.setTextColor(selectedColor)
                    val drawable1 = resources.getDrawable(R.drawable.record_ing_in)
                    drawable1.setBounds(0, 0, drawable1.minimumWidth / 3, drawable1.minimumHeight / 3)// 设置边界
                    healthPedia!!.setCompoundDrawables(null, drawable1, null, null)


                    voiceAnswer!!.setTextColor(unSelectedColor)
                    val drawable = resources.getDrawable(R.drawable.record_preheat)
                    drawable.setBounds(0, 0, drawable.minimumWidth / 3, drawable.minimumHeight / 3)// 设置边界
                    voiceAnswer!!.setCompoundDrawables(null, drawable, null, null)

                    pDected!!.setTextColor(unSelectedColor)
                    val drawable2 = resources.getDrawable(R.drawable.record_end)
                    drawable2.setBounds(0, 0, drawable2.minimumWidth / 3, drawable2.minimumHeight / 3)// 设置边界
                    pDected!!.setCompoundDrawables(null, drawable2, null, null)

                    crowdRecord!!.setTextColor(unSelectedColor)
                    val drawable3 = resources.getDrawable(R.drawable.crwrod_record)
                    drawable3.setBounds(0, 0, drawable3.minimumWidth / 3, drawable3.minimumHeight / 3)// 设置边界
                    crowdRecord!!.setCompoundDrawables(null, drawable3, null, null)

                }
                2 -> {
                    pDected!!.setTextColor(selectedColor)
                    val drawable2 = resources.getDrawable(R.drawable.record_end_in)
                    drawable2.setBounds(0, 0, drawable2.minimumWidth / 3, drawable2.minimumHeight / 3)// 设置边界
                    pDected!!.setCompoundDrawables(null, drawable2, null, null)

                    voiceAnswer!!.setTextColor(unSelectedColor)
                    val drawable = resources.getDrawable(R.drawable.record_preheat)
                    drawable.setBounds(0, 0, drawable.minimumWidth / 3, drawable.minimumHeight / 3)// 设置边界
                    voiceAnswer!!.setCompoundDrawables(null, drawable, null, null)

                    healthPedia!!.setTextColor(unSelectedColor)
                    val drawable1 = resources.getDrawable(R.drawable.record_ing)
                    drawable1.setBounds(0, 0, drawable1.minimumWidth / 3, drawable1.minimumHeight / 3)// 设置边界
                    healthPedia!!.setCompoundDrawables(null, drawable1, null, null)


                    crowdRecord!!.setTextColor(unSelectedColor)
                    val drawable3 = resources.getDrawable(R.drawable.crwrod_record)
                    drawable3.setBounds(0, 0, drawable3.minimumWidth / 3, drawable3.minimumHeight / 3)// 设置边界
                    crowdRecord!!.setCompoundDrawables(null, drawable3, null, null)
                }
                3 -> {
                    crowdRecord!!.setTextColor(selectedColor)
                    val drawable3 = resources.getDrawable(R.drawable.crwrod_record_in)
                    drawable3.setBounds(0, 0, drawable3.minimumWidth / 3, drawable3.minimumHeight / 3)// 设置边界
                    crowdRecord!!.setCompoundDrawables(null, drawable3, null, null)

                    pDected!!.setTextColor(unSelectedColor)
                    val drawable2 = resources.getDrawable(R.drawable.record_end)
                    drawable2.setBounds(0, 0, drawable2.minimumWidth / 3, drawable2.minimumHeight / 3)// 设置边界
                    pDected!!.setCompoundDrawables(null, drawable2, null, null)

                    voiceAnswer!!.setTextColor(unSelectedColor)
                    val drawable = resources.getDrawable(R.drawable.record_preheat)
                    drawable.setBounds(0, 0, drawable.minimumWidth / 3, drawable.minimumHeight / 3)// 设置边界
                    voiceAnswer!!.setCompoundDrawables(null, drawable, null, null)

                    healthPedia!!.setTextColor(unSelectedColor)
                    val drawable1 = resources.getDrawable(R.drawable.record_ing)
                    drawable1.setBounds(0, 0, drawable1.minimumWidth / 3, drawable1.minimumHeight / 3)// 设置边界
                    healthPedia!!.setCompoundDrawables(null, drawable1, null, null)

                }
            }
            viewPager!!.currentItem = index
        }

    }
}
