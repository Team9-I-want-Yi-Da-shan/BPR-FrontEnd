package com.example.home.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static UserApi userApi;
    private static ActivityApi activityApi;

    public static UserApi getUserApi() {
        if (userApi == null) {
            userApi = new Retrofit.Builder()
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserApi.class);
        }
        return userApi;
    }

    public static ActivityApi getActivityApi() {
        if (activityApi == null) {
            activityApi = new Retrofit.Builder()
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ActivityApi.class);
        }
        return activityApi;
    }
}
