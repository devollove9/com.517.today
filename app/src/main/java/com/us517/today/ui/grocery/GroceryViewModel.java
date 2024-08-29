package com.us517.today.ui.grocery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GroceryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GroceryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is grocery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}