package com.example.home.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.home.model.PersonalActivityDAO;
import com.example.home.tools.Logger;

import java.util.ArrayList;

public class CreatePersonalActivityViewModel extends ViewModel {

    private ArrayList<PersonalActivityDAO> mPersonalActivities;


    public void createActivities() {
        Logger.Debug("","createActivities+++++++++++");
    }

    public boolean validate(PersonalActivityDAO personalActivityDAO) {
        if(checkStringEmpty(personalActivityDAO.getTitle())){

        }
        if(checkStringEmpty(personalActivityDAO.getDescription())){

        }


        return false;
    }

    private boolean checkStringEmpty(String str){
        if(str==null || str.isEmpty()){
            return true;
        }
        return false;
    }
}
