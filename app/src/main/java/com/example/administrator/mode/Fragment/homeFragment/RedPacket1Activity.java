package com.example.administrator.mode.Fragment.homeFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.administrator.mode.Fragment.A_Fragment;
import com.example.administrator.mode.R;
import com.example.administrator.mode.Utlis.BaseDialog1;

public class RedPacket1Activity extends BaseDialog1 {
    private A_Fragment aFragment;
    RelativeLayout redinput;

    public A_Fragment getaFragment() {
        return aFragment;
    }

    public void setaFragment(A_Fragment aFragment) {
        this.aFragment = aFragment;
    }

    public RedPacket1Activity(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_packet1);
        redinput = (RelativeLayout) findViewById(R.id.relativelayoutred1);
        AlphaAnimation mShowAnimation = new AlphaAnimation(0.9f, 1.2f);
        mShowAnimation.setDuration(500);
        mShowAnimation.setFillAfter(true);
        mShowAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation mHideAnimation = new AlphaAnimation(1.0f, 0.5f);
                mHideAnimation.setDuration(100);
                mHideAnimation.setFillAfter(true);
                mHideAnimation.setInterpolator(new DecelerateInterpolator());
                mHideAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        getaFragment().next1();
                        dismiss();
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                redinput.startAnimation(mHideAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        redinput.startAnimation(mShowAnimation);
    }
}
