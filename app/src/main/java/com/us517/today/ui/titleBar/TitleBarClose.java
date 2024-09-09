package com.us517.today.ui.titleBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.us517.today.databinding.FragmentTitleBarCloseBinding;

public class TitleBarClose extends Fragment implements View.OnClickListener{
    private FragmentTitleBarCloseBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTitleBarCloseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.titleClose.setOnClickListener(this);
        // context = (OnClickCallBack) this.getActivity();
        return root;
    }

    @Override
    public void onClick(View view) {
        this.getActivity().finish();
    }
}
