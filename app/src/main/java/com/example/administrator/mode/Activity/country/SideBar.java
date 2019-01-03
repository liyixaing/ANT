package com.example.administrator.mode.Activity.country;

/**
 * Created by admin on 2016/7/22.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.example.administrator.mode.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 类简要描述
 * <p/>
 * <p>
 * 类详细描述
 * </p>
 *
 * @author duanbokan
 */

public class SideBar extends View {
    // 字母变化监听事件
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    private List<String> mSourceDatas;
    // 字母数组
    public static String[] bbbbb = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
        "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#" };

    // 选中
    private int choose = -1;

    private Paint paint = new Paint();

    // 点击后提示当前选中字母
    private TextView mTextDialog;


    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }


    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mSourceDatas = Arrays.asList(bbbbb);
    }


    public SideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SideBar(Context context) {
        this(context, null);
    }


    public SideBar setmSourceDatas(List<String> mSourceDatas) {
        this.mSourceDatas = mSourceDatas;
        initSourceDatas();//对数据源进行初始化
        return this;
    }


    private void initSourceDatas() {
        HashSet h = new HashSet(mSourceDatas);
        mSourceDatas.clear();
        mSourceDatas.addAll(h);
        Collections.sort(mSourceDatas);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取屏幕高度和宽度
        int height = (int) getHeight();
        int width = getWidth();

        // 设置字母高度
        float letterHeight = (height * 1f) / mSourceDatas.size();
        for (int i = 0; i < mSourceDatas.size(); i++) {
            paint.setColor(Color.rgb(112, 113, 117));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            // 抗锯齿
            paint.setAntiAlias(true);
            paint.setTextSize(20);

            if (i == choose) {
                paint.setColor(getResources().getColor(R.color.essential));
                // 设置为加粗字体
                paint.setFakeBoldText(true);
            }
            // x坐标等于中间-字符串宽度的一半.
            float xPos = width / 2 - paint.measureText(mSourceDatas.get(i)) / 2;
            float yPos = letterHeight * i + letterHeight / 2;
            canvas.drawText(mSourceDatas.get(i), xPos, yPos, paint);
            // 重置画笔
            paint.reset();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float touch_y = event.getY();
        final int oldChoose = choose;

        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        // 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
        final int c = (int) (touch_y / getHeight() * mSourceDatas.size());

        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.rgb(29, 45, 53));
                choose = -1;
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default:
                //                setBackgroundResource(R.drawable.sidebar_background);
                if (oldChoose != c) {
                    if (c >= 0 && c < mSourceDatas.size()) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(mSourceDatas.get(c));
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(mSourceDatas.get(c));
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choose = c;
                        invalidate();
                    }
                }
                break;
        }

        return true;
    }


    /**
     * 向外公开的方法
     */
    public void setOnTouchingLetterChangedListener(
        OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }


    /**
     * 接口
     *
     * @author coder
     */
    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }

}
