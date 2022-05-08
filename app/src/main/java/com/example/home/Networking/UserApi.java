package com.example.home.Networking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {
    @GET("users/login")
    Call<UserResponse> login(@Path("username") String username,@Path("password") String password);

}
