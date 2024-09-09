package com.us517.today;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;


import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import java.util.Locale;

/**
 * Main app application
  */

public class TodayApplication extends Application {
    // Shared preferences
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;
    // Locales
    public final static String LANGUAGE_ENGLISH = "en";
    public final static String LANGUAGE_CHINESE = "zh";
    private final static String LANGUAGE_TEXT = "LANGUAGE";

    // Region
    private String regionId;
    private String regionName;
    private String regionNameEn;
    private final static String REGION = "REGION";
    private final static String REGION_NAME = "REGION_NAME";
    private final static String REGION_NAME_EN = "REGION_NAME_EN";

    // User
    // private AuthModel authModel;
    private boolean isSignedIn = false;
    private String token;
    private String userId;
    private final static String USER_ACCOUNT = "ACCOUNT";
    private final static String PASSWORD = "PASSWORD";

    // System util
    private final static String SETTING = "SETTING";


    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences(SETTING, MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        // Detect if user has saved language. if yes set as saved language
        String savedL = sharedPreferences.getString(LANGUAGE_TEXT, LANGUAGE_ENGLISH);
        Log.d(savedL,savedL);
        setLanguage(savedL);

        this.regionId = sharedPreferences.getString(REGION, "");
        regionName = sharedPreferences.getString(REGION_NAME, null);
        regionNameEn = sharedPreferences.getString(REGION_NAME_EN, null);
        //sharedPreferences.edit().putInt(LANGUAGE_TEXT, language).commit();

    }

    private int time = 0;

    public boolean isSignedIn() {
        return isSignedIn;
    }

    public void setSignIn(boolean isSignedIn) {
        this.isSignedIn = isSignedIn;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setLanguage(String language) {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(Locale.forLanguageTag(language)));
        sharedPreferencesEditor.putString(LANGUAGE_TEXT, language).apply();
    }

    public String getLanguage() {
       // return AppCompatDelegate.getApplicationLocales().toString();
        return sharedPreferences.getString(LANGUAGE_TEXT, LANGUAGE_ENGLISH);
    }
}
