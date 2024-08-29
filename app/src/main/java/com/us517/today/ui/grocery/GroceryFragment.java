package com.us517.today.ui.grocery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.us517.today.databinding.FragmentGroceryBinding;

public class GroceryFragment extends Fragment {

    private FragmentGroceryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GroceryViewModel groceryViewModel =
                new ViewModelProvider(this).get(GroceryViewModel.class);

        binding = FragmentGroceryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGrocery;
        groceryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}