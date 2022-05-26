package com.example.home.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.home.Model.PersonalActivityDAO;
import com.example.home.Tools.Logger;

import java.util.ArrayList;

public class CreateActivityViewModel extends ViewModel {

    private ArrayList<PersonalActivityDAO> mPersonalActivities;

    public void addActivity(PersonalActivityDAO personalActivityDAO) {
        mPersonalActivities.add(personalActivityDAO);
    }


    public void setTitle(int position, String title){
        mPersonalActivities.get(position).setTitle(title);
    }

    public void setDescription(int position, String description){
        mPersonalActivities.get(position).setDescription(description);
    }

    public void setStartTime(int position, long startTime){
        mPersonalActivities.get(position).setStartTime(startTime);
    }

    public void setEndTime(int position, long endTime){
        mPersonalActivities.get(position).setEndTime(endTime);
    }

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
