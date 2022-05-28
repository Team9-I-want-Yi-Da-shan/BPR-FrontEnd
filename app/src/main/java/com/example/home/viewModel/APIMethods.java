package com.example.home.viewModel;

import com.example.home.model.PersonalPlan;
import com.example.home.networking.AddPersonPlanResponse;
import com.example.home.networking.GetPersonPlanListByPersonIDResponse;
import com.example.home.networking.GetPersonalPlanByPlanIDResponse;
import com.example.home.networking.RemovePersonalPlanByPlanID;
import com.example.home.networking.ServiceGenerator;
import com.example.home.networking.UpdatePersonalPlanResponse;
import com.example.home.networking.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIMethods {

    UserApi userApi = ServiceGenerator.getUserApi();

    //addPersonPlan
    public void addPersonPlan(String name, int person_id, String description, String comment){
        Call<AddPersonPlanResponse> call = userApi.addPersonalPlan(new PersonalPlan(name,person_id,description,comment));

        call.enqueue(new Callback<AddPersonPlanResponse>() {
            @Override
            public void onResponse(Call<AddPersonPlanResponse> call, Response<AddPersonPlanResponse> response) {

            }

            @Override
            public void onFailure(Call<AddPersonPlanResponse> call, Throwable t) {

            }
        });
    }


    //getPersonalPlanByPlanID
    public void getPersonalPlanByPlanID(int id){
        Call<GetPersonalPlanByPlanIDResponse> call = userApi.getPersonalPlanByPlanID(id);
        call.enqueue(new Callback<GetPersonalPlanByPlanIDResponse>() {
            @Override
            public void onResponse(Call<GetPersonalPlanByPlanIDResponse> call, Response<GetPersonalPlanByPlanIDResponse> response) {

            }

            @Override
            public void onFailure(Call<GetPersonalPlanByPlanIDResponse> call, Throwable t) {

            }
        });
    }

    //RemovePersonalPlanByPlanID

    public void removePersonalPlanByPlanID(int id){
        Call<RemovePersonalPlanByPlanID> call = userApi.removePersonalPlanByPlanID(id);
        call.enqueue(new Callback<RemovePersonalPlanByPlanID>() {
            @Override
            public void onResponse(Call<RemovePersonalPlanByPlanID> call, Response<RemovePersonalPlanByPlanID> response) {

            }

            @Override
            public void onFailure(Call<RemovePersonalPlanByPlanID> call, Throwable t) {

            }
        });
    }

    //updatePersonalPlan

    public void updatePersonalPlan(int plan_id,String name, int person_id, String description, String comment){
        Call<UpdatePersonalPlanResponse> call = userApi.updatePersonalPlan(plan_id,new PersonalPlan(name,person_id,description,comment));

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
        Call<GetPersonPlanListByPersonIDResponse> call = userApi.getPersonPlanListByPersonID(id);
        call.enqueue(new Callback<GetPersonPlanListByPersonIDResponse>() {
            @Override
            public void onResponse(Call<GetPersonPlanListByPersonIDResponse> call, Response<GetPersonPlanListByPersonIDResponse> response) {

            }

            @Override
            public void onFailure(Call<GetPersonPlanListByPersonIDResponse> call, Throwable t) {

            }
        });
    }



}
