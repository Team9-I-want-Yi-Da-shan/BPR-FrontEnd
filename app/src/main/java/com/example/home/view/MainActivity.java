package com.example.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.home.R;
import com.example.home.model.User;
import com.example.home.view.activity.ActivityActivity;
import com.example.home.view.plan.PlanActivity;
import com.example.home.view.profile.UserProfileActivity;
import com.example.home.viewModel.MainViewModel;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;

    private TextView userGreetingText;
    private MaterialCardView activityCard;
    private MaterialCardView planCard;
    private MaterialCardView smartHomeCard;
    private MaterialCardView billCard;
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setContentView(R.layout.activity_main);
        avatar = findViewById(R.id.Main_Avatar);
        userGreetingText = findViewById(R.id.Main_UserGreeting);
        activityCard = findViewById(R.id.Main_PersonalActivity);
        planCard = findViewById(R.id.Main_PersonalPlan);
        smartHomeCard = findViewById(R.id.Main_SmartHome);
        billCard = findViewById(R.id.Main_Bill);


        //get login user passed by login activity
        Bundle bundle = getIntent().getExtras();
        User user = (User)bundle.get("user");
        viewModel.setUser(user);
        //set greeting text
        userGreetingText.setText("Hi, "+user.getName());



        setOnClickCardListeners();
    }


    private void setOnClickCardListeners(){
        Intent profileActivityIntent = new Intent(this, UserProfileActivity.class);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(profileActivityIntent);
            }
        });

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
