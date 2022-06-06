package com.example.home.view.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.home.R;
import com.example.home.model.dataTransferObject.PersonalActivityDTO;
import com.example.home.tool.Logger;
import com.example.home.viewModel.ActivityViewModel;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalActivityDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalActivityDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private PersonalActivityDTO personalActivity;
//    private String mParam2;

    ActivityActivity activity;
    ActivityViewModel viewModel;

    ImageButton closeButton;
    ImageButton editButton;
    TextView titleText;
    TextView timeText;
    TextView descriptionText;
    TextView repeatText;
    TextView remindText;
    TextView alarmText;
    MaterialButton doneButton;
    MaterialButton deleteButton;


    public PersonalActivityDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PersonalActivityDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalActivityDetailFragment newInstance(PersonalActivityDTO personalActivityDTO) {
        PersonalActivityDetailFragment fragment = new PersonalActivityDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, personalActivityDTO);
        Logger.debug("personalActivityDetail",personalActivityDTO.toString());
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            personalActivity = (PersonalActivityDTO) getArguments().getSerializable(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        viewModel = new ViewModelProvider(getActivity()).get(ActivityViewModel.class);
        activity = (ActivityActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_activity_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        closeButton = view.findViewById(R.id.PersonalActivityDetail_CloseCard);
        editButton = view.findViewById(R.id.PersonalActivityDetail_EditActivity);
        titleText = view.findViewById(R.id.PersonalActivityDetail_TitleText);
        timeText = view.findViewById(R.id.PersonalActivityDetail_TimeText);
        descriptionText = view.findViewById(R.id.PersonalActivityDetail_DescriptionText);
        repeatText = view.findViewById(R.id.PersonalActivityDetail_RepeatText);
        remindText = view.findViewById(R.id.PersonalActivityDetail_ReminderText);
        alarmText = view.findViewById(R.id.PersonalActivityDetail_AlarmText);
        doneButton = view.findViewById(R.id.PersonalActivityDetail_DoneButton);
        deleteButton = view.findViewById(R.id.PersonalActivityDetail_DeleteButton);
        titleText.setText(personalActivity.getTitle());
        String time = personalActivity.getStartTimeString();
        if(personalActivity.getFinish_at()!=0){
            time +=  " - " + personalActivity.getEndTimeString();
        }
        timeText.setText(time);
        descriptionText.setText(personalActivity.getDescription());
        if(personalActivity.getIs_repeat()==-1){
            repeatText.setText("");
        }else {
            repeatText.setText("Repeat every "+ personalActivity.getRepeatInDay() + " day");
        }
        if(personalActivity.getReminder()==-1){
            remindText.setText("");
        }else {
            remindText.setText("Remind "+personalActivity.getReminder()+" minutes before");
        }
        if(personalActivity.getIsAlarm()==0){
            alarmText.setText("");
        }else {
            alarmText.setText("Alarm: ON");
        }

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.removePersonalActivityDetailFragment();
                activity.closeCentralFrameLayout();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}