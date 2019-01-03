package com.example.administrator.mode.Fragment.homeFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.mode.Fragment.A_Fragment;
import com.example.administrator.mode.R;
import com.example.administrator.mode.Utlis.BaseDialog;
import com.example.administrator.mode.Utlis.ClickUtlis;

public class RedPacket2Activity extends BaseDialog {
    private A_Fragment aFragment;
    private String siz;
    TextView num;
    Button redInput;
    RelativeLayout relativeLayout;
    public A_Fragment getaFragment() {
        return aFragment;
    }

    public String getSiz() {
        return siz;
    }

    public void setSiz(String siz) {
        this.siz = siz;
    }

    public void setaFragment(A_Fragment aFragment, String siz) {
        this.aFragment = aFragment;
        this.siz = siz;

    }

    public RedPacket2Activity(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_packet2);
        num = (TextView) findViewById(R.id.num);
        relativeLayout= (RelativeLayout) findViewById(R.id.red3);
        redInput=(Button) findViewById(R.id.saveRed);
        num.setText(siz);
        redInput.setOnClickListener(new ClickUtlis() {
            @Override
            public void onMultiClick(View v) {
                if (getaFragment() != null) {
                    getaFragment().next2();
                    dismiss();
                }
            }
        });
        AlphaAnimation mShowAnimation = new AlphaAnimation(0.9f, 1.0f);
        mShowAnimation.setDuration(200);
        mShowAnimation.setFillAfter(true);
        relativeLayout.startAnimation(mShowAnimation);
    }
}
