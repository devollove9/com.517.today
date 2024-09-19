package com.us517.today;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;


import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.Fragment;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

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
    private String userNickname;
    private final static String USER_NICKNAME = "USER_NICKNAME";
    // private AuthModel authModel;
    private boolean isSignedIn = false;
    private String token;
    private String userId;
    private final static String USER_ACCOUNT = "ACCOUNT";
    private final static String PASSWORD = "PASSWORD";

    // Places Api
    private static String googleApiKey = "";
    private static PlacesClient placesClient;

    // System util
    private final static String SETTING = "SETTING";

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences(SETTING, MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        // Detect if user has saved language. if yes set as saved language
        String savedL = sharedPreferences.getString(LANGUAGE_TEXT, LANGUAGE_ENGLISH);
        setLanguage(savedL);

        userNickname = sharedPreferences.getString(USER_NICKNAME,"Account");
        regionId = sharedPreferences.getString(REGION, "");
        regionName = sharedPreferences.getString(REGION_NAME, null);
        regionNameEn = sharedPreferences.getString(REGION_NAME_EN, null);
        //sharedPreferences.edit().putInt(LANGUAGE_TEXT, language).commit();
        ApplicationInfo appInfo;
        try {
            appInfo = this.getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
            googleApiKey = appInfo.metaData.getString("googleApiKey");

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (!googleApiKey.isEmpty()) {
            Places.initialize(getApplicationContext(), googleApiKey);
            placesClient = Places.createClient(this);
        }



    }

    private int time = 0;

    public String getGoogleApiKey() {
        return googleApiKey;
    }

    public PlacesClient getPlacesClient() { return placesClient;}

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

    public void setUserNickname(String nickname) {
        userNickname = nickname;
        sharedPreferencesEditor.putString(USER_NICKNAME, nickname).apply();
    }

    public String getUserNickname() {
        return sharedPreferences.getString(USER_NICKNAME, userNickname);
    }

}
