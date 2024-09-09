package com.us517.today.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.OnBackPressedCallback;

import com.us517.today.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);/*
        Log.d("starting","startingstartingstartingstartingstartingstartingstarting");
        final OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d("starting","startingstartingstartingstartingstartingstartingstarting");
                finish();
                setEnabled(false); //this is important line
                //onBackPressed();
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);*/
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        //Log.d("starting","startingstartingstartingstartingstartingstartingstarting");
        //overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        // overridePendingTransition(R.anim.anim_left_in, R.anim.anim_rignt_out);
    }
    /*
    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
    }
    */
}
