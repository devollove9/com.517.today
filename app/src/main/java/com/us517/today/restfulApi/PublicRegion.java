package com.us517.today.restfulApi;

import com.us517.today.model.DataModels;
import com.us517.today.model.Region;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface PublicRegion {
    @GET("public/region")
    Call<DataModels<Region>> getPublicRegions();

    @GET("public/region")
    Call<DataModels<Region>> getPublicRegion(@Query("regionId") String regionId);
}