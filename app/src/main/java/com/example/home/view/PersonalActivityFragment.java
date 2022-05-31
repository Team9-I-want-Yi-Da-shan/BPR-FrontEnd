package com.example.home.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.home.R;
import com.example.home.model.dataTransferObject.PersonalActivityDTO;
import com.example.home.viewModel.ActivityViewModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class PersonalActivityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    ActivityActivity activity;
    ActivityViewModel viewModel;

    TextView textView;
    LottieAnimationView animationView;
    RecyclerView personalActivityRecyclerView;
    PersonalActivityAdapter personalActivityAdapter;

    public PersonalActivityFragment() {
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
    public static PersonalActivityFragment newInstance() {
        PersonalActivityFragment fragment = new PersonalActivityFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        activity = (ActivityActivity)getActivity();
        viewModel = new ViewModelProvider(getActivity()).get(ActivityViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.PersonalActivity_Text);
        animationView = view.findViewById(R.id.PersonalActivity_LoadingAnimation);
        personalActivityRecyclerView = view.findViewById(R.id.PersonalActivity_RecyclerView);
        animationView.setVisibility(View.GONE);

        String message = viewModel.getGetPAMessage().getValue();
        if(message.equals("wait")){
            animationView.setVisibility(View.VISIBLE);
            animationView.playAnimation();
        }else if(message.equals("done")){
            ArrayList<PersonalActivityDTO> personalActivityDTOS = viewModel.getPersonalActivities().getValue();
            if(personalActivityDTOS.size()>0){
                personalActivityAdapter.setPersonalActivities(personalActivityDTOS);
            }else {
                textView.setText("No activity");
            }
        }

        setUpRecyclerView();
        setUpListeners();
    }

    private void setUpListeners() {
        viewModel.getGetPAMessage().observe(activity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                switch (s){
                    case "default":
                        break;
                    case "wait":
                        animationView.setVisibility(View.VISIBLE);
                        animationView.playAnimation();
                        break;
                    case "done":
                        ArrayList<PersonalActivityDTO> personalActivityDTOS = viewModel.getPersonalActivities().getValue();
                        if(personalActivityDTOS.size()>0){
                            personalActivityAdapter.setPersonalActivities(personalActivityDTOS);
                        }else {
                            textView.setText("No activity");
                        }
                }
            }
        });
        viewModel.getPersonalActivities().observe(activity, new Observer<ArrayList<PersonalActivityDTO>>() {
            @Override
            public void onChanged(ArrayList<PersonalActivityDTO> personalActivities) {

                personalActivityAdapter.setPersonalActivities(personalActivities);
            }
        });
        viewModel.getDateSelected().observe(activity, new Observer<LocalDate>() {
            @Override
            public void onChanged(LocalDate localDate) {
                if(viewModel.getBottomNavigationSelectedItem().getValue()==0){
                    viewModel.sendGetPersonalActivitiesRequest();
                }
            }
        });
        viewModel.getBottomNavigationSelectedItem().observe(activity, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer == 0){
                    viewModel.sendGetPersonalActivitiesRequest();
                }
            }
        });
    }

    private void setUpRecyclerView() {
        personalActivityRecyclerView.hasFixedSize();
        personalActivityRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        personalActivityAdapter = new PersonalActivityAdapter();
        personalActivityRecyclerView.setAdapter(personalActivityAdapter);
    }



}