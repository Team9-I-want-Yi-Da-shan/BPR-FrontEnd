package com.example.home.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.home.model.User;
import com.example.home.networking.ServiceGenerator;
import com.example.home.networking.UserApi;
import com.example.home.networking.LoginResponse;
import com.example.home.tool.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LoginViewModel extends ViewModel {


    MutableLiveData<Boolean> isLoggedin = new MutableLiveData<>();
    private String JsonResponseUsername;

    public void login(String email, String password){
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<LoginResponse> call = userApi.login(new User(email, password));

        call.enqueue(new Callback<LoginResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    JsonResponseUsername = response.body().getUser().getUsername();
                    Log.d("Username",JsonResponseUsername);
                    isLoggedin.setValue(true);
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Logger.debug("Retrofit", "Something went wrong :(");
                isLoggedin.setValue(false);
            }
        });
    }

    public LiveData<Boolean> getisLoggedin()
    {
        return isLoggedin;
    }

    public String getJsonResponseUsername(){return JsonResponseUsername;}

}
