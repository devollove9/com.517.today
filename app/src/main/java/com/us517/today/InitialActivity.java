package com.us517.today;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.us517.today.databinding.ActivityMainBinding;
import com.us517.today.util.Dialog;

/**
 *  This class will check if its first install, updates, showing logo, etc..
 *  Anything before main app will be here
 */
public class InitialActivity extends AppCompatActivity {
    private static int WAIT_TIME = 2500;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Logo Vieww
        setContentView(R.layout.activity_initial);

        // Force portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Check for update
        Dialog.showToast(this, R.string.download_newest_ver);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /*
                if (!todayApplication.getRegionId().isEmpty()) {
                    Intent intent = new Intent(LogoActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LogoActivity.this, GuideActivity.class);
                    startActivity(intent);
                }*/
                // Go to main view
                Intent intent = new Intent(InitialActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, WAIT_TIME);

    }

    private void openGooglePlayUpdate() {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        // My package name
        // String pkgName = getPackageName();
        String pkgName = "com.reddit.frontpage";
        intent.setData(Uri.parse(getString(R.string.market_uri, pkgName)));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            intent.setData(Uri.parse(getString(R.string.web_uri, pkgName)));

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Dialog.showToast(this, R.string.download_newest_ver);

            }
        }
    }
}
