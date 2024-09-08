package com.us517.today.ui.account;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.us517.today.R;
import com.us517.today.activity.AddressManageActivity;
import com.us517.today.activity.ContactActivity;
import com.us517.today.activity.LanguageSelectActivity;
import com.us517.today.activity.PaymentManageActivity;
import com.us517.today.activity.SettingActivity;
import com.us517.today.databinding.FragmentAccountBinding;


public class AccountFragment extends Fragment implements View.OnClickListener {

    private FragmentAccountBinding binding;
    View fragmentView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.accountAddress.setOnClickListener(this);
        binding.accountPayment.setOnClickListener(this);
        binding.accountLanguage.setOnClickListener(this);
        binding.accountSetting.setOnClickListener(this);
        binding.accountContact.setOnClickListener(this);
        // final TextView textView = binding.textAccount;
        //accountViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        //accountViewModel.setText("Account");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        int viewId = view.getId();
        if (viewId == R.id.account_payment) {
            intent =  new Intent(this.getActivity(), PaymentManageActivity.class);
            // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.currentplaceHolder, new TargetFragment()).commit();
        } else if (viewId == R.id.account_address) {
            intent =  new Intent(this.getActivity(), AddressManageActivity.class);
        } else if (viewId == R.id.account_setting) {
            intent =  new Intent(this.getActivity(), SettingActivity.class);
        } else if (viewId == R.id.account_language) {
            intent =  new Intent(this.getActivity(), LanguageSelectActivity.class);
        } else if (viewId == R.id.account_contact) {
            intent =  new Intent(this.getActivity(), ContactActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
