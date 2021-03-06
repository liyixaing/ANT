package com.example.administrator.mode.Utlis;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Description:
 */
class AlertController {

    private CommAlertDialog mDialog;
    private Window mWindow;

    private DialogViewHelper mViewHelper;


    public AlertController(CommAlertDialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }


    public void setViewHelper(DialogViewHelper viewHelper) {
        this.mViewHelper = viewHelper;
    }


    /**
     * 设置文本
     */
    public void setText(int viewId, CharSequence text) {
        mViewHelper.setText(viewId, text);
    }


    public void setIcon(int viewId, int resId) {
        mViewHelper.setIcon(viewId, resId);
    }


    public <T extends View> T getView(int viewId) {
        return mViewHelper.getView(viewId);
    }


    /**
     * 设置点击事件
     */
    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        mViewHelper.setOnclickListener(viewId, listener);
    }


    /**
     * 获取Dialog
     */
    public CommAlertDialog getDialog() {
        return mDialog;
    }


    /**
     * 获取Dialog的Window
     */
    public Window getWindow() {
        return mWindow;
    }


    public static class AlertParams {
        public Context mContext;
        public int mThemeResId;
        // 点击空白是否能够取消  默认点击阴影可以取消
        public boolean mCancelable = true;
        // dialog Cancel监听
        public DialogInterface.OnCancelListener mOnCancelListener;
        // dialog Dismiss监听
        public DialogInterface.OnDismissListener mOnDismissListener;
        // dialog Key监听
        public DialogInterface.OnKeyListener mOnKeyListener;
        // 布局View
        public View mView;
        // 布局layout id
        public int mViewLayoutResId;
        // 存放字体的修改
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        // 存放点击事件
        public SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();
        //存放图标的修改
        public SparseArray<Integer> mIconArray = new SparseArray<>();
        // 宽度
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mPropWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 动画
        public int mAnimations = 0;
        // 位置
        public int mGravity = Gravity.CENTER;
        // 高度
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;


        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }


        /**
         * 绑定和设置参数
         */
        public void apply(AlertController mAlert) {
            // 完善这个地方 设置参数

            // 1. 设置Dialog布局  DialogViewHelper
            DialogViewHelper viewHelper = null;
            if (mViewLayoutResId != 0) {
                viewHelper = new DialogViewHelper(mContext, mViewLayoutResId);
            }

            if (mView != null) {
                viewHelper = new DialogViewHelper();
                viewHelper.setContentView(mView);
            }

            if (viewHelper == null) {
                throw new IllegalArgumentException("请设置布局setContentView()");
            }

            // 给Dialog 设置布局
            mAlert.getDialog().setContentView(viewHelper.getContentView());

            // 设置 Controller的辅助类
            mAlert.setViewHelper(viewHelper);

            // 2.设置文本
            int textArraySize = mTextArray.size();
            for (int i = 0; i < textArraySize; i++) {
                mAlert.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }
            //设置图标
            int iconArraySize = mIconArray.size();
            for (int i = 0; i < iconArraySize; i++) {
                mAlert.setIcon(mIconArray.keyAt(i), mIconArray.valueAt(i));
            }

            // 3.设置点击
            int clickArraySize = mClickArray.size();
            for (int i = 0; i < clickArraySize; i++) {
                mAlert.setOnclickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }

            // 4.配置自定义的效果  全屏  从底部弹出    默认动画
            Window window = mAlert.getWindow();
            // 设置位置
            window.setGravity(mGravity);

            // 设置动画
            if (mAnimations != 0) {
                window.setWindowAnimations(mAnimations);
            }

            // 设置宽高
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);


        }
    }
}
