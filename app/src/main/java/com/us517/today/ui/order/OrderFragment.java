package com.us517.today.ui.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.us517.today.R;
import com.us517.today.TodayApplication;
import com.us517.today.activity.SignInActivity;
import com.us517.today.databinding.FragmentOrderBinding;
import com.us517.today.databinding.FragmentRequestSignInBinding;

public class OrderFragment extends Fragment implements View.OnClickListener {

    private FragmentOrderBinding binding;
    private FragmentRequestSignInBinding binding2;
    ActivityResultLauncher launcher;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OrderViewModel orderViewModel =
                new ViewModelProvider(this).get(OrderViewModel.class);
        View root;
        TodayApplication tApp = (TodayApplication) getActivity().getApplication();
        if (!tApp.isSignedIn()) {
            NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment_activity_main);
            launcher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(), result -> {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            navController.navigate(R.id.main_nav_order);
                        }
                    });
            binding2 = FragmentRequestSignInBinding.inflate(inflater,container, false);
            root = binding2.getRoot();
            //root = inflater.inflate(R.layout.fragment_request_sign_in, container, false);
            binding2.requestSignIn.setOnClickListener(this);
        } else {
            binding = FragmentOrderBinding.inflate(inflater, container, false);
            root = binding.getRoot();
            final TextView textView = binding.textOrder;
            orderViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
            orderViewModel.setText("Orders");
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        binding2 = null;
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        int viewId = view.getId();
        if (viewId == R.id.request_signIn) {
            intent =  new Intent(this.getActivity(), SignInActivity.class);
        }
        if (intent != null) {
            if (viewId == R.id.request_signIn) {
                launcher.launch(intent);
            } else {
                startActivity(intent);
            }
            // getActivity().overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
        }
    }
}