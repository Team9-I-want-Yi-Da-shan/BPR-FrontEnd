package com.example.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.home.R;
import com.example.home.viewModel.ActivityViewModel;
import com.example.home.viewModel.PlanViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class CreatePersonalPlanFragment extends Fragment {

    PlanActivity parentActivity;
    PlanViewModel viewModel;

    ImageButton closeButton;
    ImageButton createButton;

    TextInputEditText titleEditText;
    TextInputEditText descriptionEditText;
    TextInputEditText commentEditText;


    public static CreatePersonalPlanFragment newInstance() {
        CreatePersonalPlanFragment fragment = new CreatePersonalPlanFragment();
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

        viewModel = new ViewModelProvider(getActivity()).get(PlanViewModel.class);
        parentActivity = (PlanActivity) getActivity();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_personal_plan, container, false);
    }



    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        closeButton = getView().findViewById(R.id.CreatePersonalPlan_CloseCreatePlanCard);
        createButton = getView().findViewById(R.id.CreatePersonalPlan_CreatePlanButton);
        titleEditText = getView().findViewById(R.id.CreatePersonalPlan_TitleTextField);
        descriptionEditText = getView().findViewById(R.id.CreatePersonalPlan_DescriptionTextField);
        commentEditText = getView().findViewById(R.id.CreatePersonalPlan_CommentTextField);


        setOnClickListeners();
        setEditTextFocusListeners();
    }



    private void setOnClickListeners() {

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validationResult = viewModel.validate();
                if(validationResult.equals("ok")){
                    viewModel.createActivity();
                    parentActivity.closeCreatePlanFragment();
                }else {
                    makeToast(validationResult);
                }
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.closeCreatePlanFragment();
            }
        });
    }

    private void setEditTextFocusListeners() {
        titleEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    viewModel.setmPPTitle(titleEditText.getText().toString());
                }
            }
        });

        descriptionEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    viewModel.setmPPDescription(descriptionEditText.getText().toString());
                }
            }
        });

        commentEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    viewModel.setmPPComment(commentEditText.getText().toString());
                }
            }
        });
    }


    private void makeToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}