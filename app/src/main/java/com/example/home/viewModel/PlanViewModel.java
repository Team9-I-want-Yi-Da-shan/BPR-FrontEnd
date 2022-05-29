package com.example.home.viewModel;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.home.model.PersonalPlan;
import com.example.home.networking.AddPersonPlanResponse;
import com.example.home.networking.PlanApi;
import com.example.home.networking.ServiceGenerator;
import com.example.home.tool.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanViewModel extends ViewModel {
    MutableLiveData<String> mPPTitle;
    MutableLiveData<String> mPPDescription;
    MutableLiveData<String> mPPComment;

public PlanViewModel(){

    mPPTitle = new MutableLiveData<>();
    mPPDescription = new MutableLiveData<>();
    mPPComment = new MutableLiveData<>();
}


    public String validate() {
        if(TextUtils.isEmpty(mPPTitle.getValue())){return "Please Enter Plan Title";}
        if(TextUtils.isEmpty(mPPDescription.getValue())){return "Please Enter Plan Description";}
        if(TextUtils.isEmpty(mPPComment.getValue())){return "Please Enter Plan Comment";}
        addPersonPlan(mPPTitle.toString(),17,mPPDescription.toString(),mPPComment.toString());
        return "ok";
    }

    public void createActivity() {}

    public void addPersonPlan(String name, int user_id, String description, String comment){
        PlanApi planApi = ServiceGenerator.getPlanApi();
        Call<AddPersonPlanResponse> call = planApi.addPersonalPlan(new PersonalPlan(name,user_id,description,comment));

        call.enqueue(new Callback<AddPersonPlanResponse>() {
            @Override
            public void onResponse(Call<AddPersonPlanResponse> call, Response<AddPersonPlanResponse> response) {
                Logger.debug("Retrofit", "Plan has been created!");
            }

            @Override
            public void onFailure(Call<AddPersonPlanResponse> call, Throwable t) {
                Logger.debug("Retrofit", "Fail!");
            }
        });
    }


    public MutableLiveData<String> getmPPTitle() {
        return mPPTitle;
    }

    public void setmPPTitle(String title) {
        this.mPPTitle.setValue(title);
    }

    public MutableLiveData<String> getmPPDescription() {
        return mPPDescription;
    }

    public void setmPPDescription(String description) {
        this.mPPDescription.setValue(description);
    }

    public MutableLiveData<String> getmPPComment() {
        return mPPComment;
    }

    public void setmPPComment(String comment) {
        this.mPPComment.setValue(comment);
    }
}
