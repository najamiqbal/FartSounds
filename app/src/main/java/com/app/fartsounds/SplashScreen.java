package com.app.fartsounds;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.app.fartsounds.models.AdsManager;
import com.app.fartsounds.ui.fragments.ContinueActivity;
import com.app.fartsounds.utils.LanguagePref;
import com.facebook.ads.AudienceNetworkAds;
import com.ironsource.mediationsdk.IronSource;

import java.util.Locale;
import java.util.Timer;


public class SplashScreen extends AppCompatActivity {

    int value = 0;
    private Timer timer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        setDefaultLocale(this);
        AudienceNetworkAds.initialize(this);
        IronSource.setConsent(true);
        IronSource.setMetaData("do_not_sell","false");
        IronSource.setMetaData("is_child_directed","false");
        IronSource.init(this,getResources().getString(R.string.appKey));


        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);


        final TextView textView = (TextView) findViewById(R.id.lodertext);
        final CardView cardView = (CardView) findViewById(R.id.loadingBtn);
        AdsManager.loadIntersAd(textView,progressBar);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdsManager.showNext(SplashScreen.this,MainActivity.class);
            }
        });




    }


    public static void updateConfig(Context mContext, String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = mContext.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }



    public static void setDefaultLocale(Context mContext) {
        String lang = LanguagePref.getLang(mContext);
        updateConfig(mContext, lang);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}






