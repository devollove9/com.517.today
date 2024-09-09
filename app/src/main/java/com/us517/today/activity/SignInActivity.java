package com.us517.today.activity;

import android.os.Bundle;
import android.view.View;

import com.us517.today.TodayApplication;
import com.us517.today.databinding.ActivitySigninBinding;

public class SignInActivity extends BaseActivity implements View.OnClickListener{
    ActivitySigninBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.signInConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TodayApplication tApp = (TodayApplication) getApplication();
        tApp.setSignIn(!tApp.isSignedIn());
        setResult(RESULT_OK);
        finish();
    }
}
