package com.example.administrator.mode.Utlis;

import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/5/5/005.
 */

//Retrofit初始
public class Retrofit_manager {
    private static Retrofit_manager instance;
    private Retrofit retrofit;
    public final static String WEBURL = "http://bnt.fuyer.com";//前端界面地址 生产环境
//    public final static String WEBURL = "http://192.168.31.211:8813/_ant";//前端界面地址 本地环境

//    public final static String SERVERURL = "http://mall.fcsap.com";//商城地址

    private Retrofit_manager() {
        retrofit = new Retrofit.Builder().baseUrl("http://bmobileapi.fuyer.com")//生产环境  http://mobileapi.fuyer.com
//        retrofit = new Retrofit.Builder().baseUrl("http://192.168.31.136:80")//测试环境
                .addConverterFactory(MyGsonConverterFactory.create())
                .build();
    }

    public static Retrofit_manager getInstance() {
        if (null == instance) {
            instance = new Retrofit_manager();
        }
        return instance;
    }

    public Retrofit getUserlogin() {
        return retrofit;
    }
}
