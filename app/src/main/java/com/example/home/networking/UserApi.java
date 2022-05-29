package com.example.home.networking;

import com.example.home.model.User;
import com.example.home.model.PersonalPlan;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {
    @POST("user/login")
    Call<LoginResponse> login(@Body User user);

    @POST("user/register")
    Call<SignUpResponse> signUp(@Body User user);




}
