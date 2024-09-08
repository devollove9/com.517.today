package com.us517.today.http;

import android.util.Log;

import com.google.gson.Gson;
import com.us517.today.model.BaseModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class WrappedCallback<T extends BaseModel> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (response.body().error.errorMessage == null) {
                Log.d("Success", response.body().toString());
                this.onSuccess(response.body(), response);
            } else {
                this.onError(response.body().error.errorCode, response.body().error.errorMessage);
            }

        }
        else {
            Log.d("Failure", "Unable to present request");
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        Log.d("Failure", "Unable to present request");
        throwable.printStackTrace();
    }

    public abstract void onSuccess(T result, Response response);

    public abstract void onError(int errorCode, String errorMessage);
}
