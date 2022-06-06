package com.example.home.viewModel;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.home.model.User;
import com.example.home.networking.Apis.ServiceGenerator;
import com.example.home.networking.Apis.UserApi;
import com.example.home.networking.userResponse.LoginResponse;
import com.example.home.tool.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LoginViewModel extends ViewModel {


    MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>();
    final String defaultMessage = "default";
    MutableLiveData<String> CreateLoginMessage;
    MutableLiveData<String> email;
    MutableLiveData<String> password;
    User user;

    public LoginViewModel(){
        CreateLoginMessage = new MutableLiveData<>();
        CreateLoginMessage.setValue(defaultMessage);
        email = new MutableLiveData<>();
        password = new MutableLiveData<>();
    }

    public String validate() {
        if(TextUtils.isEmpty(email.getValue())){return "Please Enter Email";}
        if(TextUtils.isEmpty(password.getValue())){return "Please Enter Password";}
        return "ok";
    }

    public void login(){
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<LoginResponse> call = userApi.login(loginUser());

        call.enqueue(new Callback<LoginResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    user = response.body().getUser();
                    CreateLoginMessage.setValue(response.body().getMessage());
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

    private User loginUser(){
        User userDTO = new User();
        userDTO.setEmail(email.getValue());
        userDTO.setPassword(password.getValue());
        return userDTO;
    }

    public LiveData<Boolean> getIsLoggedIn()
    {
        return isLoggedIn;
    }

    public User getResponseUser(){return user;}


    public MutableLiveData<String> getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password.setValue(password);
    }

    public MutableLiveData<String> getCreateLoginMessage() {
        return CreateLoginMessage;
    }

    public void setCreateLoginMessage(String createLoginMessage) {
        CreateLoginMessage.setValue(createLoginMessage);
    }
}
