package com.example.administrator.mode.Activity.privatekeymanage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mode.R;
import com.example.administrator.mode.Utlis.BaseDialog;
import com.example.administrator.mode.Utlis.ClickUtlis;

public class DeriveThePrivatekeyActivity extends BaseDialog {
    PrivateKeyManageActivity privateKeyManageActivity;
    TextView textView;
    String Input;
    Button button;
    TextView tit;
    String titInput;
    TextView linearLayout;

    public PrivateKeyManageActivity getPrivateKeyManageActivity() {
        return privateKeyManageActivity;
    }

    public void setPrivateKeyManageActivity(PrivateKeyManageActivity privateKeyManageActivity, String input, String tit) {
        this.Input = input;
        this.titInput = tit;
        this.privateKeyManageActivity = privateKeyManageActivity;
    }

    public DeriveThePrivatekeyActivity(Context context) {
        super(context);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derive_the_privatekey);
        textView = findViewById(R.id.copyPrivateInput);
        button = findViewById(R.id.copyPrivate);
        tit = findViewById(R.id.titDerive);
        linearLayout = findViewById(R.id.LinearDerive);
        tit.setText(titInput);
        textView.setText(Input);
        if (titInput.equals("导出助记词")) {
            linearLayout.setText("安全警告:助记词须妥善保管，如诺助记词丢失可能会造成不必要的麻烦");
        }
        if (titInput.equals("deriveDSSCard")){
            linearLayout.setText(" Safety warning: mnemonic words must be properly kept, if the loss of mnemonic words may cause unnecessary trouble");
        }

        button.setOnClickListener(new ClickUtlis() {
            @Override
            public void onMultiClick(View v) {
                if (getPrivateKeyManageActivity() != null) {
                    getPrivateKeyManageActivity().OnInputCopySuccess(textView.getText().toString().trim());
                    dismiss();
                }
            }
        });
    }
}
