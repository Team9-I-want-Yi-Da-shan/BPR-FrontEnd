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
import com.example.home.model.FamilyActivity;
import com.example.home.viewModel.ActivityViewModel;

import java.util.ArrayList;

public class FamilyActivityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    ActivityViewModel viewModel;

    RecyclerView familyActivityRecyclerView;
    FamilyActivityAdapter familyActivityAdapter;

    public FamilyActivityFragment() {
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
    public static FamilyActivityFragment newInstance() {
        FamilyActivityFragment fragment = new FamilyActivityFragment();
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
        return inflater.inflate(R.layout.fragment_family_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        familyActivityRecyclerView = getView().findViewById(R.id.FamilyActivity_RecyclerView);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        familyActivityRecyclerView.hasFixedSize();
        familyActivityRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<FamilyActivity> activities = new ArrayList<>();
        String time = "17:00";

        activities.add(new FamilyActivity("chifan", "description", time,time,"null","null"));
        activities.add(new FamilyActivity("shuijiao", "description", time,time,"null","null"));
        activities.add(new FamilyActivity("dadodo", "description", time,time,"null","null"));
        activities.add(new FamilyActivity("zuoai", "description", time,time,"null","null"));
        familyActivityAdapter = new FamilyActivityAdapter(activities);
        familyActivityRecyclerView.setAdapter(familyActivityAdapter);
    }



}