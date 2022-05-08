package com.example.home.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static UserApi userApi;

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
}
