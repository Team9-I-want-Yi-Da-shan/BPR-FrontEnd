package com.example.home.view.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.home.R;
import com.example.home.view.login.LoginActivity;
import com.example.home.viewModel.UserProfileViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    UserProfileActivity activity;
    UserProfileViewModel viewModel;

    LinearLayout createFamilyLayout;
    LinearLayout joinFamilyLayout;
    LinearLayout familyMembersLayout;
    LinearLayout LogoutLayout;
    LinearLayout ChangePasswordLayout;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment JoinFamilyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        activity = (UserProfileActivity) getActivity();
        viewModel = new ViewModelProvider(getActivity()).get(UserProfileViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createFamilyLayout = view.findViewById(R.id.Profile_CreateFamilyLayout);
        joinFamilyLayout = view.findViewById(R.id.Profile_JoinFamilyLayout);
        familyMembersLayout = view.findViewById(R.id.Profile_FamilyMemberLayout);
        LogoutLayout = view.findViewById(R.id.Profile_Logout);
        ChangePasswordLayout = view.findViewById(R.id.Profile_ChangePassword);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        createFamilyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.addCreateFamilyFragment();
            }
        });
        joinFamilyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.addJoinFamilyFragment();
            }
        });
        familyMembersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.addManageFamilyFragment();
            }
        });
        Intent loginActivity = new Intent(getContext(), LoginActivity.class);
        LogoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(loginActivity);
                activity.finish();
            }
        });
        ChangePasswordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}