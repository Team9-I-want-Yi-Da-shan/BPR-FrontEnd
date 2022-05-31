package com.example.home.networking;

import com.example.home.model.dataTransferObject.FamilyActivityDTO;
import com.example.home.model.dataTransferObject.FinishActivityDTO;
import com.example.home.model.dataTransferObject.GetFamilyActivityDTO;
import com.example.home.model.dataTransferObject.GetPersonalActivityDTO;
import com.example.home.model.dataTransferObject.PersonalActivityDTO;
import com.example.home.networking.activityResponse.AddFamilyActivityResponse;
import com.example.home.networking.activityResponse.AddPersonalActivityResponse;
import com.example.home.networking.activityResponse.FinishActivityResponse;
import com.example.home.networking.activityResponse.GetFamilyActivityByDateResponse;
import com.example.home.networking.activityResponse.GetPersonalActivityByDateResponse;
import com.example.home.networking.activityResponse.UpdateActivityResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ActivityApi {

    //general
    @POST("setFinish")
    Call<FinishActivityResponse> setActivityFinish(@Body FinishActivityDTO finishActivityDTO);


    //personal activity
    @POST("addPersonalActivity")
    Call<AddPersonalActivityResponse> addPersonalActivity(@Body PersonalActivityDTO personalActivityDTO);

    @POST("getPersonActivityByDateID")
    Call<GetPersonalActivityByDateResponse> getPersonalActivityByDate(@Body GetPersonalActivityDTO getPersonalActivityDTO);

    @PUT("updateActivity")
    Call<UpdateActivityResponse> updatePersonalActivity(@Body PersonalActivityDTO personalActivityDTO);



    //family activity
    @POST("addFamilyActivity")
    Call<AddFamilyActivityResponse> addFamilyActivity(@Body FamilyActivityDTO familyActivityDTO);

    @POST("getFamilyActivityByDateID")
    Call<GetFamilyActivityByDateResponse> getFamilyActivityByDate(@Body GetFamilyActivityDTO getFamilyActivityDTO);

    @PUT("updateActivity")
    Call<UpdateActivityResponse> updateFamilyActivity(@Body FamilyActivityDTO familyActivityDTO);

}
