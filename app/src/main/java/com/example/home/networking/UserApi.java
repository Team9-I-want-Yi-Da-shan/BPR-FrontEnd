package com.example.home.networking;

import com.example.home.model.User;
import com.example.home.model.PersonalPlan;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {
    @POST("user/login")
    Call<LoginResponse> login(@Body User user);

    @POST("user/register")
    Call<SignUpResponse> signUp(@Body User user);


    @POST("plan/addPersonPlan")
    Call<AddPersonPlanResponse> addPersonalPlan(@Body PersonalPlan plan);

    @POST("plan/getPersonalPlanByPlanID")
    Call<GetPersonalPlanByPlanIDResponse> getPersonalPlanByPlanID(@Path("id") int id);

    @PUT("plan/updatePersonalPlan")
    Call<UpdatePersonalPlanResponse> updatePersonalPlan(@Path("id") int plan_id,@Body PersonalPlan plan);

    @DELETE("plan/RemovePersonalPlanByPlanID")
    Call<RemovePersonalPlanByPlanID> removePersonalPlanByPlanID(@Path("id") int id);

    @POST("plan/getPersonPlanListByPersonID")
    Call<GetPersonPlanListByPersonIDResponse> getPersonPlanListByPersonID(@Path("id") int id);


}
