package com.example.home.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.home.model.User;

public class MainViewModel extends ViewModel {
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
