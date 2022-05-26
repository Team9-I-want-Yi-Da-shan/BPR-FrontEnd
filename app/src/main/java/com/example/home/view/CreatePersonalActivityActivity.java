package com.example.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;


import com.example.home.R;
import com.example.home.viewModel.CreatePersonalActivityViewModel;
import com.google.android.material.appbar.MaterialToolbar;


import java.time.LocalDateTime;

public class CreatePersonalActivityActivity extends AppCompatActivity {

    LocalDateTime localDateTime;

    CreatePersonalActivityViewModel vm;
    CreatePersonalActivityAdapter adapter;

    RecyclerView recyclerView;
    MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personal_activity);

        localDateTime = (LocalDateTime) getIntent().getSerializableExtra("date");

        vm = new ViewModelProvider(this).get(CreatePersonalActivityViewModel.class);

        topAppBar = findViewById(R.id.CreateActivity_TopAppBar);
        recyclerView = findViewById(R.id.CreateActivity_RecyclerView);

        setUpRecyclerView();
        setOnClickListeners();
    }


    private void setUpRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CreatePersonalActivityAdapter();
        recyclerView.setAdapter(adapter);
    }


    private void createPersonalActivities(){

//            String title = viewHolder.titleEditText.getText().toString();
//            String description = viewHolder.descriptionEditText.getText().toString();
//            if(TextUtils.isEmpty(title)){
//                Toast.makeText(this, "Activity" + viewHolder.position + "title is empty", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if(TextUtils.isEmpty(description)){
//                Toast.makeText(this, "Activity" + viewHolder.position + "description is empty", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if(viewHolder.startTime == null){
//                Toast.makeText(this, "Activity" + viewHolder.position + "please set start time", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if(viewHolder.endTime == null){
//                Toast.makeText(this, "Activity" + viewHolder.position + "please set end time", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            long startTimeInEpoch = viewHolder.startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//            long endTimeInEpoch = viewHolder.endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//
//            PersonalActivityDAO personalActivityDAO = new PersonalActivityDAO(title,description,startTimeInEpoch,endTimeInEpoch);
//
//            if(createActivityVM.validate(personalActivityDAO)){
//                createActivityVM.addActivity(personalActivityDAO);
//            }
//
//        createActivityVM.createActivities();
    }


    private void setOnClickListeners() {
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        topAppBar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.ok:
//                    createPersonalActivities();
                    return true;
                default:
                    return false;
            }
        });

    }





}