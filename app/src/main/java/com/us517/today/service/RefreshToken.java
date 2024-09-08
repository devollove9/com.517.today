package com.us517.today.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class RefreshToken extends Service {
    private final int REFRESH_TIME = 60;

    @Override
    public IBinder onBind(Intent intent) {return null;}

    @Override
    public void onDestroy() {super.onDestroy();}

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
       // handleStart(intent, startId);
        return START_NOT_STICKY;
    }
}
