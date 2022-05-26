package com.example.home.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.home.networking.ServiceGenerator;
import com.example.home.networking.UserApi;
import com.example.home.networking.UserResponse;
import com.example.home.tools.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LoginViewModel extends ViewModel {



    public void login(String username, String password){
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.login(username, password);

        call.enqueue(new Callback<UserResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    //login successful
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Logger.Debug("Retrofit", "Something went wrong :(");
            }
        });
    }






}
