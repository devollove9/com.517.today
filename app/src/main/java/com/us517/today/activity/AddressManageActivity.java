package com.us517.today.activity;

import android.os.Bundle;

import com.us517.today.databinding.ActivityAddressManageBinding;

public class AddressManageActivity extends BaseActivity {
    ActivityAddressManageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressManageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}