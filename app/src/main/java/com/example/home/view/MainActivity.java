package com.example.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.home.R;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    private MaterialCardView personalActivityCard;
    private MaterialCardView personalPlanCard;
    private MaterialCardView familyActivityCard;
    private MaterialCardView familyPlanCard;
    private MaterialCardView smartHomeCard;
    private MaterialCardView billCard;

    private void findViews(){
        personalActivityCard = findViewById(R.id.Main_PersonalActivity);
        personalPlanCard = findViewById(R.id.Main_PersonalPlan);
        familyActivityCard = findViewById(R.id.Main_FamilyActivity);
        familyPlanCard = findViewById(R.id.Main_FamilyPlan);
        smartHomeCard = findViewById(R.id.Main_SmartHome);
        billCard = findViewById(R.id.Main_Bill);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setOnClickCardListeners();
    }


    private void setOnClickCardListeners(){
        Intent personalActivityIntent = new Intent(this, PersonalActivityActivity.class);
        personalActivityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(personalActivityIntent);
            }
        });

        Intent personalPlanIntent = new Intent(this, PersonalPlanActivity.class);
        personalPlanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(personalPlanIntent);
            }
        });

        Intent familyActivityIntent = new Intent(this, FamilyActivityActivity.class);
        familyActivityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(familyActivityIntent);
            }
        });

        Intent familyPlanIntent = new Intent(this, FamilyActivityActivity.class);
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
