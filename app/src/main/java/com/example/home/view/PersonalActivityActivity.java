package com.example.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.home.model.PersonalActivity;
import com.example.home.R;
import com.example.home.tools.Logger;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class PersonalActivityActivity extends AppCompatActivity {

    RecyclerView activityList;
    PersonalActivityAdapter personalActivityAdapter;
    FloatingActionButton fab;

    private void findViews() {
        activityList = findViewById(R.id.PersonalActivity_RecyclerView);
        fab = findViewById(R.id.PersonalActivity_FAB);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_activity);
        findViews();
        setUpRecyclerView();
        setOnClickListeners();
    }

    private void setUpRecyclerView() {
        activityList.hasFixedSize();
        activityList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<PersonalActivity> activities = new ArrayList<>();
        LocalTime time = LocalTime.of(15,30);

        activities.add(new PersonalActivity("Bulbasaur", "description", time,time,0,0));
        activities.add(new PersonalActivity("Ivysaur", "description", time,time,0,0));
        activities.add(new PersonalActivity("Venusaur", "description", time,time,0,0));
        activities.add(new PersonalActivity("Charmander", "description", time,time,0,0));
        activities.add(new PersonalActivity("Charmeleon", "description", time,time,0,0));
        activities.add(new PersonalActivity("Charizard", "description", time,time,0,0));
        activities.add(new PersonalActivity("Squirtle", "description", time,time,0,0));
        activities.add(new PersonalActivity("Wartortle", "description", time,time,0,0));
        activities.add(new PersonalActivity("Blastoise", "description", time,time,0,0));
        activities.add(new PersonalActivity("Caterpie", "description", time,time,0,0));
        activities.add(new PersonalActivity("Metapod", "description", time,time,0,0));
        activities.add(new PersonalActivity("Butterfree", "description", time,time,0,0));
        personalActivityAdapter = new PersonalActivityAdapter(activities);
        activityList.setAdapter(personalActivityAdapter);
    }

    private void setOnClickListeners() {
        Intent createActivityIntent = new Intent(this, CreatePersonalActivityActivity.class);
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atStartOfDay();
        Logger.Debug("","LocalDateTime:" + localDateTime.toString());
        createActivityIntent.putExtra("date",localDateTime);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(createActivityIntent);
            }
        });
    }

}