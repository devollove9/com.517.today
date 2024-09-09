package com.us517.today.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class RequestSignInFragment extends Fragment {
    /*
    FragmentRequestsigninBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRequestsigninBinding.inflate(inflater,container, false);
        View root = binding.getRoot();
        binding.requestSignIn.setOnClickListener(this);
        Button button = getActivity().findViewById(R.id.request_signIn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("asdasdasdasdasd","----------------------------------");
            }
        });

        return root;
    }
    @Override
    public void onClick(View view) {
        Log.d("asdasdasdasdasd","----------------------------------");
        Intent intent =  new Intent(this.getActivity(), SignInActivity.class);
        startActivity(intent);
    }*/
}
