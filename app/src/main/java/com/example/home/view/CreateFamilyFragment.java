package com.example.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.home.R;
import com.example.home.viewModel.UserProfileViewModel;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateFamilyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFamilyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    UserProfileActivity activity;
    UserProfileViewModel viewModel;

    TextInputEditText familyNameTextField;
    ImageButton closeButton;
    ImageButton createButton;

    public CreateFamilyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateFamilyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateFamilyFragment newInstance() {
        CreateFamilyFragment fragment = new CreateFamilyFragment();
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
        return inflater.inflate(R.layout.fragment_create_family, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        familyNameTextField = getView().findViewById(R.id.CreateFamily_FamilyNameTextField);
        closeButton = getView().findViewById(R.id.CreateFamily_CloseCard);
        createButton = getView().findViewById(R.id.CreateFamily_CreateFamilyButton);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.removeCreateFamilyFragment();
            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = viewModel.validateAndCreateFamily(familyNameTextField.getText().toString());
                if(!result.equals("ok")){
                    makeToast(result);
                }
            }
        });
    }

    private void makeToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}