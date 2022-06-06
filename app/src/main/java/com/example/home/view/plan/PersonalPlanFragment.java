package com.example.home.view.plan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.model.PersonalPlan;
import com.example.home.viewModel.PlanViewModel;

import java.util.ArrayList;

public class PersonalPlanFragment extends Fragment {


    PlanViewModel viewModel;

    RecyclerView personalPlanRecyclerView;
    PersonalPlanAdapter personalPlanAdapter;


    public PersonalPlanFragment() {
        // Required empty public constructor
    }

    public static PersonalPlanFragment newInstance() {
        PersonalPlanFragment fragment = new PersonalPlanFragment();
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
        viewModel = new ViewModelProvider(getActivity()).get(PlanViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        personalPlanRecyclerView = getView().findViewById(R.id.PersonalPlan_RecyclerView);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        personalPlanRecyclerView.hasFixedSize();
        personalPlanRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<PersonalPlan> plans = new ArrayList<>();

        plans.add(new PersonalPlan("Bulbasaur", "description", "Comment"));
        plans.add(new PersonalPlan("Ivysaur", "description","Comment") );
        plans.add(new PersonalPlan("Venusaur", "description", "Comment"));
        plans.add(new PersonalPlan("Charmander", "description", "Comment"));

        personalPlanAdapter = new PersonalPlanAdapter(plans);
        personalPlanRecyclerView.setAdapter(personalPlanAdapter);
    }


}
