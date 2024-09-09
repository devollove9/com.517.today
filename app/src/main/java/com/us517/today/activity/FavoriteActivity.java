package com.us517.today.activity;

import android.os.Bundle;

import com.us517.today.databinding.ActivityFavoriteBinding;

public class FavoriteActivity extends BaseActivity {
    ActivityFavoriteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}