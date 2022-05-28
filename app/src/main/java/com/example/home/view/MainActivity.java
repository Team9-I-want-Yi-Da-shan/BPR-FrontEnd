package com.example.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.home.R;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    private MaterialCardView activityCard;
    private MaterialCardView planCard;
    private MaterialCardView smartHomeCard;
    private MaterialCardView billCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityCard = findViewById(R.id.Main_PersonalActivity);
        planCard = findViewById(R.id.Main_PersonalPlan);
        smartHomeCard = findViewById(R.id.Main_SmartHome);
        billCard = findViewById(R.id.Main_Bill);
        setOnClickCardListeners();
    }


    private void setOnClickCardListeners(){
        Intent activityActivityIntent = new Intent(this, ActivityActivity.class);
        activityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(activityActivityIntent);
            }
        });

        Intent planActivityIntent = new Intent(this, PlanActivity.class);
        planCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(planActivityIntent);
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
