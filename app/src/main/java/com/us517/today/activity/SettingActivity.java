package com.us517.today.activity;

import android.os.Bundle;

import com.us517.today.databinding.ActivitySettingBinding;

public class SettingActivity extends BaseActivity {
    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}