package com.example.home.view;

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

import com.example.home.R;
import com.example.home.model.PersonalActivity;
import com.example.home.viewModel.ActivityViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class PersonalActivityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    ActivityViewModel viewModel;

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

        personalActivityRecyclerView = getView().findViewById(R.id.PersonalActivity_RecyclerView);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        personalActivityRecyclerView.hasFixedSize();
        personalActivityRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<PersonalActivity> activities = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();

        activities.add(new PersonalActivity("Bulbasaur", "description", time,time,0,0));
        activities.add(new PersonalActivity("Ivysaur", "description", time,time,0,0));
        activities.add(new PersonalActivity("Venusaur", "description", time,time,0,0));
        activities.add(new PersonalActivity("Charmander", "description", time,time,0,0));
        activities.add(new PersonalActivity("Charmeleon", "description", time,time,0,0));
        activities.add(new PersonalActivity("Charizard", "description", time,time,0,0));
        activities.add(new PersonalActivity("Squirtle", "description", time,time,0,0));
        activities.add(new PersonalActivity("Wartortle", "description", time,time,0,0));
        activities.add(new PersonalActivity("Blastoise", "description", time,time,0,0));
        activities.add(new PersonalActivity("Caterpie", "description", time,time,0,0));
        activities.add(new PersonalActivity("Metapod", "description", time,time,0,0));
        activities.add(new PersonalActivity("Butterfree", "description", time,time,0,0));
        personalActivityAdapter = new PersonalActivityAdapter(activities);
        personalActivityRecyclerView.setAdapter(personalActivityAdapter);
    }



}