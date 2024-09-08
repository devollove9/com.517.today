package com.us517.today.restfulApi;

import com.us517.today.model.AppVersion;
import com.us517.today.model.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface PublicAppVersion {
    @GET("/public/appversion")
    Call<DataModel<AppVersion>> getPublicAppVersion(@Query("platform") String platform);
}
