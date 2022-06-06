package com.example.home.viewModel;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.home.model.User;
import com.example.home.model.dataTransferObject.FamilyActivityDTO;
import com.example.home.networking.Apis.ActivityApi;
import com.example.home.networking.Apis.ServiceGenerator;
import com.example.home.networking.activityCall.GetFamilyActivityCall;
import com.example.home.networking.activityCall.GetPersonalActivityCall;
import com.example.home.model.dataTransferObject.PersonalActivityDTO;
import com.example.home.networking.activityResponse.AddPersonalActivityResponse;
import com.example.home.networking.activityResponse.GetFamilyActivityByDateResponse;
import com.example.home.networking.activityResponse.ResultResponse1;
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

    MutableLiveData<String> getPAMessage;
    MutableLiveData<String> createPAMessage;
    final String defaultMessage = "default";
    final String waitMessage = "waiting";
    final String doneMessage = "done";
    final String failMessage = "fail";


    ActivityApi activityApi;

    public ActivityViewModel(){
        dateSelected = new MutableLiveData<>(LocalDate.now());
        bottomNavigationSelectedItem = new MutableLiveData<>(0);

        personalActivities = new MutableLiveData<>();
        personalActivities.setValue(new ArrayList<PersonalActivityDTO>());
        familyActivities = new MutableLiveData<>();
        familyActivities.setValue(new ArrayList<FamilyActivityDTO>());

        mPATitle = new MutableLiveData<>();
        mPADescription = new MutableLiveData<>();
        mPAStartTime = new MutableLiveData<>();
        mPAEndTime = new MutableLiveData<>();
        mPARemind = new MutableLiveData<>(-1);
        mPARepeat = new MutableLiveData<>(-1);
        mPAAlarm = new MutableLiveData<>(false);

        getPAMessage = new MutableLiveData<>(defaultMessage);
        createPAMessage = new MutableLiveData<>(defaultMessage);

        activityApi = ServiceGenerator.getActivityApi();
    }



    public String validate(String title, String description) {
        if(TextUtils.isEmpty(title)){return "Please Enter Activity Title";}
        if(TextUtils.isEmpty(description)){return "Please Enter Activity Description";}
        if(mPAStartTime.getValue()==null){return "Please Select Start Time";}
        mPATitle.setValue(title);
        mPADescription.setValue(description);
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
                    createPAMessage.setValue(response.body().getMessage());
                    Logger.debug("CreatePersonalActivity", createPAMessage.getValue());
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

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime start_at = mPAStartTime.getValue();
        LocalDateTime end_at = mPAEndTime.getValue();

        long startAtToEpoch = start_at.atZone(zoneId).toEpochSecond();
        personalActivityDTO.setStart_at(startAtToEpoch);

        if(end_at!=null){
            long endAtToEpoch = end_at.atZone(zoneId).toEpochSecond();
            personalActivityDTO.setFinish_at(endAtToEpoch);
        }

        personalActivityDTO.setReminder(mPARemind.getValue());

        if(mPARepeat.getValue()!=-1){
            personalActivityDTO.setIs_repeat(1);
            personalActivityDTO.setRepeat_interval(mPARepeat.getValue());
        }else {
            personalActivityDTO.setIs_repeat(0);
        }

        if(mPAAlarm.getValue()){
            personalActivityDTO.setIsAlarm(1);
        }else {
            personalActivityDTO.setIsAlarm(0);
        }

        personalActivityDTO.setIsFinish(0);

        return personalActivityDTO;
    }

    public void sendGetPersonalActivitiesRequest(){
        getPAMessage.setValue(waitMessage);
        long epoch = dateSelected.getValue().atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

        GetPersonalActivityCall getPersonalActivityCall = new GetPersonalActivityCall(user.getUserId(),epoch);
        Call<ResultResponse1> call = activityApi.getPersonalActivityByDate(getPersonalActivityCall);

        call.enqueue(new Callback<ResultResponse1>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ResultResponse1> call, Response<ResultResponse1> response) {
                if (response.isSuccessful()) {
                    ArrayList<PersonalActivityDTO> personalActivityDTOS =
                            response.body().getResults().get(0).getPersonalActivityDAOS();
                    personalActivities.setValue(personalActivityDTOS);
                    getPAMessage.setValue(doneMessage);
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ResultResponse1> call, Throwable t) {
                getPAMessage.setValue(t.getMessage());
            }
        });
    }

    public void sendGetFamilyActivitiesResponse() {
        long epoch = dateSelected.getValue().atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

        Call<GetFamilyActivityByDateResponse> call = activityApi.getFamilyActivityByDate(new GetFamilyActivityCall(user.getFamilyId(),epoch));

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

    public void setBottomNavigationSelectedItem(Integer bottomNavigationSelectedItem) {
        this.bottomNavigationSelectedItem.setValue(bottomNavigationSelectedItem);
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
        return createPAMessage;
    }

    public void setCreatePAMessage(String createPAMessage) {
        this.createPAMessage.setValue(createPAMessage);
    }

    public MutableLiveData<String> getGetPAMessage() {
        return getPAMessage;
    }

    public void setGetPAMessage(String getPAMessage) {
        this.getPAMessage.setValue(getPAMessage);
    }
}
