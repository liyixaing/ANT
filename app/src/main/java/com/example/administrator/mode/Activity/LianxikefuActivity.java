package com.example.administrator.mode.Activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mode.R;

/**
 * 联系客服界面
 */
public class LianxikefuActivity extends Activity {
    TextView tv_fuzhi;
    LinearLayout ll_retuin;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lianxikefu);
        context = getApplicationContext();
        initView();
    }

    //初始化View
    public void initView() {
        tv_fuzhi = findViewById(R.id.tv_fuzhi);
        tv_fuzhi.setOnClickListener(onClickListener);
        ll_retuin = findViewById(R.id.ll_retuin);
        ll_retuin.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_retuin:
                    finish();
                    break;
                case R.id.tv_fuzhi:
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    cm.setText("ANTtoken001");
                    Toast.makeText(context, getResources().getString(R.string.Out_copyok), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
}
