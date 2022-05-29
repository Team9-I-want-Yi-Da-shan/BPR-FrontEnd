package com.example.home.view;

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
import com.example.home.model.FamilyActivity;
import com.example.home.model.FamilyPlan;
import com.example.home.viewModel.ActivityViewModel;
import com.example.home.viewModel.PlanViewModel;

import java.util.ArrayList;

public class FamilyPlanFragment extends Fragment {

    PlanViewModel viewModel;

    RecyclerView familyPlanRecyclerView;
    FamilyPlanAdapter familyPlanAdapter;

    public FamilyPlanFragment() {
        // Required empty public constructor
    }

    public static FamilyPlanFragment newInstance() {
        FamilyPlanFragment fragment = new FamilyPlanFragment();
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
        return inflater.inflate(R.layout.fragment_family_plan, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        familyPlanRecyclerView = getView().findViewById(R.id.FamilyPlan_RecyclerView);
        setUpRecyclerView();
    }


    private void setUpRecyclerView() {

        familyPlanRecyclerView.hasFixedSize();
        familyPlanRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<FamilyPlan> plans = new ArrayList<>();
        String time = "17:00";

        plans.add(new FamilyPlan("chifan", "description", "null"));
        plans.add(new FamilyPlan("shuijiao", "description", "null"));
        plans.add(new FamilyPlan("dadodo", "description", "null"));
        plans.add(new FamilyPlan("zuoai", "description", "null"));
        familyPlanAdapter = new FamilyPlanAdapter(plans);
        familyPlanRecyclerView.setAdapter(familyPlanAdapter);
    }


}
