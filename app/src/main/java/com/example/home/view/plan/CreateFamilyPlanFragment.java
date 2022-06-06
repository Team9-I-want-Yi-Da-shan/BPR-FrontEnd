package com.example.home.view.plan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.home.R;
import com.example.home.viewModel.PlanViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class CreateFamilyPlanFragment extends Fragment {

    PlanActivity parentActivity;
    PlanViewModel viewModel;

    ImageButton closeButton;
    ImageButton createButton;

    TextInputEditText familyPlanTitleEditText;
    TextInputEditText familyPlanDescriptionEditText;
    TextInputEditText familyPlanCommentEditText;


    public static CreateFamilyPlanFragment newInstance() {
        CreateFamilyPlanFragment fragment = new CreateFamilyPlanFragment();
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
        return inflater.inflate(R.layout.fragment_create_family_plan, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        closeButton = getView().findViewById(R.id.CreateFamilyPlan_CloseCreatePlanCard);
        createButton = getView().findViewById(R.id.CreateFamilyPlan_CreatePlanButton);
        familyPlanTitleEditText = getView().findViewById(R.id.CreateFamilyPlan_TitleTextField);
        familyPlanDescriptionEditText = getView().findViewById(R.id.CreateFamilyPlan_DescriptionTextField);
        familyPlanCommentEditText = getView().findViewById(R.id.CreateFamilyPlan_CommentTextField);


        setOnClickListeners();
        setEditTextFocusListeners();
        setCreatePlanResultOnChangeObservers();
    }




    private void setOnClickListeners() {

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validationResult = viewModel.validateFamilyPlan();
                if(validationResult.equals("ok")){
                    viewModel.addFamilyPlan();
                   // parentActivity.closeCreatePlanFragment();
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


    private void setCreatePlanResultOnChangeObservers() {

        viewModel.getCreateFPMessage().observe(parentActivity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                switch(s) {
                    case "default":
                        break;
                    case "Successfully added":
                        makeToast("Plan created successfully");
                        viewModel.setCreateFPMessage("default");
                        parentActivity.closeCreatePlanFragment();
                        break;
                    default:
                        makeToast(s);
                }
            }
        });

    }


    private void setEditTextFocusListeners() {

        familyPlanTitleEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    viewModel.setmFPTitle(familyPlanTitleEditText.getText().toString());
                }
            }
        });

        familyPlanDescriptionEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    viewModel.setmFPDescription(familyPlanDescriptionEditText.getText().toString());
                }
            }
        });

        familyPlanCommentEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    viewModel.setmFPComment(familyPlanCommentEditText.getText().toString());
                }
            }
        });
    }


    private void makeToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


}
