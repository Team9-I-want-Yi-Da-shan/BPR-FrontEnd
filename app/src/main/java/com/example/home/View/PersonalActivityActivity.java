package com.example.home.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.home.Model.PersonalActivity;
import com.example.home.R;
import com.example.home.Tools.Logger;

import java.util.ArrayList;
import java.util.Date;

public class PersonalActivityActivity extends AppCompatActivity {

    RecyclerView activityList;
    ActivityAdapter activityAdapter;

    private void findViews() {
        activityList = findViewById(R.id.PersonalActivity_RecyclerView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_activity);
        Logger.Debug("","进pa界面了");
        findViews();
        setUpRecyclerView();
        setClickListeners();
    }

    private void setUpRecyclerView() {
        activityList.hasFixedSize();
        activityList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<PersonalActivity> activities = new ArrayList<>();
        String time = "17:00";

        activities.add(new PersonalActivity("Bulbasaur", "description", time,time,"null","null"));
        activities.add(new PersonalActivity("Ivysaur", "description", time,time,"null","null"));
        activities.add(new PersonalActivity("Venusaur", "description", time,time,"null","null"));
        activities.add(new PersonalActivity("Charmander", "description", time,time,"null","null"));
        activities.add(new PersonalActivity("Charmeleon", "description", time,time,"null","null"));
        activities.add(new PersonalActivity("Charizard", "description", time,time,"null","null"));
        activities.add(new PersonalActivity("Squirtle", "description", time,time,"null","null"));
        activities.add(new PersonalActivity("Wartortle", "description", time,time,"null","null"));
        activities.add(new PersonalActivity("Blastoise", "description", time,time,"null","null"));
        activities.add(new PersonalActivity("Caterpie", "description", time,time,"null","null"));
        activities.add(new PersonalActivity("Metapod", "description", time,time,"null","null"));
        activities.add(new PersonalActivity("Butterfree", "description", time,time,"null","null"));
        activityAdapter = new ActivityAdapter(activities);
        activityList.setAdapter(activityAdapter);
    }

    private void setClickListeners() {

    }

}