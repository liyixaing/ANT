package com.example.administrator.mode.Utlis;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.security.MessageDigest;
import java.util.Locale;


/**
 * 自定义工具,提供handler等工具,获取资源
 */
public class UiUtil {
    private static Context context;
    private static Handler handler;
    private static final String TAG = "UiUtil";


    public static Context getContext() {
        return context;
    }


    public static Handler getHandler() {
        return handler;
    }


    public static String[] getStringArray(int resId) {
        return context.getResources().getStringArray(resId);
    }


    /**
     * 得到应用程序的包名
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }


    /**
     * 初始化
     */
    public static void init(Application application) {
        context = application;
        //创建textview
        //        TextView tv = new_icon TextView(context);
        //开启其他页面
        //        context.startActivity(new_icon Intent());
        //handler ,
        handler = new Handler();
    }

    /**
     * 执行一个任务
     */
    public static void post(Runnable task) {
        handler.post(task);
    }


    /**
     * 取消一个任务
     */
    public static void cancel(Runnable task) {
        handler.removeCallbacks(task);
    }


    //args支持多个变量
    public static String getString(int resId, Object... args) {
        return context.getResources().getString(resId, args);
    }


    public static String hideName(String name) {
        if (!TextUtils.isEmpty(name)) {
            StringBuilder sb = new StringBuilder(name);

            sb.replace(0, 1, "*");

            return sb.toString();
        }

        return "";

    }


    public static String hideIdNum(String idNum) {
        if (!TextUtils.isEmpty(idNum) && idNum.length() >= 15) {
            StringBuilder sb = new StringBuilder(idNum);

            sb.replace(4, idNum.length() - 4, "****");

            return sb.toString();

        }
        return "";

    }


    public static String hidePhoneNum(String phoneNum) {
        if (!TextUtils.isEmpty(phoneNum) && phoneNum.length() == 11) {
            StringBuilder sb = new StringBuilder(phoneNum);

            sb.replace(3, phoneNum.length() - 4, "****");

            return sb.toString();

        }
        return "";

    }


    /**
     * px转换成dp
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static void i(String tag, String msg) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.i(tag, msg);
    }


    public static double footToMeter(double foot)

    {

        double n = foot * 0.305;

        n = Math.round(n * 1000) / 1000.0;

        return n;

    }


    public static double meterToFoot(double meter)

    {

        double n = meter / 0.305;

        n = Math.round(n * 1000) / 1000.0;

        return n;

    }


    /**
     * 判断国家是否是国内用户
     *
     * 方法一
     */
    public static boolean isCN(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(
            Context.TELEPHONY_SERVICE);
        String countryIso = tm.getSimCountryIso();
        boolean isCN = false;//判断是不是大陆
        if (!TextUtils.isEmpty(countryIso)) {
            countryIso = countryIso.toUpperCase(Locale.US);
            if (countryIso.contains("CN")) {
                isCN = true;
            }
        }
        return isCN;

    }


    /**
     * 获取字符串的MD5值
     */
    public static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            //把字节转换成对应的字符串
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
