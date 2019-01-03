package com.example.administrator.mode.Fragment.homeFragment;

import android.content.Context;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.administrator.mode.R;
import com.example.administrator.mode.Utlis.BaseDialog;
import com.example.administrator.mode.Utlis.ClickUtlis;
import com.example.administrator.mode.Utlis.PayPwdEditText;

public class PayDialog extends BaseDialog {


    private AffirmDealActivity affirmDealActivity;
    private ConversionActivity conversionActivity;
    private CommodityDealActivity commodityDealActivity;

    public CommodityDealActivity getCommodityDealActivity() {
        return commodityDealActivity;
    }

    public void setCommodityDealActivity(CommodityDealActivity commodityDealActivity) {
        this.commodityDealActivity = commodityDealActivity;
    }


    public AffirmDealActivity getAffirmDealActivity() {
        return affirmDealActivity;
    }

    public void setAffirmDealActivity(AffirmDealActivity affirmDealActivity) {
        this.affirmDealActivity = affirmDealActivity;
    }


    public ConversionActivity getConversionActivity() {
        return conversionActivity;
    }

    public void setConversionActivity(ConversionActivity conversionActivity) {
        this.conversionActivity = conversionActivity;
    }

    private PayPwdEditText payPwdEditText;

    public PayDialog(Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_dialog);

        TextView textView = (TextView) findViewById(R.id.didog_findpwd);
        textView.setOnClickListener(new ClickUtlis() {
            @Override
            public void onMultiClick(View v) {
                if (getAffirmDealActivity() != null) {
                    getAffirmDealActivity().tiaozhuan();
                }
                if (getConversionActivity() != null) {
                    getConversionActivity().tiaozhuan();
                }
                if (getCommodityDealActivity() != null) {
                    getCommodityDealActivity().tiaozhuan();
                }


            }
        });
        payPwdEditText = findViewById(R.id.ppet);
        payPwdEditText.initStyle(R.drawable.edit_num_bg_red, 6, 0.33f, R.color.essential, R.color.essential, 20);
        payPwdEditText.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {//密码输入完后的回调

                if (getAffirmDealActivity() != null) {
                    getAffirmDealActivity().OnInputPayPasswrodSuccess(str);
                }
                if (getConversionActivity() != null) {
                    getConversionActivity().OnInputPayPasswrodSuccess(str);
                }
                if (getCommodityDealActivity() != null) {
                    getCommodityDealActivity().OnInputPayPasswrodSuccess(str);
                }


                dismiss();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                payPwdEditText.setFocus();
            }
        }, 100);
    }
}
