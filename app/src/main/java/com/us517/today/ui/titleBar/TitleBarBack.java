package com.us517.today.ui.titleBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.us517.today.databinding.FragmentTitleBarBackBinding;

public class TitleBarBack extends Fragment implements View.OnClickListener{
    private FragmentTitleBarBackBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTitleBarBackBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.titleBack.setOnClickListener(this);
        // context = (OnClickCallBack) this.getActivity();
        return root;
    }

    @Override
    public void onClick(View view) {
        this.getActivity().finish();
    }
}
