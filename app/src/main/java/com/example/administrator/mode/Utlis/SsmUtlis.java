package com.example.administrator.mode.Utlis;

import android.content.Context;

import android.util.Log;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
import com.example.administrator.mode.Activity.DataResultException;
import com.example.administrator.mode.Interface.MoneyService;
import com.example.administrator.mode.Pojo.Common;
import com.example.administrator.mode.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 获取验证码
 */
public class SsmUtlis {

    public void Ssm(String Inputphone, String worldCode, String phoneKey, String status, final Context context) {
        String nowtime = DateUtils.getdata();
        Retrofit retrofit = Retrofit_manager.getInstance().getUserlogin();
        Call<Common> ssm = retrofit.create(MoneyService.class).sendssm(Inputphone, worldCode, phoneKey, "1", "0", status, nowtime, PreferencesUtil.get("language", ""), SignatureUtil.signtureByPrivateKey(nowtime));
        ssm.enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                if (response.body().getCode() == 1) {
                    Map<String, String> attributes = new HashMap<String, String>();
                    attributes.put("UserName", "[请填写事件参数的值]");
                    StatService.onEvent(context, "RegisterView.SendVerifyCode", "wms", 1, attributes);
                    Toast.makeText(context, R.string.SSM, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                if (t instanceof DataResultException) {
                    DataResultException resultException = (DataResultException) t;
                    Toast.makeText(context, resultException.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
