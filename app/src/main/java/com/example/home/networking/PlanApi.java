package com.example.home.networking;

import com.example.home.model.FamilyPlan;
import com.example.home.model.PersonalPlan;
import com.example.home.networking.planResponse.AddFamilyPlanResponse;
import com.example.home.networking.planResponse.AddPersonPlanResponse;
import com.example.home.networking.planResponse.GetFamilyPlanByPlanIDResponse;
import com.example.home.networking.planResponse.GetFamilyPlanListByFamilyIDResponse;
import com.example.home.networking.planResponse.GetPersonPlanListByPersonIDResponse;
import com.example.home.networking.planResponse.GetPersonalPlanByPlanIDResponse;
import com.example.home.networking.planResponse.RemoveFamilyPlanByPlanIDResponse;
import com.example.home.networking.planResponse.RemovePersonalPlanByPlanIDResponse;
import com.example.home.networking.planResponse.UpdateFamilyPlanResponse;
import com.example.home.networking.planResponse.UpdatePersonalPlanResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlanApi {

    //PersonalPlan

    @POST("addPersonPlan")
    Call<AddPersonPlanResponse> addPersonalPlan(@Body PersonalPlan plan);

    @POST("getPersonalPlanByPlanID")
    Call<GetPersonalPlanByPlanIDResponse> getPersonalPlanByPlanID(@Body PersonalPlan plan);

    @PUT("updatePersonalPlan")
    Call<UpdatePersonalPlanResponse> updatePersonalPlan(@Path("id") int plan_id, @Body PersonalPlan plan);

//    @DELETE("plan/RemovePersonalPlanByPlanID")
    @HTTP(method = "DELETE", path = "plan/RemovePersonalPlanByPlanID", hasBody = true)
    Call<RemovePersonalPlanByPlanIDResponse> removePersonalPlanByPlanID(@Body PersonalPlan plan);

    @POST("getPersonPlanListByPersonID")
    Call<GetPersonPlanListByPersonIDResponse> getPersonPlanListByPersonID(@Body PersonalPlan plan);



    //FamilyPlan


    @POST("addFamilyPlan")
    Call<AddFamilyPlanResponse> addFamilyPlan(@Body FamilyPlan familyPlan);

    @POST("getFamilyPlanByPlanID")
    Call<GetFamilyPlanByPlanIDResponse> getFamilyPlanByPlanID(@Body FamilyPlan familyPlan);

    @PUT("updateFamilyPlan")
    Call<UpdateFamilyPlanResponse> updateFamilyPlan(@Path("id") int plan_id, @Body FamilyPlan familyPlan);

    @HTTP(method = "DELETE", path = "plan/RemoveFamilyPlanByPlanID", hasBody = true)
    Call<RemoveFamilyPlanByPlanIDResponse> removeFamilyPlanByPlanID(@Body FamilyPlan familyPlan);

    @POST("getFamilyPlanListByPersonID")
    Call<GetFamilyPlanListByFamilyIDResponse> getFamilyPlanListByFamilyID(@Body FamilyPlan familyPlan);
}
