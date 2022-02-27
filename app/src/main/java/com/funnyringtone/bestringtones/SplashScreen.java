package com.funnyringtone.bestringtones;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.funnyringtone.bestringtones.utils.LanguagePref;


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




        final TextView textView = (TextView) findViewById(R.id.lodertext);
        final CardView cardView = (CardView) findViewById(R.id.loadingBtn);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
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






