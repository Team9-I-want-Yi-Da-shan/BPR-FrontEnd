package com.example.home.networking;

import com.example.home.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @GET("users/login")
    Call<UserResponse> login(@Path("username") String username,@Path("password") String password);

    @POST("users/signUp")
    Call<Void> signUp(@Body User user);

}
