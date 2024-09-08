package com.us517.today.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.us517.today.databinding.FragmentHomeBinding;
import com.us517.today.http.HttpService;
import com.us517.today.http.WrappedCallback;
import com.us517.today.model.DataModels;
import com.us517.today.model.Region;
import com.us517.today.restfulApi.PublicRegion;

import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        HttpService ht = HttpService.getInstance(this.getContext());
        //PublicRegion publicRegionService = HttpService.create(PublicRegion.class);

        ht.getPublicRegions( new WrappedCallback<DataModels<Region>>() {
            @Override
            public void onSuccess(DataModels<Region> result, Response response) {
                Log.d("Success", result.data.get(0).getNameEn());
            }

            @Override
            public void onError(int errorCode, String errorMessage) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}