package com.us517.today.ui.titleBar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.us517.today.databinding.FragmentTitleBarBinding;

public class TitleBarFragment extends Fragment implements View.OnClickListener{
    private FragmentTitleBarBinding binding;
    //  private OnClickCallBack context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTitleBarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.tittleBack.setOnClickListener(this);
        // context = (OnClickCallBack) this.getActivity();
        return root;
    }

    @Override
    public void onClick(View view) {
        // Log.d("11111","1111111111111111");
        this.getActivity().finish();
    }
}
