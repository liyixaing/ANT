package com.example.administrator.mode.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mode.Fragment.A_Fragment;
import com.example.administrator.mode.R;
import com.example.administrator.mode.Utlis.BaseDialog;
import com.example.administrator.mode.Utlis.ClickUtlis;
import com.example.administrator.mode.Utlis.VerifyUtlis;

public class AnnouncementActivity extends BaseDialog {
    TextView textView;
    String character;
    ImageView imageView;
    private A_Fragment aFragment;
    private MainActivity mainActivity;

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity, String character) {
        this.mainActivity = mainActivity;
        this.character = character;
    }

    public AnnouncementActivity(Context context) {
        super(context);
    }

    public A_Fragment getaFragment() {
        return aFragment;
    }

    public void setaFragment(A_Fragment aFragment, String character) {
        this.aFragment = aFragment;
        this.character = character;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        textView = (TextView) findViewById(R.id.announcement);
        textView.setText(VerifyUtlis.stringChang("\n" + character + "\n"));
        imageView = (ImageView) findViewById(R.id.imageN);
        imageView.setOnClickListener(new ClickUtlis() {
            @Override
            public void onMultiClick(View v) {
                dismiss();
            }
        });
    }
}
