package com.example.home.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.home.R;
import com.example.home.model.User;
import com.example.home.tool.Logger;
import com.example.home.viewModel.ActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ActivityActivity extends AppCompatActivity {

    ActivityViewModel viewModel;
    FragmentManager fragmentManager;

    FrameLayout createActivityFrameLayout;

    ImageButton backButton;
    ImageButton calendarButton;
    TextView dateTextView;
    FloatingActionButton fab;
    BottomNavigationView bottomNavigationView;

    MaterialDatePicker datePicker;

    PersonalActivityFragment personalActivityFragment;
    FamilyActivityFragment familyActivityFragment;

    CreatePersonalActivityFragment createPersonalActivityFragment;
    CreateFamilyActivityFragment createFamilyActivityFragment;
    TimePickerFragment timePickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        viewModel = new ViewModelProvider(this).get(ActivityViewModel.class);
        viewModel.setDateSelected(LocalDate.now());
        getUserPreference();

        createActivityFrameLayout = findViewById(R.id.Activity_CreateActivityFrameLayout);
        backButton = findViewById(R.id.Activity_BackToMain);
        calendarButton = findViewById(R.id.Activity_Calendar);
        fab = findViewById(R.id.Activity_FAB);
        bottomNavigationView = findViewById(R.id.Activity_BottomNavigation);
        dateTextView = findViewById(R.id.Activity_DateTextView);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String dateString = viewModel.getDateSelected().getValue().format(formatter);
        dateTextView.setText(dateString);

        createActivityFrameLayout.setVisibility(View.GONE);

        personalActivityFragment = PersonalActivityFragment.newInstance();
        familyActivityFragment = FamilyActivityFragment.newInstance();
        createPersonalActivityFragment = CreatePersonalActivityFragment.newInstance();
        createFamilyActivityFragment = CreateFamilyActivityFragment.newInstance();

        fragmentManager = getSupportFragmentManager();
        replaceWithMyActivityFragment();

        datePicker = MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setTitleText("Select date")
                .build();

        setOnClickListeners();
        setDateSelectedListener();
    }

    private void getUserPreference() {
        SharedPreferences prefs = getSharedPreferences("UserPreference", MODE_PRIVATE);
        int userId = prefs.getInt("userId",-1);
        String userName = prefs.getString("userName","");
        String userEmail = prefs.getString("userEmail","");
        int familyId = prefs.getInt("familyId",-1);
        viewModel.setUser(new User(userId,userName,userEmail,familyId));
    }


    public void closeCreateActivityFragment(){
        createActivityFrameLayout.setVisibility(View.GONE);
    }



    //manage activity fragments
    public void replaceWithMyActivityFragment(){
        replaceFragmentToActivityFrameLayout(personalActivityFragment,"replacePersonalActivity");
        Logger.debug("replace", "replaceWithMyActivityFragment");
    }

    public void replaceWithFamilyActivityFragment(){
        replaceFragmentToActivityFrameLayout(familyActivityFragment,"replaceFamilyActivity");
    }

    private void replaceFragmentToActivityFrameLayout(Fragment fragment, String stack){
        fragmentManager.beginTransaction()
                .replace(R.id.Activity_ActivityFrameLayout, fragment, null)
                .setReorderingAllowed(true)
                .addToBackStack(stack) // name can be null
                .commit();
    }



    //manage create activity fragments
    public void addCreatePersonalActivityFragment(){
        addFragmentToCreateActivityFrameLayout(createPersonalActivityFragment,"addCreatePersonalActivity");
    }

    public void removeCreatePersonalActivityFragment(){
        removeFragmentFromCreateActivityFrameLayout(createPersonalActivityFragment,"removeCreatePersonalActivity");
    }

    public void addCreateFamilyActivityFragment(){
        addFragmentToCreateActivityFrameLayout(createFamilyActivityFragment,"addCreateFamilyActivity");
    }

    public void removeCreateFamilyActivityFragment(){
        removeFragmentFromCreateActivityFrameLayout(createFamilyActivityFragment,"removeCreateFamilyActivity");
    }

    public void addTimePickerFragment(int activityAndTimeType){
        timePickerFragment = TimePickerFragment.newInstance(activityAndTimeType);
        addFragmentToCreateActivityFrameLayout(timePickerFragment,"AddTimePicker");
    }

    public void removeTimePickerFragment(){
        removeFragmentFromCreateActivityFrameLayout(timePickerFragment,"removeTimePicker");
    }

    private void addFragmentToCreateActivityFrameLayout(Fragment fragment, String stack){
        fragmentManager.beginTransaction()
                .add(R.id.Activity_ActivityFrameLayout, fragment, null)
                .setReorderingAllowed(true)
                .addToBackStack(stack) // name can be null
                .commit();
    }

    private void removeFragmentFromCreateActivityFrameLayout(Fragment fragment, String stack){
        fragmentManager.beginTransaction()
                .remove(fragment)
                .setReorderingAllowed(true)
                .addToBackStack(stack) // name can be null
                .commit();
    }





    private void setDateSelectedListener() {
        viewModel.getDateSelected().observe(this, new Observer<LocalDate>() {
            @Override
            public void onChanged(LocalDate localDate) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
                String dateString = viewModel.getDateSelected().getValue().format(formatter);
                dateTextView.setText(dateString);
            }
        });
    }



    private void setOnClickListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(fragmentManager,"datePicker");
            }
        });

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                LocalDate localDate = Instant.ofEpochMilli((Long) selection).atZone(ZoneId.systemDefault()).toLocalDate();
                viewModel.setDateSelected(localDate);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Activity_Navigation_MyActivity:
                        replaceWithMyActivityFragment();
                        return true;
                    case R.id.Activity_Navigation_FamilyActivity:
                        replaceWithFamilyActivityFragment();
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