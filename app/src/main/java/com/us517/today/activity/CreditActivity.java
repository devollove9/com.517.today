package com.us517.today.activity;

import android.os.Bundle;

import com.us517.today.databinding.ActivityCreditBinding;

public class CreditActivity extends BaseActivity {
    ActivityCreditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}