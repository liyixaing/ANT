package com.example.administrator.mode.Utlis;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.administrator.mode.R;

/**
 * Description: 自定义万能Dialog
 */
public class CommAlertDialog extends Dialog {

    private AlertController mAlert;
    private Boolean isFullScreen;


    public CommAlertDialog(Context context, int themeResId, Boolean isFullScreen) {
        super(context, themeResId);
        this.isFullScreen = isFullScreen;
        mAlert = new AlertController(this, getWindow());
    }


    /**
     * 设置文本
     */
    public void setText(int viewId, CharSequence text) {
        mAlert.setText(viewId, text);
    }


    public <T extends View> T getView(int viewId) {
        return mAlert.getView(viewId);
    }


    /**
     * 设置点击事件
     */
    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        mAlert.setOnclickListener(viewId, listener);
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isFullScreen) {
            setWidth();
        }
    }


    //设置4/5屏幕宽度
    protected void setWidth() {
        Window w = getWindow();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        WindowManager.LayoutParams l = w.getAttributes();
        l.width = (p.x / 10) * 8;
        w.setAttributes(l);
    }


    public static class Builder {

        private final AlertController.AlertParams P;


        /**
         * Creates a builder for an alert dialog that uses the default alert
         * dialog theme.
         * <p/>
         * The default alert dialog theme is defined by
         * {@link android.R.attr#alertDialogTheme} within the parent
         * {@code context}'s theme.
         *
         * @param context the parent context
         */
        public Builder(Context context) {
            this(context, R.style.dialog);
        }


        /**
         * Creates a builder for an alert dialog that uses an explicit theme
         * resource.
         * <p/>
         * The specified theme resource ({@code themeResId}) is applied on top
         * of the parent {@code context}'s theme. It may be specified as a
         * style resource containing a fully-populated theme, such as
         * {@link android.R.style#Theme_Material_Dialog}, to replace all
         * attributes in the parent {@code context}'s theme including primary
         * and accent colors.
         * <p/>
         * To preserve attributes such as primary and accent colors, the
         * {@code themeResId} may instead be specified as an overlay theme such
         * as {@link android.R.style#ThemeOverlay_Material_Dialog}. This will
         * override only the window attributes necessary to style the alert
         * window as a dialog.
         * <p/>
         * Alternatively, the {@code themeResId} may be specified as {@code 0}
         * to use the parent {@code context}'s resolved value for
         * {@link android.R.attr#alertDialogTheme}.
         *
         * @param context the parent context
         * @param themeResId the resource ID of the theme against which to inflate
         * this dialog, or {@code 0} to use the parent
         * {@code context}'s default alert dialog theme
         */
        public Builder(Context context, int themeResId) {

            P = new AlertController.AlertParams(context, themeResId);
        }


        /**
         * Sets a custom view to be the contents of the alert dialog.
         * <p/>
         * When using a pre-Holo theme, if the supplied view is an instance of
         * a {@link ListView} then the light background will be used.
         * <p/>
         * <strong>Note:</strong> To ensure consistent styling, the custom view
         * should be inflated or constructed using the alert dialog's themed
         * context obtained via {@link #getContext()}.
         *
         * @param view the view to use as the contents of the alert dialog
         * @return this Builder object to allow for chaining of calls to set
         * methods
         */
        public Builder setContentView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }


        // 设置布局内容的layoutId
        public Builder setContentView(int layoutId) {
            P.mView = null;
            P.mViewLayoutResId = layoutId;
            return this;
        }


        // 设置文本
        public Builder setText(int viewId, CharSequence text) {
            P.mTextArray.put(viewId, text);
            return this;
        }


        public Builder setIcon(int viewId, int resID) {
            P.mIconArray.put(viewId, resID);
            return this;
        }


        // 设置点击事件
        public Builder setOnClickListener(int view, View.OnClickListener listener) {
            P.mClickArray.put(view, listener);
            return this;
        }


        // 配置一些万能的参数
        public Builder propWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;

            return this;
        }


        // 配置一些万能的参数
        public Builder fullWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }


        /**
         * 从底部弹出
         *
         * @param isAnimation 是否有动画
         */
        public Builder formBottom(boolean isAnimation) {
            if (isAnimation) {
                P.mAnimations = R.style.dialog_from_bottom_anim;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }


        /**
         * 设置Dialog的宽高
         */
        public Builder setWidthAndHeight(int width, int height) {
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }


        /**
         * 添加默认动画
         */
        public Builder addDefaultAnimation() {
            P.mAnimations = R.style.dialog_scale_anim;
            return this;
        }


        /**
         * 设置动画
         */
        public Builder setAnimations(int styleAnimation) {
            P.mAnimations = styleAnimation;
            return this;
        }


        /**
         * Sets whether the dialog is cancelable or not.  Default is true.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }


        /**
         * Sets the callback that will be called if the dialog is canceled.
         *
         * <p>Even in a cancelable dialog, the dialog may be dismissed for reasons other than
         * being canceled or one of the supplied choices being selected.
         * If you are interested in listening for all cases where the dialog is dismissed
         * and not just when it is canceled, see
         * {@link #setOnDismissListener(OnDismissListener) setOnDismissListener}.</p>
         *
         * @return This Builder object to allow for chaining of calls to set methods
         * @see #setCancelable(boolean)
         * @see #setOnDismissListener(OnDismissListener)
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }


        /**
         * Sets the callback that will be called when the dialog is dismissed for any reason.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }


        /**
         * Sets the callback that will be called if a key is dispatched to the dialog.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }


        /**
         * Creates an {@link CommAlertDialog} with the arguments supplied to this
         * builder.
         * <p/>
         * Calling this method does not display the dialog. If no additional
         * processing is needed, {@link #show()} may be called instead to both
         * create and display the dialog.
         */
        public CommAlertDialog create(Boolean isFullScreen) {
            // Context has already been wrapped with the appropriate theme.
            final CommAlertDialog dialog = new CommAlertDialog(P.mContext, P.mThemeResId,
                isFullScreen);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }


        /**
         * Creates an {@link CommAlertDialog} with the arguments supplied to this
         * builder and immediately displays the dialog.
         * <p/>
         * Calling this method is functionally identical to:
         * <pre>
         *     CommAlertDialog dialog = builder.create();
         *     dialog.show();
         * </pre>
         */
        public CommAlertDialog show(Boolean isFullScreen) {
            final CommAlertDialog dialog = create(isFullScreen);
            dialog.show();
            return dialog;
        }
    }
}
