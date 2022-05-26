package com.example.home.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;



import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.home.Model.PersonalActivityDAO;
import com.example.home.R;
import com.example.home.ViewModel.CreateActivityViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class CreateActivityActivity extends AppCompatActivity {

    LocalDateTime localDateTime;

    CreateActivityViewModel createActivityVM;
    ArrayList<ViewHolder> viewHolders;

    LinearLayout linearLayoutContainer;
    MaterialToolbar topAppBar;
    MaterialButton addCreateActivityCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);

        localDateTime = (LocalDateTime) getIntent().getSerializableExtra("date");

        createActivityVM = new ViewModelProvider(this).get(CreateActivityViewModel.class);
        viewHolders = new ArrayList<ViewHolder>();

        linearLayoutContainer = findViewById(R.id.CreateActivity_LinearLayout);
        topAppBar = findViewById(R.id.CreateActivity_TopAppBar);
        addCreateActivityCardButton = findViewById(R.id.CreateActivity_AddCreateActivityCard);

        setOnClickListeners();
        onCreateViewHolder();
    }

    private void onCreateViewHolder() {
        LayoutInflater inflater = LayoutInflater.from(linearLayoutContainer.getContext());
        View personalActivity = inflater.inflate(R.layout.list_create_personal_activity_item, linearLayoutContainer, false);
        linearLayoutContainer.addView(personalActivity);
        viewHolders.add(new ViewHolder(personalActivity));
    }

    private void createPersonalActivities(){
        viewHolders.forEach( viewHolder -> {

            String title = viewHolder.titleEditText.getText().toString();
            String description = viewHolder.descriptionEditText.getText().toString();
            if(TextUtils.isEmpty(title)){
                Toast.makeText(this, "Activity" + viewHolder.position + "title is empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(description)){
                Toast.makeText(this, "Activity" + viewHolder.position + "description is empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if(viewHolder.startTime == null){
                Toast.makeText(this, "Activity" + viewHolder.position + "please set start time", Toast.LENGTH_SHORT).show();
                return;
            }
            if(viewHolder.endTime == null){
                Toast.makeText(this, "Activity" + viewHolder.position + "please set end time", Toast.LENGTH_SHORT).show();
                return;
            }
            long startTimeInEpoch = viewHolder.startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long endTimeInEpoch = viewHolder.endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

            PersonalActivityDAO personalActivityDAO = new PersonalActivityDAO(title,description,startTimeInEpoch,endTimeInEpoch);

            if(createActivityVM.validate(personalActivityDAO)){
                createActivityVM.addActivity(personalActivityDAO);
            }

        });
        createActivityVM.createActivities();
    }


    private void setOnClickListeners() {
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolders.clear();

                finish();
            }
        });

        topAppBar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.ok:
                    createPersonalActivities();
                    return true;
                default:
                    return false;
            }
        });

        addCreateActivityCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateViewHolder();
            }
        });
    }




    class ViewHolder {
        int position;
        LocalDateTime startTime;
        LocalDateTime endTime;

        ImageButton closeButton;

        TextInputEditText titleEditText;
        TextInputEditText descriptionEditText;

        LinearLayout startTimeLinearLayout;
        LinearLayout endTimeButtonLinearLayout;

        LinearLayout repeatLinearLayout;
        TextView repeatTimeTextView;

        LinearLayout remindLinearLayout;
        TextView remindTimeTextView;
        SwitchMaterial alarmSwitch;



        ViewHolder(View itemView) {
            position = viewHolders.size();

            closeButton = itemView.findViewById(R.id.CreatePersonalActivity_CloseCreateActivityCard);

            titleEditText = itemView.findViewById(R.id.CreatePersonalActivity_TitleTextField);
            descriptionEditText = itemView.findViewById(R.id.CreatePersonalActivity_DescriptionTextField);

            startTimeLinearLayout = itemView.findViewById(R.id.CreatePersonalActivity_StartTimeLayout);
            endTimeButtonLinearLayout = itemView.findViewById(R.id.CreatePersonalActivity_EndTimeLayout);

            repeatLinearLayout = itemView.findViewById(R.id.CreatePersonalActivity_RepeatLayout);
            repeatTimeTextView = itemView.findViewById(R.id.CreatePersonalActivity_RepeatTextView);

            remindLinearLayout = itemView.findViewById(R.id.CreatePersonalActivity_RemindLayout);
            remindTimeTextView = itemView.findViewById(R.id.CreatePersonalActivity_RemindTextView);
            alarmSwitch = itemView.findViewById(R.id.CreatePersonalActivity_RemindAlarmSwitch);

            setCloseButtonOnClickListener(closeButton);
            setTimeLayoutOnClickListener(startTimeLinearLayout);
            setTimeLayoutOnClickListener(endTimeButtonLinearLayout);
            setRemindLayoutOnClickListener();

            alarmSwitch.setVisibility(View.GONE);
        }

        private void setCloseButtonOnClickListener(ImageButton closeButton) {
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaterialCardView cardView = (MaterialCardView)v.getParent();
                    linearLayoutContainer.removeView(cardView);
                }
            });
        }

        private void setRemindLayoutOnClickListener() {
            remindLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if(){
//                        alarmSwitch.setVisibility(View.VISIBLE);
//                    }else {
//                        alarmSwitch.setVisibility(View.GONE);
//                    }
                }
            });

        }

        private void setTimeLayoutOnClickListener(LinearLayout linearLayout){
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // instance of MDC time picker
                    MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                            // set the title for the alert dialog
                            .setTitleText("SELECT YOUR TIMING")
                            // set the default hour for the
                            // dialog when the dialog opens
                            .setHour(12)
                            .setMinute(10)
                            // set the time format
                            // according to the region
                            .setTimeFormat(TimeFormat.CLOCK_12H)
                            .build();

                    materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int newHour = materialTimePicker.getHour();
                            int newMinute = materialTimePicker.getMinute();
//                            linearLayout.setText(newHour+":"+newMinute);

                            if(linearLayout.getId()==R.id.CreatePersonalActivity_StartTimeLayout){
                                startTime = localDateTime.plusHours(newHour).plusMinutes(newMinute);
                            }

                            if(linearLayout.getId()==R.id.CreatePersonalActivity_EndTimeLayout){
                                endTime = localDateTime.plusHours(newHour).plusMinutes(newMinute);
                            }
                        }
                    });

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    materialTimePicker.show(fragmentManager,"CreateActivityActivity");
                }
            });
        }




    }


}