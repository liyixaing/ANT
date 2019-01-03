package com.example.administrator.mode.Utlis;

import android.util.Base64;

//bese加密
public class Base64Utils {

    public static String encode( byte[] bytes ){
        try{
            if( bytes == null || bytes.length < 1 ) return null;
            bytes = Base64.encode(bytes,bytes.length);
            return  new String(bytes , "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decode( String str ){
        if( str == null || "".equals( str )) return null;
        try{

            return Base64.decode(str,str.length());
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] str){
        String s = "Tde6VURU1nT0493QPVGRVR==";
        System.out.println( decode( s ));
    }
}
