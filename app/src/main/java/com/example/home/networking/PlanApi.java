package com.example.home.networking;

import com.example.home.model.FamilyPlan;
import com.example.home.model.PersonalPlan;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlanApi {

    //PersonalPlan

    @POST("plan/addPersonPlan")
    Call<AddPersonPlanResponse> addPersonalPlan(@Body PersonalPlan plan);

    @POST("plan/getPersonalPlanByPlanID")
    Call<GetPersonalPlanByPlanIDResponse> getPersonalPlanByPlanID(@Body PersonalPlan plan);

    @PUT("plan/updatePersonalPlan")
    Call<UpdatePersonalPlanResponse> updatePersonalPlan(@Path("id") int plan_id,@Body PersonalPlan plan);

//    @DELETE("plan/RemovePersonalPlanByPlanID")
    @HTTP(method = "DELETE", path = "plan/RemovePersonalPlanByPlanID", hasBody = true)
    Call<RemovePersonalPlanByPlanIDResponse> removePersonalPlanByPlanID(@Body PersonalPlan plan);

    @POST("plan/getPersonPlanListByPersonID")
    Call<GetPersonPlanListByPersonIDResponse> getPersonPlanListByPersonID(@Body PersonalPlan plan);



    //FamilyPlan


    @POST("plan/addFamilyPlan")
    Call<AddFamilyPlanResponse> addFamilyPlan(@Body FamilyPlan familyPlan);

    @POST("plan/getFamilyPlanByPlanID")
    Call<GetFamilyPlanByPlanIDResponse> getFamilyPlanByPlanID(@Body FamilyPlan familyPlan);

    @PUT("plan/updateFamilyPlan")
    Call<UpdateFamilyPlanResponse> updateFamilyPlan(@Path("id") int plan_id,@Body FamilyPlan familyPlan);

    @HTTP(method = "DELETE", path = "plan/RemoveFamilyPlanByPlanID", hasBody = true)
    Call<RemoveFamilyPlanByPlanIDResponse> removeFamilyPlanByPlanID(@Body FamilyPlan familyPlan);

    @POST("plan/getFamilyPlanListByPersonID")
    Call<GetFamilyPlanListByFamilyIDResponse> getFamilyPlanListByFamilyID(@Body FamilyPlan familyPlan);
}
