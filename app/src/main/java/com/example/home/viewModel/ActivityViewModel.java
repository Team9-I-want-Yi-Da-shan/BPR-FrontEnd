package com.example.home.viewModel;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.home.model.PersonalActivityDAO;
import com.example.home.networking.ActivityApi;
import com.example.home.networking.CreatePersonalActivityResponse;
import com.example.home.networking.ServiceGenerator;
import com.example.home.tool.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ActivityViewModel extends ViewModel {

    LocalDate dateSelected;

    MutableLiveData<String> mPATitle;
    MutableLiveData<String> mPADescription;
    MutableLiveData<LocalDateTime> mPAStartTime;
    MutableLiveData<LocalDateTime> mPAEndTime;
    MutableLiveData<Integer> mPARemind;
    MutableLiveData<Integer> mPARepeat;
    MutableLiveData<Boolean> mPAAlarm;


    ActivityApi activityApi;

    public ActivityViewModel(){
        dateSelected = LocalDate.now();
//        activityApi = ServiceGenerator.getActivityApi();

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
    }



    public String validate() {
        if(TextUtils.isEmpty(mPATitle.getValue())){return "Please Enter Activity Title";}
        if(TextUtils.isEmpty(mPADescription.getValue())){return "Please Enter Activity Description";}
        if(mPAStartTime.getValue()==null){return "Please Select Start Time";}
        return "ok";
    }

    public void createActivity() {
//        Call<CreatePersonalActivityResponse> call = activityApi.createPersonalActivity(createNewPersonalActivityDAO());
//
//        call.enqueue(new Callback<CreatePersonalActivityResponse>() {
//            @EverythingIsNonNull
//            @Override
//            public void onResponse(Call<CreatePersonalActivityResponse> call, Response<CreatePersonalActivityResponse> response) {
//                if (response.isSuccessful()) {
//                    //create successful
//                }
//            }
//            @EverythingIsNonNull
//            @Override
//            public void onFailure(Call<CreatePersonalActivityResponse> call, Throwable t) {
//                Logger.debug("Retrofit", "Something went wrong :(");
//            }
//        });
    }

    private PersonalActivityDAO createNewPersonalActivityDAO() {
        PersonalActivityDAO personalActivityDAO = new PersonalActivityDAO();
        //TODO: Replace id with actual person id
        personalActivityDAO.setPerson_id(1);
        personalActivityDAO.setTitle(mPATitle.getValue());
        personalActivityDAO.setDescription(mPADescription.getValue());

        ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
        LocalDateTime start_at = mPAStartTime.getValue();
        LocalDateTime end_at = mPAEndTime.getValue();

        long startAtToEpoch = start_at.atZone(zoneId).toEpochSecond();
        long endAtToEpoch = end_at.atZone(zoneId).toEpochSecond();

        personalActivityDAO.setStart_at(startAtToEpoch);
        personalActivityDAO.setStart_at(endAtToEpoch);
        personalActivityDAO.setIsFinish(0);
        personalActivityDAO.setReminder(mPARemind.getValue());

        if(mPARepeat.getValue()!=-1){
            personalActivityDAO.setRepeat(1);
            personalActivityDAO.setRepeat_interval(mPARepeat.getValue());
        }else {
            personalActivityDAO.setRepeat(0);
        }

        if(mPAAlarm.getValue()){
            personalActivityDAO.setIs_alarm(1);
        }else {
            personalActivityDAO.setIs_alarm(0);
        }
        return personalActivityDAO;
    }


    public LocalDate getDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(LocalDate dateSelected) {
        this.dateSelected = dateSelected;
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
}
