package com.example.home.view.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.home.R;
import com.example.home.model.User;
import com.example.home.viewModel.UserProfileViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageFamilyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageFamilyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    UserProfileActivity activity;
    UserProfileViewModel viewModel;
    ManageFamilyAdapter manageFamilyAdapter;

    ImageButton closeButton;
    RecyclerView recyclerView;

    public ManageFamilyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ManageFamilyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageFamilyFragment newInstance() {
        ManageFamilyFragment fragment = new ManageFamilyFragment();
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
        activity = (UserProfileActivity) getActivity();
        viewModel = new ViewModelProvider(getActivity()).get(UserProfileViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_family, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.ManageFamily_RecyclerView);
        closeButton = getView().findViewById(R.id.ManageFamily_CloseCard);
        setUpRecyclerView();
        setOnClickListeners();
    }



    private void setUpRecyclerView() {
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //TODO 异步获取不到
        ArrayList<User> activities = viewModel.getFamilyMembers();
        manageFamilyAdapter = new ManageFamilyAdapter(activities);
        recyclerView.setAdapter(manageFamilyAdapter);
    }

    private void setOnClickListeners() {
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.removeManageFamilyFragment();
            }
        });
    }

}