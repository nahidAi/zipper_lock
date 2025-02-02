package com.valentines.zipperlock;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class SettingActivity extends Activity {
    private SwitchCompat mSwitchd = null;
    private Context mContext = null;
    TextView tv_status;
    int choser;
    public static InterstitialAd interstitialAd;
    RelativeLayout playico, rateus, aboutus, disable, chooseImage;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);


        InterstitialAdmob_Load();
        AdView adView = (AdView) this.findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {


                if (choser == 1) {
                    Intent in = new Intent(getApplicationContext(), Developerintro.class);
                    startActivity(in);

                }
                super.onAdClosed();
            }
        });
        mContext = this;

        sharedPreferencesUtil.init(mContext);
        tv_status = (TextView) findViewById(R.id.text_en);
        mSwitchd = (SwitchCompat) this.findViewById(R.id.switch_locksetting);
        disable = (RelativeLayout) findViewById(R.id.disablescreenlock);
        playico = (RelativeLayout) findViewById(R.id.playicon);
        rateus = (RelativeLayout) findViewById(R.id.rateus);
        aboutus = (RelativeLayout) findViewById(R.id.aboutus);
        chooseImage = (RelativeLayout) findViewById(R.id.chooseImage);


        mSwitchd.setTextOn("yes");
        mSwitchd.setTextOff("no");
        boolean lockState = sharedPreferencesUtil.get(Lockscreen.ISLOCK);
        if (lockState) {
            mSwitchd.setChecked(true);
            tv_status.setText("غیرفعال کردن");

        } else {
            mSwitchd.setChecked(false);


        }


        mSwitchd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedPreferencesUtil.setBoolean(Lockscreen.ISLOCK, true);
                    Lockscreen.getInstance(mContext).startLockscreenService();
                    tv_status.setText("غیرفعال کردن قفل");
                    finish();

                } else {
                    sharedPreferencesUtil.setBoolean(Lockscreen.ISLOCK, false);
                    Lockscreen.getInstance(mContext).stopLockscreenService();
                    tv_status.setText("فعال کردن قفل");

                }

            }
        });

        playico.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/developer?id=New+and+Top+Apps"));
                startActivity(i);


            }
        });
        rateus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.valentines.zipperlocks"));
                startActivity(i);


            }
        });
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,ImageActivity.class);
                startActivity(intent);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, AboutActivity.class));

               /* choser = 1;
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    Intent in = new Intent(getApplicationContext(), Developerintro.class);
                    startActivity(in);

                }*/

            }
        });

        disable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD);
                startActivity(intent);


            }
        });


    }

    /*Assign and Load Intercialtial*/
    public void InterstitialAdmob_Load() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.intercitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);


    }

}



