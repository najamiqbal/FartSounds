package com.funnyringtone.bestringtones;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.funnyringtone.bestringtones.utils.LanguagePref;




import java.util.Locale;

public class MyApplication extends Application {

    private static MyApplication myApp;
    private static String appLanguage;

    public static MyApplication getApplication() {
        return myApp;
    }







    public static void setDefaultLocale(Context mContext) {
        String lang = LanguagePref.getLang(mContext);
        updateConfig(mContext, lang);
    }

    public String getAppLanguage() {
        appLanguage = LanguagePref.getLang(this);
        return appLanguage;
    }

    public static void updateConfig(Context mContext, String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = mContext.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public static void updateLocale(Context mContext, String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = mContext.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        new Resources(res.getAssets(), dm, conf);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;



        setDefaultLocale(this);
    }
}