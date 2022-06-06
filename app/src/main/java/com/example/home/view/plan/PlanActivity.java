package com.example.home.view.plan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.home.R;
import com.example.home.model.User;
import com.example.home.view.plan.CreateFamilyPlanFragment;
import com.example.home.view.plan.CreatePersonalPlanFragment;
import com.example.home.view.plan.FamilyPlanFragment;
import com.example.home.view.plan.PersonalPlanFragment;
import com.example.home.viewModel.PlanViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class PlanActivity extends AppCompatActivity {


    PlanViewModel viewModel;
    FragmentManager fragmentManager;

    FrameLayout createPlanFrameLayout;
    FloatingActionButton fab;
    BottomNavigationView bottomNavigationView;

    CreatePersonalPlanFragment createPersonalPlanFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        viewModel = new ViewModelProvider(this).get(PlanViewModel.class);

        createPersonalPlanFragment = CreatePersonalPlanFragment.newInstance();

        createPlanFrameLayout = findViewById(R.id.Plan_CreatePlanFrameLayout);
        fab = findViewById(R.id.Plan_FAB);
        bottomNavigationView = findViewById(R.id.Plan_BottomNavigation);
        createPlanFrameLayout.setVisibility(View.GONE);

        fragmentManager = getSupportFragmentManager();
        addMyPlanFragment();
        setOnClickListeners();
        getUserPreference();
    }

    public void closeCreatePlanFragment(){
        createPlanFrameLayout.setVisibility(View.GONE);
    }

    public void addMyPlanFragment(){
        fragmentManager.beginTransaction()
                .add(R.id.Plan_PlanFrameLayout, PersonalPlanFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("personalPlan") // name can be null
                .commit();
    }

    public void addFamilyPlanFragment(){
        fragmentManager.beginTransaction()
                .add(R.id.Plan_PlanFrameLayout, FamilyPlanFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("familyPlan") // name can be null
                .commit();
    }

    public void addCreatePersonalPlanFragment(){
        fragmentManager.beginTransaction()
                .add(R.id.Plan_CreatePlanFrameLayout, createPersonalPlanFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("createPersonalPlan") // name can be null
                .commit();
    }

    public void addCreateFamilyPlanFragment(){
        fragmentManager.beginTransaction()
                .add(R.id.Plan_CreatePlanFrameLayout, CreateFamilyPlanFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("createFamilyPlan") // name can be null
                .commit();
    }


    private void setOnClickListeners() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Plan_Navigation_MyPlan:
                        addMyPlanFragment();
                        return true;
                    case R.id.Plan_Navigation_FamilyPlan:
                        addFamilyPlanFragment();
                        return true;
                    default:
                        return false;
                }
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomNavigationView.getSelectedItemId()==R.id.Plan_Navigation_MyPlan){
                    addCreatePersonalPlanFragment();
                    createPlanFrameLayout.setVisibility(View.VISIBLE);
                }
                if(bottomNavigationView.getSelectedItemId()==R.id.Plan_Navigation_FamilyPlan){
                    addCreateFamilyPlanFragment();
                    createPlanFrameLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void getUserPreference() {
        SharedPreferences prefs = getSharedPreferences("UserPreference", MODE_PRIVATE);
        int userId = prefs.getInt("userId",-1);
        String userName = prefs.getString("userName","");
        String userEmail = prefs.getString("userEmail","");
        int familyId = prefs.getInt("familyId",-1);
        viewModel.setUser(new User(userId,userName,userEmail,familyId));
    }



}