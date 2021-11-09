package com.app.fartsounds.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class LanguagePref {

    private final static String USER_SHAREDS = "prefsLang";
    private final static String USER_DATA = "dataLang";

    public static void persistLang(Context context, String mail) {
        SharedPreferences.Editor pref = context.getSharedPreferences(USER_SHAREDS, Context.MODE_PRIVATE).edit();
        pref.putString(USER_DATA, mail);
        pref.apply();
    }

    public static String getLang(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(USER_SHAREDS, Context.MODE_PRIVATE);
        String user = prefs.getString(USER_DATA, "us");
        System.out.println("========>>>"+user);
        return user;
    }

}
