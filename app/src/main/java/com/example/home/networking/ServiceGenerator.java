package com.example.home.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static UserApi userApi;
    private static ActivityApi activityApi;
    private static PlanApi planApi;

    public static UserApi getUserApi() {
        if (userApi == null) {
            userApi = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.20:9000/v1/user/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserApi.class);
        }
        return userApi;
    }

    public static ActivityApi getActivityApi() {
        if (activityApi == null) {
            activityApi = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.20:9000/v1/activity/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ActivityApi.class);
        }
        return activityApi;
    }

    public static PlanApi getPlanApi() {
        if (planApi == null) {
            planApi = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.20:9000/v1/plan/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PlanApi.class);
        }
        return planApi;
    }

}
