package com.example.home.networking;

import com.example.home.model.User;
import com.example.home.networking.userResponse.LoginResponse;
import com.example.home.networking.userResponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("login")
    Call<LoginResponse> login(@Body User user);

    @POST("register")
    Call<SignUpResponse> signUp(@Body User user);




}
