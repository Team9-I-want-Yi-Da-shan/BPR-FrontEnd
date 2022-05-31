package com.example.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.R;
import com.example.home.viewModel.ActivityViewModel;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDateTime;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreatePersonalActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePersonalActivityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    ActivityActivity activity;
    ActivityViewModel viewModel;

    ImageButton closeButton;
    ImageButton createButton;

    TextInputEditText titleEditText;
    TextInputEditText descriptionEditText;

    LinearLayout startTimeLinearLayout;
    TextView startTimeTextView;
    LinearLayout endTimeLinearLayout;
    TextView endTimeTextView;

    LinearLayout repeatLinearLayout;
    TextView repeatTimeTextView;

    LinearLayout remindLinearLayout;
    TextView remindTimeTextView;
    SwitchMaterial alarmSwitch;

    public CreatePersonalActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment CreatePersonalActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatePersonalActivityFragment newInstance() {
        CreatePersonalActivityFragment fragment = new CreatePersonalActivityFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        viewModel = new ViewModelProvider(getActivity()).get(ActivityViewModel.class);
        activity = (ActivityActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_personal_activity, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        closeButton = getView().findViewById(R.id.CreatePersonalActivity_CloseCreateActivityCard);
        createButton = getView().findViewById(R.id.CreatePersonalActivity_CreateActivityButton);
        titleEditText = getView().findViewById(R.id.CreatePersonalActivity_TitleTextField);
        descriptionEditText = getView().findViewById(R.id.CreatePersonalActivity_DescriptionTextField);
        startTimeLinearLayout = getView().findViewById(R.id.CreatePersonalActivity_StartTimeLayout);
        startTimeTextView = getView().findViewById(R.id.CreatePersonalActivity_StartTimeText);
        endTimeLinearLayout = getView().findViewById(R.id.CreatePersonalActivity_EndTimeLayout);
        endTimeTextView = getView().findViewById(R.id.CreatePersonalActivity_EndTimeText);
        repeatLinearLayout = getView().findViewById(R.id.CreatePersonalActivity_RepeatLayout);
        repeatTimeTextView = getView().findViewById(R.id.CreatePersonalActivity_RepeatTextView);
        remindLinearLayout = getView().findViewById(R.id.CreatePersonalActivity_RemindLayout);
        remindTimeTextView = getView().findViewById(R.id.CreatePersonalActivity_RemindTextView);
        alarmSwitch = getView().findViewById(R.id.CreatePersonalActivity_RemindAlarmSwitch);

        setOnClickListeners();
        setCreateActivityTimeOnChangeObservers();
        setCreateActivityResultOnChangeObservers();
    }



    private void setOnClickListeners() {
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validationResult = viewModel.validate(titleEditText.getText().toString(),descriptionEditText.getText().toString());
                if(validationResult.equals("ok")){
                    viewModel.createPersonalActivity();
                    activity.closeCreateActivityFragment();
                }else {
                    makeToast(validationResult);
                }
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.removeCreatePersonalActivityFragment();
                activity.closeCreateActivityFragment();
            }
        });

        startTimeLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addTimePickerFragment(0);
            }
        });

        endTimeLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addTimePickerFragment(1);
            }
        });

        repeatLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        remindLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void setCreateActivityTimeOnChangeObservers() {
        viewModel.getmPAStartTime().observe(activity, new Observer<LocalDateTime>() {
            @Override
            public void onChanged(LocalDateTime localDateTime) {
                int hour = localDateTime.getHour();
                int minute = localDateTime.getMinute();
                startTimeTextView.setText(hour+":"+minute);
            }
        });
        viewModel.getmPAEndTime().observe(activity, new Observer<LocalDateTime>() {
            @Override
            public void onChanged(LocalDateTime localDateTime) {
                int hour = localDateTime.getHour();
                int minute = localDateTime.getMinute();
                endTimeTextView.setText(hour+":"+minute);
            }
        });
    }

    private void setCreateActivityResultOnChangeObservers(){
        viewModel.getCreatePAMessage().observe(activity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                switch(s) {
                    case "default":
                        break;
                    case "activity created":
                        makeToast("Activity created successfully");
                        viewModel.setCreatePAMessage("default");
                        activity.closeCreateActivityFragment();
                        break;
                    default:
                        makeToast(s);
                }
            }
        });
    }

    private void makeToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}