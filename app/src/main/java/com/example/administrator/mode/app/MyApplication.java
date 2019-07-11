package com.example.administrator.mode.app;

import android.app.Application;

import android.content.Context;
import android.os.Debug;


import com.example.administrator.mode.Pojo.KeyAddressBean;
import com.example.administrator.mode.Pojo.Loginturn;
import com.example.administrator.mode.Utlis.FileUtil;
import com.example.administrator.mode.Utlis.PreferencesUtil;
import com.example.administrator.mode.Utlis.SharedPreferencesUtil;
import com.example.administrator.mode.Utlis.UiUtil;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.util.NIMUtil;

/**
 * Created by nie.xin on 2017/10/19.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    public static boolean isLogin = false;//登录状态
    public static String token;
    //用户登录之后的token，可以用来查询用户信息,有时候mUserInfo并不能成功获取，可以通过token重新查询
    public static Loginturn.DataBean mUserInfo; //登录之后用户的信息
    public static LoginInfo loginInfo; //登录之后用户的信息
    public static Context sContext;
    public static int ltype = 0;
    public static String uType;
    public static String userid = "";
    public static KeyAddressBean keyAddressBeans;
    public static String userPhone = "";
    public static String isVip = "";
    public static String isMerchant = "";
    public static String isaaa = "";
    public static String userWc = "";
    Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        SharedPreferencesUtil.getInstance(mContext, "whz666");
        sContext = this;
        UiUtil.init(this);
        if (PreferencesUtil.get("ltype", "Chinese").equals("Chinese")) {
            ltype = 0;
        } else {
            ltype = 1;
        }
        //全局缓存数据,据说app转到后台Application有可能重建 所以在此重新获取下
        isLogin = PreferencesUtil.get("isLogin", false);
        token = PreferencesUtil.get("token", "");
        userid = PreferencesUtil.get("uid", "");
        uType = PreferencesUtil.get("uType", "");
        if (isLogin) {
            //经过测试这里取mUserInfo可能会拿到null,在启动页面会有补偿机制
            mUserInfo = (Loginturn.DataBean) FileUtil.readSerializableObj(
                    Loginturn.DataBean.class.getSimpleName(), sContext);
        }
    }

    private void initNIMUtil() {
        NIMClient.init(this,loginInfo(),options());
        if (NIMUtil.isMainProcess(this)) {
            // 注意：以下操作必须在主进程中进行
            // 1、UI相关初始化操作
            // 2、相关Service调用
        }
    }

    private SDKOptions options() {
        return  null;
    }

    private LoginInfo loginInfo() {
        return null;
    }

    private static void saveRongToken(String s) {
        PreferencesUtil.put("rongToken", s);
    }



    private static void saveuserid(String uType) {
        MyApplication.uType = uType;
        PreferencesUtil.put("uType", uType); //0用户1经纪人
    }

    //将token保存在文件中
    public static void saveUsername(String token) {
        MyApplication.token = token;
        PreferencesUtil.put("token", token);
    }


    //将uid保存在文件中
    public static void saveUserphone(String uid) {
        MyApplication.userid = uid;
        PreferencesUtil.put("uid", uid);

    }

}
