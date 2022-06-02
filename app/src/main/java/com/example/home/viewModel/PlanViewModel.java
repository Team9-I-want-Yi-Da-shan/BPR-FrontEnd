package com.example.home.viewModel;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.home.model.FamilyPlan;
import com.example.home.model.PersonalPlan;
import com.example.home.model.User;
import com.example.home.model.dataTransferObject.FamilyPlanDTO;
import com.example.home.model.dataTransferObject.PersonalActivityDTO;
import com.example.home.model.dataTransferObject.PersonalPlanDTO;
import com.example.home.networking.planResponse.AddFamilyPlanResponse;
import com.example.home.networking.planResponse.AddPersonPlanResponse;
import com.example.home.networking.planResponse.GetFamilyPlanByPlanIDResponse;
import com.example.home.networking.planResponse.GetFamilyPlanListByFamilyIDResponse;
import com.example.home.networking.planResponse.GetPersonPlanListByPersonIDResponse;
import com.example.home.networking.planResponse.GetPersonalPlanByPlanIDResponse;
import com.example.home.networking.PlanApi;
import com.example.home.networking.planResponse.RemoveFamilyPlanByPlanIDResponse;
import com.example.home.networking.planResponse.RemovePersonalPlanByPlanIDResponse;
import com.example.home.networking.ServiceGenerator;
import com.example.home.networking.planResponse.UpdateFamilyPlanResponse;
import com.example.home.networking.planResponse.UpdatePersonalPlanResponse;
import com.example.home.tool.Logger;
import com.example.home.view.PlanActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanViewModel extends ViewModel {
    MutableLiveData<String> mPPTitle;
    MutableLiveData<String> mPPDescription;
    MutableLiveData<String> mPPComment;
    MutableLiveData<String> CreatePPMessage;
    MutableLiveData<String> CreateFPMessage;
    MutableLiveData<String> getPPMessage;

    MutableLiveData<String> mFPTitle;
    MutableLiveData<String> mFPDescription;
    MutableLiveData<String> mFPComment;
    final String defaultMessage = "default";
    User user;

    PlanApi planApi = ServiceGenerator.getPlanApi();
    private static final String TAG = PlanActivity.class.getSimpleName();


    public PlanViewModel(){

    mPPTitle = new MutableLiveData<>();
    mPPDescription = new MutableLiveData<>();
    mPPComment = new MutableLiveData<>();
    mFPTitle = new MutableLiveData<>();
    mFPDescription = new MutableLiveData<>();
    mFPComment = new MutableLiveData<>();
    CreatePPMessage = new MutableLiveData<>();
    CreateFPMessage = new MutableLiveData<>();
    CreateFPMessage.setValue(defaultMessage);
    CreatePPMessage.setValue(defaultMessage);
}


    public String validatePersonalPlan() {
        if(TextUtils.isEmpty(mPPTitle.getValue())){return "Please Enter Plan Title";}
        if(TextUtils.isEmpty(mPPDescription.getValue())){return "Please Enter Plan Description";}
        if(TextUtils.isEmpty(mPPComment.getValue())){return "Please Enter Plan Comment";}
        return "ok";
    }

    public String validateFamilyPlan() {
        if(TextUtils.isEmpty(mFPTitle.getValue())){return "Please Enter Plan Title";}
        if(TextUtils.isEmpty(mFPDescription.getValue())){return "Please Enter Plan Description";}
        if(TextUtils.isEmpty(mFPComment.getValue())){return "Please Enter Plan Comment";}
        return "ok";
    }

    public void createActivity() {}

    public void addPersonPlan(){
        PlanApi planApi = ServiceGenerator.getPlanApi();
        Call<AddPersonPlanResponse> call = planApi.addPersonalPlan(createNewPersonalPlan());

        call.enqueue(new Callback<AddPersonPlanResponse>() {
            @Override
            public void onResponse(Call<AddPersonPlanResponse> call, Response<AddPersonPlanResponse> response) {
                CreatePPMessage.setValue(response.body().getMessage());
                Logger.debug("Retrofit", "Plan has been created!");
            }

            @Override
            public void onFailure(Call<AddPersonPlanResponse> call, Throwable t) {
                Logger.debug("Retrofit", "Fail!");
            }
        });
    }



    public void removePersonalPlanByPlanID(int id){
        Call<RemovePersonalPlanByPlanIDResponse> call = planApi.removePersonalPlanByPlanID(new PersonalPlan(id));
        call.enqueue(new Callback<RemovePersonalPlanByPlanIDResponse>() {
            @Override
            public void onResponse(Call<RemovePersonalPlanByPlanIDResponse> call, Response<RemovePersonalPlanByPlanIDResponse> response) {
                Logger.debug("Retrofit", "Plan has been removed!");

            }

            @Override
            public void onFailure(Call<RemovePersonalPlanByPlanIDResponse> call, Throwable t) {
                Logger.debug("Retrofit", "Fail!");
            }

        });
    }

    //updatePersonalPlan

    public void updatePersonalPlan(int plan_id,String name, int person_id, String description, String comment){
        Call<UpdatePersonalPlanResponse> call = planApi.updatePersonalPlan(plan_id,new PersonalPlan(name,person_id,description,comment));

        call.enqueue(new Callback<UpdatePersonalPlanResponse>() {
            @Override
            public void onResponse(Call<UpdatePersonalPlanResponse> call, Response<UpdatePersonalPlanResponse> response) {

            }

            @Override
            public void onFailure(Call<UpdatePersonalPlanResponse> call, Throwable t) {

            }
        });
    }

    //getPersonPlanListByPersonID
    public void getPersonPlanListByPersonID(int id){
        Call<GetPersonPlanListByPersonIDResponse> call = planApi.getPersonPlanListByPersonID(new PersonalPlan(id));
        call.enqueue(new Callback<GetPersonPlanListByPersonIDResponse>() {
            @Override
            public void onResponse(Call<GetPersonPlanListByPersonIDResponse> call, Response<GetPersonPlanListByPersonIDResponse> response) {
                List<PersonalPlan> plans = response.body().getResults();
                Log.d(TAG, "Plan list: " + plans.size());
            }

            @Override
            public void onFailure(Call<GetPersonPlanListByPersonIDResponse> call, Throwable t) {

            }
        });
    }




    //getPersonalPlanByPlanID
    public void getPersonalPlanByByPlanID(int id){
        Call<GetPersonalPlanByPlanIDResponse> call = planApi.getPersonalPlanByPlanID(new PersonalPlan(id));
        call.enqueue(new Callback<GetPersonalPlanByPlanIDResponse>() {
            @Override
            public void onResponse(Call<GetPersonalPlanByPlanIDResponse> call, Response<GetPersonalPlanByPlanIDResponse> response) {

            }

            @Override
            public void onFailure(Call<GetPersonalPlanByPlanIDResponse> call, Throwable t) {

            }
        });
    }

    public void addFamilyPlan(){
        PlanApi planApi = ServiceGenerator.getPlanApi();
        Call<AddFamilyPlanResponse> call = planApi.addFamilyPlan(createNewFamilyPlan());

        call.enqueue(new Callback<AddFamilyPlanResponse>() {
            @Override
            public void onResponse(Call<AddFamilyPlanResponse> call, Response<AddFamilyPlanResponse> response) {
                CreateFPMessage.setValue(response.body().getMessage());
                Logger.debug("Retrofit", "Plan has been created!");
            }

            @Override
            public void onFailure(Call<AddFamilyPlanResponse> call, Throwable t) {
                Logger.debug("Retrofit", "Fail!");
            }
        });
    }

    private FamilyPlanDTO createNewFamilyPlan(){

        FamilyPlanDTO familyPlanDTO = new FamilyPlanDTO();
        familyPlanDTO.setFamily_id(user.getFamilyId());
        familyPlanDTO.setName(mFPTitle.getValue());
        familyPlanDTO.setDescription(mFPDescription.getValue());
        familyPlanDTO.setComment(mFPComment.getValue());


        return familyPlanDTO;
    }




    public void removeFamilyPlanByPlanID(int id){
        Call<RemoveFamilyPlanByPlanIDResponse> call = planApi.removeFamilyPlanByPlanID(new FamilyPlan(id));
        call.enqueue(new Callback<RemoveFamilyPlanByPlanIDResponse>() {
            @Override
            public void onResponse(Call<RemoveFamilyPlanByPlanIDResponse> call, Response<RemoveFamilyPlanByPlanIDResponse> response) {
                Logger.debug("Retrofit", "Plan has been removed!");

            }

            @Override
            public void onFailure(Call<RemoveFamilyPlanByPlanIDResponse> call, Throwable t) {
                Logger.debug("Retrofit", "Fail!");
            }

        });
    }





    public void updateFamilyPlan(int plan_id,String name, int person_id, String description, String comment){
        Call<UpdateFamilyPlanResponse> call = planApi.updateFamilyPlan(plan_id,new FamilyPlan(name,person_id,description,comment));

        call.enqueue(new Callback<UpdateFamilyPlanResponse>() {
            @Override
            public void onResponse(Call<UpdateFamilyPlanResponse> call, Response<UpdateFamilyPlanResponse> response) {

            }

            @Override
            public void onFailure(Call<UpdateFamilyPlanResponse> call, Throwable t) {

            }
        });
    }




    public void getFamilyPlanListByPersonID(int id){
        Call<GetFamilyPlanListByFamilyIDResponse> call = planApi.getFamilyPlanListByFamilyID(new FamilyPlan(id));
        call.enqueue(new Callback<GetFamilyPlanListByFamilyIDResponse>() {
            @Override
            public void onResponse(Call<GetFamilyPlanListByFamilyIDResponse> call, Response<GetFamilyPlanListByFamilyIDResponse> response) {
                List<FamilyPlan> plans = response.body().getResults();
                Log.d(TAG, "Plan list: " + plans.size());
            }

            @Override
            public void onFailure(Call<GetFamilyPlanListByFamilyIDResponse> call, Throwable t) {

            }
        });
    }



    public void getFamilyPlanByByPlanID(int id){
        Call<GetFamilyPlanByPlanIDResponse> call = planApi.getFamilyPlanByPlanID(new FamilyPlan(id));
        call.enqueue(new Callback<GetFamilyPlanByPlanIDResponse>() {
            @Override
            public void onResponse(Call<GetFamilyPlanByPlanIDResponse> call, Response<GetFamilyPlanByPlanIDResponse> response) {

            }

            @Override
            public void onFailure(Call<GetFamilyPlanByPlanIDResponse> call, Throwable t) {

            }
        });
    }


    private PersonalPlanDTO createNewPersonalPlan(){

        PersonalPlanDTO personalPlanDTO = new PersonalPlanDTO();
        personalPlanDTO.setUser_id(user.getUserId());
        personalPlanDTO.setName(mPPTitle.getValue());
        personalPlanDTO.setDescription(mPPDescription.getValue());
        personalPlanDTO.setComment(mPPComment.getValue());


        return personalPlanDTO;
    }


    public MutableLiveData<String> getmFPTitle() {
        return mFPTitle;
    }

    public void setmFPTitle(String mFPTitle) {
        this.mFPTitle.setValue(mFPTitle);
    }

    public MutableLiveData<String> getmFPDescription() {
        return mFPDescription;
    }

    public void setmFPDescription(String mFPDescription) {
        this.mFPDescription.setValue(mFPDescription);
    }

    public MutableLiveData<String> getmFPComment() {
        return mFPComment;
    }

    public void setmFPComment(String mFPComment) {
        this.mFPComment.setValue(mFPComment);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MutableLiveData<String> getCreatePPMessage() {
        return CreatePPMessage;
    }
    public MutableLiveData<String> getCreateFPMessage() {
        return CreateFPMessage;
    }

    public void setCreateFPMessage(String createFPMessage) {
        CreateFPMessage.setValue(createFPMessage);
    }


    public void setCreatePPMessage(String createPPMessage) {
        CreatePPMessage.setValue(createPPMessage);
    }

    public MutableLiveData<String> getGetPPMessage() {
        return getPPMessage;
    }

    public void setGetPPMessage(String getPPMessage) {
        this.getPPMessage.setValue(getPPMessage);
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
