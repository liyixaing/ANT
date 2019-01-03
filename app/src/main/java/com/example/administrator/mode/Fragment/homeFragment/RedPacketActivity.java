package com.example.administrator.mode.Fragment.homeFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mode.Fragment.A_Fragment;
import com.example.administrator.mode.R;
import com.example.administrator.mode.Utlis.BaseDialog;
import com.example.administrator.mode.Utlis.ClickUtlis;

public class RedPacketActivity extends BaseDialog {
    private A_Fragment aFragment;
    TextView redPackent;
    RelativeLayout redinput;
    private int num = 0;

    public A_Fragment getaFragment() {
        return aFragment;
    }

    public void setaFragment(A_Fragment aFragment) {
        this.aFragment = aFragment;
    }

    public RedPacketActivity(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_packet);
        redPackent = (TextView) findViewById(R.id.openRedPacket);
        if (redPackent.getText().toString().equals("Open")){
            redPackent.setTextSize(18);
        }
        redinput = (RelativeLayout) findViewById(R.id.red);
        redPackent.setOnClickListener(new ClickUtlis() {
            @Override
            public void onMultiClick(View v) {
                if (getaFragment() != null) {
                    Animation animation = new RotateAnimation(-10f, 10f, 1, 0.5f, 1, 0.5f);
                    animation.setDuration(80);
                    animation.setRepeatCount(4);//动画的重复次数
                    animation.setRepeatMode(Animation.REVERSE);
                    animation.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {
                            num = 0;
                        }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            dismiss();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            num++;
                            if (num == 3) {
                                getaFragment().next();
                            }
                        }
                    });
                    redinput.startAnimation(animation);//开始动画
                }
            }
        });
    }
}
