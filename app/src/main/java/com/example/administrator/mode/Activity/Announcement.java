package com.example.administrator.mode.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mode.Fragment.A_Fragment;
import com.example.administrator.mode.Fragment.red_packet.RedPacketGetActivity;
import com.example.administrator.mode.R;
import com.example.administrator.mode.Utlis.BaseDialog;
import com.example.administrator.mode.Utlis.ClickUtlis;
import com.example.administrator.mode.Utlis.VerifyUtlis;

public class Announcement extends BaseDialog {
    RedPacketGetActivity redPacketGetActivity;

    public RedPacketGetActivity getRedPacketGetActivity() {
        return redPacketGetActivity;
    }

    public void setRedPacketGetActivity(RedPacketGetActivity redPacketGetActivity) {
        this.redPacketGetActivity = redPacketGetActivity;
    }

    ImageView imageView;
    public Announcement(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement);
        imageView = (ImageView) findViewById(R.id.imageN);
        imageView.setOnClickListener(new ClickUtlis() {
            @Override
            public void onMultiClick(View v) {
                dismiss();
            }
        });
    }

}
