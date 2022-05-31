package com.example.home.viewModel;

import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.home.model.User;
import com.example.home.networking.ServiceGenerator;
import com.example.home.networking.UserApi;
import com.example.home.networking.userResponse.LoginResponse;
import com.example.home.tool.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LoginViewModel extends ViewModel {


    MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>();
    private User user;


    public String validateAndLogin(String email, String password) {
        if (TextUtils.isEmpty(email)){
            return "Please enter email";
        }else if (TextUtils.isEmpty(password)){
            return "Please enter password";
        } else {
            login(email, password);
            return "ok";
        }
    }

    public void login(String email, String password){
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<LoginResponse> call = userApi.login(new User(email, password));

        call.enqueue(new Callback<LoginResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    user = response.body().getUser();
                    isLoggedIn.setValue(true);
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Logger.debug("Retrofit", "Something went wrong :(");
                isLoggedIn.setValue(false);
            }
        });
    }

    public LiveData<Boolean> getIsLoggedIn()
    {
        return isLoggedIn;
    }

    public User getResponseUser(){return user;}




}
