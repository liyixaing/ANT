package com.example.administrator.mode.Utlis;

import com.example.administrator.mode.Activity.DataResultException;
import com.example.administrator.mode.Pojo.Beansuper;
import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2018/5/22/022.
 */

final class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody,T>{
    private Gson gson;
    private Type type;

    public MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
        Beansuper beanerror = gson.fromJson(response,Beansuper.class);
        if(beanerror.getCode()!=1){
            throw new DataResultException(beanerror.getMessage(),beanerror.getCode());
        }
        return gson.fromJson(response,type);
        }finally {
            value.close();
        }
    }
}
