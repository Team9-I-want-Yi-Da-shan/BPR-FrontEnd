package com.example.home.view;

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
import android.widget.TimePicker;

import com.example.home.R;
import com.example.home.tool.Logger;
import com.example.home.viewModel.ActivityViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimePickerFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    private int activityAndTimeType;

    ActivityViewModel viewModel;

    ImageButton backButton;
    TextView titleTextView;
    TimePicker timePicker;

    public TimePickerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param activityAndTimeType Parameter 1.
     *                            To check which kind of activity is going to be created
     *                            To check weather start time or end time is going to be set
     *                            0 for personal activity start time
     *                            1 for personal activity end time
     *                            2 for family activity start time
     *                            3 for family activity end time
     * @return A new instance of fragment TimePickerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimePickerFragment newInstance(int activityAndTimeType) {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, activityAndTimeType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            activityAndTimeType = getArguments().getInt(ARG_PARAM1);
        }
        viewModel = new ViewModelProvider(getActivity()).get(ActivityViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backButton = getView().findViewById(R.id.TimePickerFragment_BackButton);
        titleTextView = getView().findViewById(R.id.TimePickerFragment_Title);
        timePicker  = getView().findViewById(R.id.TimePickerFragment_TimePicker);
        timePicker.setIs24HourView(true);

        setTitle();
        setOnClickListeners();
    }

    private void setTitle() {
        switch (activityAndTimeType){
            case 0:
            case 2:
                titleTextView.setText("Start Time");
                break;
            case 1:
            case 3:
                titleTextView.setText("End Time");
        }
    }


    private void setOnClickListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                LocalDate localDate = viewModel.getDateSelected();
                LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(hour,minute));
                ActivityActivity activity = (ActivityActivity)getActivity();

                switch (activityAndTimeType){
                    case 0:
                        viewModel.setmPAStartTime(localDateTime);
                        Logger.debug("stq","time picker time:"+hour+":"+minute);
                        activity.removeCreatePersonalActivityFragment();
                        break;
                    case 1:
                        viewModel.setmPAEndTime(localDateTime);
                        activity.removeCreatePersonalActivityFragment();
                        break;
//                    case 2:
//                        viewModel.getmFAToCreateMutableLivedata().getValue().setStartTime(localDateTime);
//                        break;
//                    case 3:
//                        viewModel.getmFAToCreateMutableLivedata().getValue().setEndTime(localDateTime);
//                        break;
                }


            }
        });
    }
}