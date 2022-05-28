package com.example.home.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.home.R;
import com.example.home.viewModel.ActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class ActivityActivity extends AppCompatActivity {

    ActivityViewModel viewModel;
    FragmentManager fragmentManager;

    FrameLayout createActivityFrameLayout;
    FloatingActionButton fab;
    BottomNavigationView bottomNavigationView;

    CreatePersonalActivityFragment createPersonalActivityFragment;
    TimePickerFragment timePickerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        viewModel = new ViewModelProvider(this).get(ActivityViewModel.class);

        createPersonalActivityFragment = CreatePersonalActivityFragment.newInstance();

        createActivityFrameLayout = findViewById(R.id.Activity_CreateActivityFrameLayout);
        fab = findViewById(R.id.Activity_FAB);
        bottomNavigationView = findViewById(R.id.Activity_BottomNavigation);
        createActivityFrameLayout.setVisibility(View.GONE);

        fragmentManager = getSupportFragmentManager();
        addMyActivityFragment();

        setOnClickListeners();
    }


    public void closeCreateActivityFragment(){
        createActivityFrameLayout.setVisibility(View.GONE);
    }

    public void addMyActivityFragment(){
        fragmentManager.beginTransaction()
                .add(R.id.Activity_ActivityFrameLayout, PersonalActivityFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("personalActivity") // name can be null
                .commit();
    }

    public void addFamilyActivityFragment(){
        fragmentManager.beginTransaction()
                .add(R.id.Activity_ActivityFrameLayout, FamilyActivityFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("familyActivity") // name can be null
                .commit();
    }


    //manage create activity fragments
    public void addCreatePersonalActivityFragment(){
        fragmentManager.beginTransaction()
                .add(R.id.Activity_CreateActivityFrameLayout, createPersonalActivityFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("createPersonalActivity") // name can be null
                .commit();
    }


    public void addCreateFamilyActivityFragment(){
        fragmentManager.beginTransaction()
                .add(R.id.Activity_CreateActivityFrameLayout, CreatePersonalActivityFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("createFamilyActivity") // name can be null
                .commit();
    }

    public void addTimePickerFragment(int activityAndTimeType){
        timePickerFragment = TimePickerFragment.newInstance(activityAndTimeType);
        fragmentManager.beginTransaction()
                .add(R.id.Activity_CreateActivityFrameLayout, timePickerFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("AddTimePicker") // name can be null
                .commit();
    }

    public void removeCreatePersonalActivityFragment(){
        fragmentManager.beginTransaction()
                .remove(timePickerFragment)
                .setReorderingAllowed(true)
                .addToBackStack("RemoveTimePicker") // name can be null
                .commit();
    }



    private void setOnClickListeners() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Activity_Navigation_MyActivity:
                        addMyActivityFragment();
                        return true;
                    case R.id.Activity_Navigation_FamilyActivity:
                        addFamilyActivityFragment();
                        return true;
                    default:
                        return false;
                }
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomNavigationView.getSelectedItemId()==R.id.Activity_Navigation_MyActivity){
                    addCreatePersonalActivityFragment();
                    createActivityFrameLayout.setVisibility(View.VISIBLE);
                }
                if(bottomNavigationView.getSelectedItemId()==R.id.Activity_Navigation_FamilyActivity){
                    addCreateFamilyActivityFragment();
                    createActivityFrameLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }




}