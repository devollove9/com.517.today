package com.us517.today.ui.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.us517.today.R;
import com.us517.today.TodayApplication;
import com.us517.today.activity.AddressManageActivity;
import com.us517.today.activity.ContactActivity;
import com.us517.today.activity.CreditActivity;
import com.us517.today.activity.FavoriteActivity;
import com.us517.today.activity.LanguageSelectActivity;
import com.us517.today.activity.PaymentManageActivity;
import com.us517.today.activity.ProfileActivity;
import com.us517.today.activity.SettingActivity;

import com.us517.today.activity.SignInActivity;
import com.us517.today.databinding.FragmentAccountBinding;
import com.us517.today.databinding.FragmentRequestSignInBinding;


public class AccountFragment extends Fragment implements View.OnClickListener {

    private FragmentAccountBinding binding;
    private FragmentRequestSignInBinding binding2;
    ActivityResultLauncher launcher, launcher2;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // AccountViewModel accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        TodayApplication tApp = (TodayApplication) getActivity().getApplication();

        View root;
        if (!tApp.isSignedIn()) {
            NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment_activity_main);
            launcher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode() == Activity.RESULT_OK) {
                                navController.navigate(R.id.main_nav_account);
                            }
                        }
                    });
            binding2 = FragmentRequestSignInBinding.inflate(inflater,container, false);
            root = binding2.getRoot();
            binding2.requestSignIn.setOnClickListener(this);
        } else {
            binding = FragmentAccountBinding.inflate(inflater, container, false);
            if (launcher2 == null) {
                launcher2 = registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if(result.getResultCode() == Activity.RESULT_OK) {
                                    binding.accountNickname.setText(tApp.getUserNickname());
                                }
                            }
                        });
            }

            root = binding.getRoot();
            Glide.with(this).load("https://s3.amazonaws.com/1.us-east-1.517.today/default_avatar.jpg").apply(RequestOptions.skipMemoryCacheOf(true))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).into(binding.accountAvatar);
            binding.accountNickname.setText(tApp.getUserNickname());
            binding.accountAvatar.setOnClickListener(this);
            binding.accountAddress.setOnClickListener(this);
            binding.accountCredit.setOnClickListener(this);
            binding.accountFavorate.setOnClickListener(this);
            binding.accountPayment.setOnClickListener(this);
            binding.accountLanguage.setOnClickListener(this);
            binding.accountSetting.setOnClickListener(this);
            binding.accountContact.setOnClickListener(this);
        }
        /*

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            navController.navigate(R.id.main_nav_account);
                        }
                    }
                });*/

        // NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment_activity_main);
        // navController.navigate(R.id.main_nav_requestSignIn);

        // final TextView textView = binding.textAccount;
        //accountViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        //accountViewModel.setText("Account");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        binding2 = null;
        launcher = null;
        launcher2 = null;
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
        } else if (viewId == R.id.account_credit) {
            intent =  new Intent(this.getActivity(), CreditActivity.class);
        } else if (viewId == R.id.account_favorate) {
            intent =  new Intent(this.getActivity(), FavoriteActivity.class);
        } else if (viewId == R.id.request_signIn) {
            intent =  new Intent(this.getActivity(), SignInActivity.class);
        } else if (viewId == R.id.account_avatar) {
            intent =  new Intent(this.getActivity(), ProfileActivity.class);
        }
        if (intent != null) {
            if (viewId == R.id.request_signIn) {
                launcher.launch(intent);
            } else if (viewId == R.id.account_avatar) {
                launcher2.launch(intent);
            } else {
                startActivity(intent);
            }
            // getActivity().overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
        }
    }
}
