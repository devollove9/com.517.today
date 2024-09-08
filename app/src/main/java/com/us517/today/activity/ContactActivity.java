package com.us517.today.activity;

import android.os.Bundle;

import com.us517.today.databinding.ActivityContactBinding;

public class ContactActivity extends BaseActivity {
    ActivityContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}