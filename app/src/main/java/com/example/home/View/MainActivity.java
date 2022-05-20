package com.example.home.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.home.R;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    private MaterialCardView personalScheduleCard;
    private MaterialCardView personalPlanCard;
    private MaterialCardView familyScheduleCard;
    private MaterialCardView familyPlanCard;
    private MaterialCardView smartHomeCard;
    private MaterialCardView billCard;

    private void findViews(){
        personalScheduleCard = findViewById(R.id.Main_PersonalActivity);
        personalPlanCard = findViewById(R.id.Main_PersonalActivity);
        familyScheduleCard = findViewById(R.id.Main_PersonalActivity);
        familyPlanCard = findViewById(R.id.Main_PersonalActivity);
        smartHomeCard = findViewById(R.id.Main_PersonalActivity);
        billCard = findViewById(R.id.Main_PersonalActivity);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setClickCardListeners();
    }










    private void setClickCardListeners(){
        Intent personalScheduleIntent = new Intent(this, PersonalScheduleActivity.class);
        personalScheduleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(personalScheduleIntent);
            }
        });

        Intent personalPlanIntent = new Intent(this, PersonalPlanActivity.class);
        personalPlanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(personalPlanIntent);
            }
        });

        Intent familyScheduleIntent = new Intent(this, FamilyScheduleActivity.class);
        familyScheduleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(familyScheduleIntent);
            }
        });

        Intent familyPlanIntent = new Intent(this, FamilyScheduleActivity.class);
        familyPlanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(familyPlanIntent);
            }
        });

        Intent smartHomeIntent = new Intent(this, SmartHomeActivity.class);
        smartHomeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(smartHomeIntent);
            }
        });

        Intent billIntent = new Intent(this, BillActivity.class);
        billCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(billIntent);
            }
        });

    }

}
