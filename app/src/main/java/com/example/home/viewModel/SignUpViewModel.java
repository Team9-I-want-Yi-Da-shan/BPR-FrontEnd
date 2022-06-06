package com.example.home.viewModel;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.home.model.User;
import com.example.home.networking.Apis.ServiceGenerator;
import com.example.home.networking.Apis.UserApi;
import com.example.home.networking.userResponse.SignUpResponse;
import com.example.home.tool.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class SignUpViewModel extends ViewModel {

    MutableLiveData<Boolean> isAccountCreated = new MutableLiveData<>();

    //Number check
    public static final String REG_NUMBER = ".*\\d+.*";
    //Special character check
    public static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";
    //Email check
    public static final String REG_EMAIL = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";


    public String validateAndSignUp(String username, String email, String password, String passwordConfirm) {
        if (TextUtils.isEmpty(username)){
            return "You do not insert username!";
        }else if (TextUtils.isEmpty(email)){
            return "You do not insert email!";
        }else if (TextUtils.isEmpty(password)){
            return "You do not insert password!";
        }else if (TextUtils.isEmpty(passwordConfirm)){
            return "You do not insert confirm!";
        }else if (!password.equals(passwordConfirm)){
            return "Different password!";
        }else if (username.matches(REG_NUMBER)){
            return "No number in username!";
        }else if (username.matches(REG_SYMBOL)){
            return "No special character in username!";
        }else if (!email.matches(REG_EMAIL)){
            return "Please enter right email!";
        } else {
            signUp(username, email, password);
            return "ok";
        }
    }

    public void signUp(String username, String email, String password) {

        UserApi userApi = ServiceGenerator.getUserApi();
        Call<SignUpResponse> call = userApi.signUp(new User(username,email,password));

        call.enqueue(new Callback<SignUpResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    if (response.isSuccessful()){
                        Logger.debug("Retrofit", "Account has been created!");
                        isAccountCreated.setValue(true);
                    }else{
                        Log.d("Failed Reason",response.errorBody().toString());
                    }

            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    Logger.debug("Retrofit", "Something went wrong" + t.getMessage());
                    isAccountCreated.setValue(false);
            }
        });
    }

    public LiveData<Boolean> getisAccountCreated()
    {
        return isAccountCreated;
    }



}