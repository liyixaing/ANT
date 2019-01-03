package com.example.administrator.mode.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.mode.R;
import com.example.administrator.mode.Utlis.Event;
import com.example.administrator.mode.Utlis.PreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class BaseActivity extends FragmentActivity {

    private static final String TAG = "BaseActivity";
    private FragmentManager mFragmentManager;
    private Fragment showFragment;
    protected Context mContext;

    public static Boolean isFristCreated = false;
    private ProgressDialog waitDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        showLanguage(PreferencesUtil.get("language", "zh"));
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        }


        setContentView(getContentViewId());
        mFragmentManager = getSupportFragmentManager();
        init();
        loadDatas();
    }

    protected void showLanguage(String language) {
        //设置应用语言类型
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (language.equals("zh")) {
            PreferencesUtil.put("home", "首页");
            PreferencesUtil.put("wallet", "钱包");
            PreferencesUtil.put("deal", "交易");
            PreferencesUtil.put("mall", "商城");
            PreferencesUtil.put("quantity", "节点量:");
            PreferencesUtil.put("invite111", "邀请码:");
            PreferencesUtil.put("hint", "提示:");
            PreferencesUtil.put("Whether", "是否退出登录:");
            PreferencesUtil.put("confirm", "确定");
            PreferencesUtil.put("cancel", "取消");
            PreferencesUtil.put("envelope", "取消领取红包");
            PreferencesUtil.put("collection", "确认继续领取");
            PreferencesUtil.put("enough", "积分不足继续领取将失去");
            PreferencesUtil.put("earnings", "收益");
            PreferencesUtil.put("Pleaselanguage", "请选择语言");

            config.locale = Locale.SIMPLIFIED_CHINESE;
        } else {
            PreferencesUtil.put("home", "Home");
            PreferencesUtil.put("wallet", "Wallet");
            PreferencesUtil.put("deal", "Trades");
            PreferencesUtil.put("mall", "Mall");
            PreferencesUtil.put("quantity", "Node quantity:");
            PreferencesUtil.put("invite111", "Invitation code:");
            PreferencesUtil.put("hint", "hint:");
            PreferencesUtil.put("Whether", "Whether to log out or not");
            PreferencesUtil.put("confirm", "confirm");
            PreferencesUtil.put("cancel", "cancel");
            PreferencesUtil.put(" envelope", "Cancel the red envelope");
            PreferencesUtil.put(" collection", "Confirm continued collection");
            PreferencesUtil.put(" enough", "If you do not have enough points, you lose them");
            PreferencesUtil.put(" earnings", "earnings");
            PreferencesUtil.put("Pleaselanguage", "Please select language");
            config.locale = Locale.ENGLISH;
        }
        resources.updateConfiguration(config, dm);
    }


    /**
     * 返回当前activity所显示的布局ID
     */
    public abstract int getContentViewId();


    /**
     * 初始化方法
     */
    protected void init() {

    }

    public void abnormal(Context context) {
        Toast.makeText(context, R.string.System_in_hurry, Toast.LENGTH_SHORT).show();
        SharedPreferences sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("first", false);
        startActivity(new Intent(context, LoginActivity.class));
    }


    /**
     * 加载数据的方法
     */
    protected void loadDatas() {

    }


    protected void openActivity(Class<?> cls) {
        hideSoftKeyboard();
        Intent i = new Intent(mContext, cls);
        startActivity(i);
        overridePendingTransition(0, 0);
    }


    protected void openActivity(Class<?> cls, int type) {
        hideSoftKeyboard();
        Intent i = new Intent(mContext, cls);
        i.putExtra("type", type);
        startActivity(i);
        overridePendingTransition(0, 0);
    }


    public void toast(String str) {
        if (!isFinishing()) {
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }
    }


    protected void hideSoftKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void animateClose(final LinearLayout view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                onAnimationEndListener.onAnimationEnd();
            }
        });
        animator.start();
    }

    public ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }


    private OnAnimationEndListener onAnimationEndListener = new OnAnimationEndListener() {
        @Override
        public void onAnimationEnd() {

        }
    };


    public void setOnAnimationEndListener(OnAnimationEndListener onAnimationEndListener) {
        this.onAnimationEndListener = onAnimationEndListener;
    }


    public interface OnAnimationEndListener {
        void onAnimationEnd();
    }


    private void showDialogLoginOutofDate() {
      /*  final MyDialog1 myDialog1 = new_icon MyDialog1(this);
        myDialog1.setText("您的登录信息已过期", "请重新登录");
        myDialog1.setBtnText("确定", "取消");
        myDialog1.setCanceledOnTouchOutside(false);

        myDialog1.setOnDismissListener(new_icon DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });

        myDialog1.setPositiveOnClickListener(new_icon View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog1.setOnDismissListener(new_icon DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Intent intent = new_icon Intent(BaseActivity.this, MainActivity.class);
                        intent.putExtra("goToLogin", true);
                        startActivity(intent);
                    }
                });
                myDialog1.dismiss();

            }
        });
        myDialog1.setNegativeOnClickListener(new_icon View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog1.dismiss();
                finish();
            }
        });
        myDialog1.show();*/
    }

    /**
     * 发现之前的登录信息已经过时
     */
    public void checkTheTokenOutOfDate() {
      /*  if (!isFinishing()) {
            MyApplication.saveLoginInfo(false, null);
            showDialogLoginOutofDate();
        }*/
    }

    /*    public void showLoadingDialog(String msg) {
            showLoadingDialog(R.layout.view_tips_loading3, msg, false, true);//默认
        }

        public void showLoadingDialog(int layoutId, String msg, boolean touchOutSideCancel, boolean cancelable) {
            if (mLoadingDialog == null) {
                mLoadingDialog = new_icon LoadingDialog(this, layoutId, msg);
            }
            mLoadingDialog.setMessage(msg);
            mLoadingDialog.setCanceledOnTouchOutside(touchOutSideCancel);
            mLoadingDialog.setCancelable(cancelable);
            mLoadingDialog.setMessage(msg);
            if (!mLoadingDialog.isShowing())
                mLoadingDialog.show();
        }

        public void dismissLoadingDialog() {
            if (mLoadingDialog != null && mLoadingDialog.isShowing())
                mLoadingDialog.dismiss();
        }*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void lang(Event.Langrage even) {
        recreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    public void setLoading(boolean isLoading) {
       /* try {
            if (isLoading) {
                if (waitDialog == null || !waitDialog.isShowing()) {
                    waitDialog = new ProgressDialog(this, R.style.dialog);
                    waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    waitDialog.setCanceledOnTouchOutside(false);
                    ImageView view = new ImageView(this);
                    view.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                    Animation loadAnimation = AnimationUtils.loadAnimation(
                        this, R.anim.rotate);
                    view.startAnimation(loadAnimation);
                    loadAnimation.start();
                    view.setImageResource(R.mipmap.loading);
                    waitDialog.show();
                    waitDialog.setContentView(view);
                }
            } else {
                if (waitDialog != null && waitDialog.isShowing()) {
                    waitDialog.dismiss();
                    waitDialog = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("===", "类名===" + this.getClass().getName());
    }
}
