package com.us517.today.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.us517.today.R;
import com.us517.today.TodayApplication;
import com.us517.today.databinding.ActivityProfileBinding;

public class ProfileActivity extends BaseActivity implements View.OnClickListener{
    ActivityProfileBinding binding;
    private boolean modified = false;
    private String avatarUrl = "https://s3.amazonaws.com/1.us-east-1.517.today/default_avatar.jpg";
    private String nickName = "Devo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Glide.with(this).load("https://s3.amazonaws.com/1.us-east-1.517.today/default_avatar.jpg").into(binding.profileAvatar);
        TodayApplication tApp = (TodayApplication) getApplication();
        nickName = tApp.getUserNickname();
        binding.profileNickname.setText(nickName);
        binding.profileUpdate.setOnClickListener(this);
        binding.profileNicknameEdit.setOnClickListener(this);
        binding.profileAvatarEdit.setOnClickListener(this);
        binding.profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        int viewId = view.getId();
        if(viewId != R.id.profile_nickname && viewId != R.id.profile_nickname_edit) {
            hideKeyboard(view);
            binding.profileNickname.clearFocus();
        }
        if (viewId == R.id.profile_avatar_edit) {
            // Change avatar
        } else if (viewId == R.id.profile_nickname_edit) {
            // Change nickname
            binding.profileNickname.setText(nickName);
            binding.profileNickname.setHint(R.string.profile_nickname_hint);
            binding.profileNickname.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(binding.profileNickname, InputMethodManager.SHOW_IMPLICIT);
        } else if (viewId == R.id.profile_update) {
            if (binding.profileNickname.getText().toString() != nickName) {
                TodayApplication tApp = (TodayApplication) getApplication();
                tApp.setUserNickname(binding.profileNickname.getText().toString());
                setResult(RESULT_OK);
            }
            // Check URL too
            finish();
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}