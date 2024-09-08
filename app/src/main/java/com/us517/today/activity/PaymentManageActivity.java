package com.us517.today.activity;

import android.os.Bundle;

import com.us517.today.databinding.ActivityPaymentManageBinding;

public class PaymentManageActivity extends BaseActivity {
    ActivityPaymentManageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentManageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}
