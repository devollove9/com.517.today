package com.us517.today.http;

import android.content.Context;
import android.util.Log;

import com.us517.today.model.AppVersion;
import com.us517.today.model.DataModel;
import com.us517.today.model.DataModels;
import com.us517.today.model.Region;
import com.us517.today.restfulApi.PublicAppVersion;
import com.us517.today.restfulApi.PublicRegion;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class HttpService{

    public static final String API_URL = "https://apiv2.517.today";

    private Context context;
    private static Retrofit retrofit;
    private static HttpService instance;

    private HttpService(Context context) {

        this.context = context;
        if (retrofit == null) {
            //RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
    }

    public static HttpService getInstance(Context context) {
        if (instance == null) {
            instance = new HttpService(context);
        }
        return instance;
    }
    public void getAppVersion(String platform, WrappedCallback<DataModel<AppVersion>> callback) {
        PublicAppVersion rService = retrofit.create(PublicAppVersion.class);
        Call<DataModel<AppVersion>> call = rService.getPublicAppVersion(platform);
        call.enqueue(callback);
    }

    public void getPublicRegion (String regionId, WrappedCallback<DataModels<Region>> callback) {
        PublicRegion rService = retrofit.create(PublicRegion.class);
        Call<DataModels<Region>> call = rService.getPublicRegion(regionId);
        call.enqueue(callback);
    }

    public void getPublicRegions (WrappedCallback<DataModels<Region>> callback) {
        PublicRegion rService = retrofit.create(PublicRegion.class);
        Call<DataModels<Region>> call = rService.getPublicRegions();
        call.enqueue(callback);
    }
    public static void testApi() {
        Log.d("333333333333333", "33333333333333333333333333");
    }


/*
    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }


    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);
    }

    public static void main(String... args) throws IOException {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        // Create an instance of our GitHub API interface.
        GitHub github = retrofit.create(GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Contributor>> call = github.contributors("square", "retrofit");

        // Fetch and print a list of the contributors to the library.
        List<Contributor> contributors = call.execute().body();
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
    }*/
}
