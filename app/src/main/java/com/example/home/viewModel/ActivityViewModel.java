package com.example.home.viewModel;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.home.model.User;
import com.example.home.model.dataTransferObject.FamilyActivityDTO;
import com.example.home.model.dataTransferObject.GetFamilyActivityDTO;
import com.example.home.model.dataTransferObject.GetPersonalActivityDTO;
import com.example.home.model.dataTransferObject.PersonalActivityDTO;
import com.example.home.networking.ActivityApi;
import com.example.home.networking.ServiceGenerator;
import com.example.home.networking.activityResponse.AddPersonalActivityResponse;
import com.example.home.networking.activityResponse.GetFamilyActivityByDateResponse;
import com.example.home.networking.activityResponse.GetPersonalActivityByDateResponse;
import com.example.home.tool.Logger;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


public class ActivityViewModel extends ViewModel {

    User user;

    MutableLiveData<LocalDate> dateSelected;
    MutableLiveData<Integer> bottomNavigationSelectedItem;

    MutableLiveData<ArrayList<PersonalActivityDTO>> personalActivities;
    MutableLiveData<ArrayList<FamilyActivityDTO>> familyActivities;

    MutableLiveData<String> mPATitle;
    MutableLiveData<String> mPADescription;
    MutableLiveData<LocalDateTime> mPAStartTime;
    MutableLiveData<LocalDateTime> mPAEndTime;
    MutableLiveData<Integer> mPARemind;
    MutableLiveData<Integer> mPARepeat;
    MutableLiveData<Boolean> mPAAlarm;
    final String defaultMessage = "default";
    MutableLiveData<String> CreatePAMessage;
    final String waitMessage = "waiting";
    final String doneMessage = "done";
    final String failMessage = "fail";
    MutableLiveData<String> getPAMessage;

    ActivityApi activityApi;

    public ActivityViewModel(){
        dateSelected = new MutableLiveData<>();
        dateSelected.setValue(LocalDate.now());
        bottomNavigationSelectedItem = new MutableLiveData<>();
        bottomNavigationSelectedItem.setValue(0);

        personalActivities = new MutableLiveData<>();
        familyActivities = new MutableLiveData<>();

        mPATitle = new MutableLiveData<>();
        mPADescription = new MutableLiveData<>();
        mPAStartTime = new MutableLiveData<>();
        mPAEndTime = new MutableLiveData<>();
        mPARemind = new MutableLiveData<>();
        mPARepeat = new MutableLiveData<>();
        mPAAlarm = new MutableLiveData<>();
        mPARemind.setValue(-1);
        mPARepeat.setValue(-1);
        mPAAlarm.setValue(false);

        CreatePAMessage = new MutableLiveData<>();
        getPAMessage = new MutableLiveData<>();
        CreatePAMessage.setValue(defaultMessage);
        getPAMessage.setValue(defaultMessage);

        activityApi = ServiceGenerator.getActivityApi();
    }



    public String validate() {
        if(TextUtils.isEmpty(mPATitle.getValue())){return "Please Enter Activity Title";}
        if(TextUtils.isEmpty(mPADescription.getValue())){return "Please Enter Activity Description";}
        if(mPAStartTime.getValue()==null){return "Please Select Start Time";}
        return "ok";
    }

    public void createPersonalActivity() {
        Call<AddPersonalActivityResponse> call = activityApi.addPersonalActivity(createNewPersonalActivity());

        call.enqueue(new Callback<AddPersonalActivityResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AddPersonalActivityResponse> call, Response<AddPersonalActivityResponse> response) {
                if (response.isSuccessful()) {
                    //create successful
                    CreatePAMessage.setValue(response.body().getMessage());
                    Logger.debug("CreatePersonalActivity", "Something went wrong :(");
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AddPersonalActivityResponse> call, Throwable t) {
                Log.d("CreatePersonalActivity", "Something went wrong :(");
            }
        });
    }

    private PersonalActivityDTO createNewPersonalActivity() {
        PersonalActivityDTO personalActivityDTO = new PersonalActivityDTO();
        //TODO: Replace id with actual person id
        personalActivityDTO.setUser_id(user.getUserId());
        personalActivityDTO.setTitle(mPATitle.getValue());
        personalActivityDTO.setDescription(mPADescription.getValue());

        ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
        LocalDateTime start_at = mPAStartTime.getValue();
        LocalDateTime end_at = mPAEndTime.getValue();

        long startAtToEpoch = start_at.atZone(zoneId).toEpochSecond();
        long endAtToEpoch = end_at.atZone(zoneId).toEpochSecond();

        personalActivityDTO.setStart_at(startAtToEpoch);
        personalActivityDTO.setStart_at(endAtToEpoch);
        personalActivityDTO.setIsFinish(0);
        personalActivityDTO.setReminder(mPARemind.getValue());

        if(mPARepeat.getValue()!=-1){
            personalActivityDTO.setRepeat(1);
            personalActivityDTO.setInterval(mPARepeat.getValue());
        }else {
            personalActivityDTO.setRepeat(0);
        }

        if(mPAAlarm.getValue()){
            personalActivityDTO.setIs_alarm(1);
        }else {
            personalActivityDTO.setIs_alarm(0);
        }

        return personalActivityDTO;
    }

    public void sendGetPersonalActivitiesRequest(){
        getPAMessage.setValue(waitMessage);
        long epoch = dateSelected.getValue().atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

        GetPersonalActivityDTO getPersonalActivityDTO = new GetPersonalActivityDTO(user.getUserId(),epoch);
        Logger.debug("getPersonalActivity",getPersonalActivityDTO.toString());
        Call<GetPersonalActivityByDateResponse> call = activityApi.getPersonalActivityByDate(getPersonalActivityDTO);

        call.enqueue(new Callback<GetPersonalActivityByDateResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<GetPersonalActivityByDateResponse> call, Response<GetPersonalActivityByDateResponse> response) {
                if (response.isSuccessful()) {
                    getPAMessage.setValue(doneMessage);
                    personalActivities.setValue(response.body().getPersonalActivityDAOS());
                    Logger.debug("getPersonalActivity",response.body().toString());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<GetPersonalActivityByDateResponse> call, Throwable t) {
                getPAMessage.setValue(failMessage);
                Logger.debug("getPersonalActivity", "Something went wrong :(");
            }
        });
    }

    public void sendGetFamilyActivitiesResponse() {
        long epoch = dateSelected.getValue().atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

        Call<GetFamilyActivityByDateResponse> call = activityApi.getFamilyActivityByDate(new GetFamilyActivityDTO(user.getFamilyId(),epoch));

        call.enqueue(new Callback<GetFamilyActivityByDateResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<GetFamilyActivityByDateResponse> call, Response<GetFamilyActivityByDateResponse> response) {
                if (response.isSuccessful()) {
                    familyActivities.setValue(response.body().getFamilyActivityDAOS());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<GetFamilyActivityByDateResponse> call, Throwable t) {
                Logger.debug("getFamilyActivities", "Something went wrong :(");
            }
        });
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MutableLiveData<LocalDate> getDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(LocalDate dateSelected) {
        this.dateSelected.setValue(dateSelected);
    }

    public MutableLiveData<Integer> getBottomNavigationSelectedItem() {
        return bottomNavigationSelectedItem;
    }

    public void setBottomNavigationSelectedItem(MutableLiveData<Integer> bottomNavigationSelectedItem) {
        this.bottomNavigationSelectedItem = bottomNavigationSelectedItem;
    }

    public MutableLiveData<ArrayList<PersonalActivityDTO>> getPersonalActivities() {
        return personalActivities;
    }

    public void setPersonalActivities(MutableLiveData<ArrayList<PersonalActivityDTO>> personalActivities) {
        this.personalActivities = personalActivities;
    }

    public MutableLiveData<ArrayList<FamilyActivityDTO>> getFamilyActivities() {
        return familyActivities;
    }

    public void setFamilyActivities(MutableLiveData<ArrayList<FamilyActivityDTO>> familyActivities) {
        this.familyActivities = familyActivities;
    }

    public MutableLiveData<String> getmPATitle() {
        return mPATitle;
    }

    public void setmPATitle(String title) {
        this.mPATitle.setValue(title);
    }

    public MutableLiveData<String> getmPADescription() {
        return mPADescription;
    }

    public void setmPADescription(String description) {
        this.mPADescription.setValue(description);
    }

    public MutableLiveData<LocalDateTime> getmPAStartTime() {
        return mPAStartTime;
    }

    public void setmPAStartTime(LocalDateTime startTime) {
        this.mPAStartTime.setValue(startTime);
    }

    public MutableLiveData<LocalDateTime> getmPAEndTime() {
        return mPAEndTime;
    }

    public void setmPAEndTime(LocalDateTime endTime) {
        this.mPAEndTime.setValue(endTime);
    }

    public MutableLiveData<Integer> getmPARemind() {
        return mPARemind;
    }

    public void setmPARemind(Integer remind) {
        this.mPARemind.setValue(remind);
    }

    public MutableLiveData<Integer> getmPARepeat() {
        return mPARepeat;
    }

    public void setmPARepeat(Integer repeat) {
        this.mPARepeat.setValue(repeat);
    }

    public MutableLiveData<String> getCreatePAMessage() {
        return CreatePAMessage;
    }

    public void setCreatePAMessage(String createPAMessage) {
        CreatePAMessage.setValue(createPAMessage);
    }

    public MutableLiveData<String> getGetPAMessage() {
        return getPAMessage;
    }

    public void setGetPAMessage(String getPAMessage) {
        this.getPAMessage.setValue(getPAMessage);
    }
}
