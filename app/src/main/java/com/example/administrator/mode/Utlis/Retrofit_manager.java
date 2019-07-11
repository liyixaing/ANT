package com.example.administrator.mode.Utlis;

import retrofit2.Retrofit;

/**
 * 0
 * Created by Administrator on 2018/5/5/005.
 */

//Retrofit初始
public class Retrofit_manager {
    private static Retrofit_manager instance;
    private Retrofit retrofit;

    private Retrofit_manager() {
        //baseUrl链接地址  http://mobileapi.antbank.org
        retrofit = new Retrofit.Builder().baseUrl("http://mobileapi.fuyer.com")
//         retrofit = new Retrofit.Builder().baseUrl("http://hellokitty.natapp1.cc")
        //添加gson解析
       // retrofit = new Retrofit.Builder().baseUrl("http://47.74.25.143:80")
       // retrofit = new Retrofit.Builder().baseUrl("http://192.168.31.135:80")
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