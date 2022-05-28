package com.example.home.networking;

import com.example.home.model.PersonalActivityDAO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ActivityApi {
    @POST("addPersonalActivity")
    Call<CreatePersonalActivityResponse> createPersonalActivity(@Body PersonalActivityDAO personalActivityDAO);
}
