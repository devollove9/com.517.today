package com.us517.today;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

/**
 * Main app application
  */

public class TodayApplication extends Application {
    // Shared preferences
    private SharedPreferences sharedPreferences;

    // Locales
    public static String ENGLISH = "en";
    public static String CHAINESE = "cn";
    public static String LANGUAGE;
    public final static int LANGUAGE_ENGLISH = 9;
    public final static int LANGUAGE_CHINESE = 8;
    private final static String LANGUAGE_TEXT = "language";

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

        Resources resource = getResources();
        Configuration config = resource.getConfiguration();
        int language;
        if (sharedPreferences.getInt(LANGUAGE_TEXT, LANGUAGE_CHINESE) == LANGUAGE_CHINESE) {
            config.setLocale(Locale.CHINESE);
            language = LANGUAGE_CHINESE;
        } else {
            config.setLocale(Locale.ENGLISH);
            language = LANGUAGE_ENGLISH;
        }

        this.regionId = sharedPreferences.getString(REGION, "");
        regionName = sharedPreferences.getString(REGION_NAME, null);
        regionNameEn = sharedPreferences.getString(REGION_NAME_EN, null);
        sharedPreferences.edit().putInt(LANGUAGE_TEXT, language).commit();

        int a = sharedPreferences.getInt(LANGUAGE_TEXT, LANGUAGE_CHINESE);

        Log.i("LOG_MESSAGE", String.valueOf(a));
        //getResources().updateConfiguration(config, dm);
    }

    private int time = 0;


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @SuppressWarnings("deprecation")
    public void setLanguage(int language) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();
        if (language == LANGUAGE_ENGLISH) {
            config.setLocale(Locale.ENGLISH);
        } else {
            config.setLocale(Locale.CHINESE);
        }
        res.updateConfiguration(config, dm);
        LANGUAGE = config.locale.getLanguage();
        sharedPreferences.edit().putInt(LANGUAGE_TEXT, language).commit();
    }
}
