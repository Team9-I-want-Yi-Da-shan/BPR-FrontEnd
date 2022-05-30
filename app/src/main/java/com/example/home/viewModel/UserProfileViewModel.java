package com.example.home.viewModel;

import android.text.TextUtils;

import androidx.lifecycle.ViewModel;

import com.example.home.model.User;

import java.util.ArrayList;

public class UserProfileViewModel extends ViewModel {




    public String validateAndCreateFamily(String familyName) {
        if (TextUtils.isEmpty(familyName)){
            return "Please Enter Family Name";
        }
        else {
            createFamily();
            return "ok";
        }
    }

    //TODO create family interface
    private void createFamily() {

    }

    //TODO get family interface
    public ArrayList<User> getFamilyMembers() {
        return null;
    }



}
