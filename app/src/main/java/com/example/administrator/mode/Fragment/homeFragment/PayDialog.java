package com.example.administrator.mode.Fragment.homeFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mode.Activity.privatekeymanage.PrivateKeyManageActivity;
import com.example.administrator.mode.Fragment.crowd.CrowdParticularsActivity;
import com.example.administrator.mode.Fragment.red_packet.OrdinaryRedEnvelopesActivity;
import com.example.administrator.mode.Fragment.red_packet.RredEnvelopeActivity;
import com.example.administrator.mode.R;
import com.example.administrator.mode.Utlis.BaseDialog;
import com.example.administrator.mode.Utlis.ClickUtlis;

public class PayDialog extends BaseDialog {


    private AffirmDealActivity affirmDealActivity;
    private ConversionActivity conversionActivity;
    private CommodityDealActivity commodityDealActivity;
    private OrdinaryRedEnvelopesActivity ordinaryRedEnvelopesActivity;
    private RredEnvelopeActivity rredEnvelopeActivity;
    PrivateKeyManageActivity privateKeyManageActivity;

    public PrivateKeyManageActivity getPrivateKeyManageActivity() {
        return privateKeyManageActivity;
    }

    public void setPrivateKeyManageActivity(PrivateKeyManageActivity privateKeyManageActivity, String tit) {
        this.tit = tit;
        this.privateKeyManageActivity = privateKeyManageActivity;
    }

    CrowdParticularsActivity crowdParticularsActivity;

    public CrowdParticularsActivity getCrowdParticularsActivity() {
        return crowdParticularsActivity;
    }

    public void setCrowdParticularsActivity(CrowdParticularsActivity crowdParticularsActivity, String tit) {
        this.crowdParticularsActivity = crowdParticularsActivity;
        this.tit = tit;
    }

    private String tit;

    public RredEnvelopeActivity getRredEnvelopeActivity() {
        return rredEnvelopeActivity;
    }

    public void setRredEnvelopeActivity(RredEnvelopeActivity rredEnvelopeActivity, String tit) {
        this.rredEnvelopeActivity = rredEnvelopeActivity;
        this.tit = tit;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public OrdinaryRedEnvelopesActivity getOrdinaryRedEnvelopesActivity() {
        return ordinaryRedEnvelopesActivity;
    }

    public void setOrdinaryRedEnvelopesActivity(OrdinaryRedEnvelopesActivity ordinaryRedEnvelopesActivity, String tit) {
        this.ordinaryRedEnvelopesActivity = ordinaryRedEnvelopesActivity;
        this.tit = tit;
    }

    public CommodityDealActivity getCommodityDealActivity() {
        return commodityDealActivity;
    }

    public void setCommodityDealActivity(CommodityDealActivity commodityDealActivity, String tit) {
        this.tit = tit;
        this.commodityDealActivity = commodityDealActivity;
    }


    public AffirmDealActivity getAffirmDealActivity() {
        return affirmDealActivity;
    }

    public void setAffirmDealActivity(AffirmDealActivity affirmDealActivity, String tit) {
        this.tit = tit;
        this.affirmDealActivity = affirmDealActivity;
    }

    public ConversionActivity getConversionActivity() {
        return conversionActivity;
    }

    public void setConversionActivity(ConversionActivity conversionActivity, String tit) {
        this.tit = tit;
        this.conversionActivity = conversionActivity;
    }

    private Button payPwdEditText;
    ImageView imageView;

    TextView payDialogTit;

    public PayDialog(Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_dialog);
        imageView = (ImageView) findViewById(R.id.payDialogClose);
        imageView.setOnClickListener(new ClickUtlis() {
            @Override
            public void onMultiClick(View v) {
                dismiss();
            }
        });
        payDialogTit = findViewById(R.id.payDialogTit);
        payDialogTit.setText(tit);
        TextView textView = (TextView) findViewById(R.id.payPwdInput);
        payPwdEditText = findViewById(R.id.paySuccess);
        payPwdEditText.setOnClickListener(new ClickUtlis() {
            @Override
            public void onMultiClick(View v) {
                String str = textView.getText().toString().trim();
                try {
                    if (str.length() < 1) {
                        return;
                    }
                    if (getAffirmDealActivity() != null) {
                        getAffirmDealActivity().OnInputPayPasswrodSuccess(str);
                    }
                    if (getOrdinaryRedEnvelopesActivity() != null) {
                        getOrdinaryRedEnvelopesActivity().OnInputPayPasswrodSuccess(str);
                    }
                    if (getConversionActivity() != null) {
                        getConversionActivity().OnInputPayPasswrodSuccess(str);
                    }

                    if (getCommodityDealActivity() != null) {
                        getCommodityDealActivity().OnInputPayPasswrodSuccess(str);
                    }

                    if (getRredEnvelopeActivity() != null) {
                        getRredEnvelopeActivity().OnInputPayPasswrodSuccess(str);
                    }
                    if (getCrowdParticularsActivity() != null) {
                        getCrowdParticularsActivity().OnInputPayPasswrodSuccess(str);

                    }
                    if (getPrivateKeyManageActivity() != null) {
                        getPrivateKeyManageActivity().OnremoveSuccess(str + "," + tit);
                    }


                } catch (Exception e) {
                    Log.i("dadas", e.toString());
                } finally {
                    dismiss();
                }

            }
        });

    }
}
