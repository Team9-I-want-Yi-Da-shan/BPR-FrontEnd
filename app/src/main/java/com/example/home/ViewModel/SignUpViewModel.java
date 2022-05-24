package com.example.home.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.home.Model.User;
import com.example.home.Networking.ServiceGenerator;
import com.example.home.Networking.UserApi;
import com.example.home.Networking.UserResponse;
import com.example.home.Tools.Logger;

import java.sql.SQLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class SignUpViewModel extends ViewModel {


    public void signUp(String username, String email, String password) {

        UserApi userApi = ServiceGenerator.getUserApi();
        Call<Void> call = userApi.signUp(new User(username,email,password));

        call.enqueue(new Callback<Void>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Logger.Debug("Retrofit", "Account has been created!");
                    //signUp successful
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Logger.Debug("Retrofit", "Something went wrong :(");
            }
        });
    }




//    private String attemptCreateUser(String username, String email, String password, String passwordConfirm ) {
//        UserApi userApi = ServiceGenerator.getUserApi();
//        UserResponse userResponse = new UserResponse();
//        List<User> users = userResponse.getAllUser();
//        for (int i = 0; i < users.size(); i++) {
//            if (username.equals(users.get(i).getUsername())) {
//                return "username already used";
//            } else (email.equals(users.get(i).getEmail())) {
//                return "eamil already used";
//            }
//        }
//            return validatePasswords(password, passwordConfirm);
//    }
//
//    private String validatePasswords (String password, String passwordConfirm){
//
//        if (password == null) {
//            return "Password cannot be empty";
//        }
//        if (!password.equals(passwordConfirm)) {
//            return "Passwords do not match";
//        }
//        return "OK";
//    }
}