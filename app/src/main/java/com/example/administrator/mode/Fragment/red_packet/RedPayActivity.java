package com.example.administrator.mode.Fragment.red_packet;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mode.R;
import com.example.administrator.mode.Utlis.BaseDialog;
import com.example.administrator.mode.Utlis.PayPwdEditText;

public class RedPayActivity extends BaseDialog {
    public RedPayActivity(Context context) {
        super(context);
    }

    private PayPwdEditText payPwdEditTextRed;
    private TextView textViewShow;
    OrdinaryRedEnvelopesActivity ordinaryRedEnvelopesActivity;
    RredEnvelopeActivity rredEnvelopeActivity;
    ImageView isClose;
    String show;

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public OrdinaryRedEnvelopesActivity getOrdinaryRedEnvelopesActivity() {
        return ordinaryRedEnvelopesActivity;
    }

    public void setOrdinaryRedEnvelopesActivity(OrdinaryRedEnvelopesActivity ordinaryRedEnvelopesActivity, String show) {
        this.show = show;
        this.ordinaryRedEnvelopesActivity = ordinaryRedEnvelopesActivity;
    }

    public RredEnvelopeActivity getRredEnvelopeActivity() {
        return rredEnvelopeActivity;
    }

    public void setRredEnvelopeActivity(RredEnvelopeActivity rredEnvelopeActivity, String show) {
        this.show = show;
        this.rredEnvelopeActivity = rredEnvelopeActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_pay);
        payPwdEditTextRed = findViewById(R.id.ppetRed);
        textViewShow = findViewById(R.id.redCardPay);
        isClose = (ImageView) findViewById(R.id.payRed);
        textViewShow.setText(show);
        isClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        payPwdEditTextRed.initStyle(R.drawable.edit_num_bg, 6, 0.33f, R.color.editText, R.color.editText, 20);
        payPwdEditTextRed.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {//密码输入完后的回调

                if (getRredEnvelopeActivity() != null) {
                    getRredEnvelopeActivity().OnInputPayPasswrodSuccess(str);
                }
                if (getOrdinaryRedEnvelopesActivity() != null) {
                    getOrdinaryRedEnvelopesActivity().OnInputPayPasswrodSuccess(str);
                }
                dismiss();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                payPwdEditTextRed.setFocus();
            }
        }, 100);
    }
}
