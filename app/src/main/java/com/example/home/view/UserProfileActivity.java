package com.example.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.home.R;
import com.example.home.viewModel.UserProfileViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;

public class UserProfileActivity extends AppCompatActivity {

    UserProfileViewModel viewModel;
    FragmentManager fragmentManager;

    UserProfileFragment userProfileFragment;

    CreateFamilyFragment createFamilyFragment;
    JoinFamilyFragment joinFamilyFragment;
    ManageFamilyFragment manageFamilyFragment;

    MaterialToolbar topBar;
    FrameLayout userProfileFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        viewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);

        fragmentManager = getSupportFragmentManager();
        userProfileFragment = UserProfileFragment.newInstance();

        createFamilyFragment = CreateFamilyFragment.newInstance();
        joinFamilyFragment = JoinFamilyFragment.newInstance();
        manageFamilyFragment = ManageFamilyFragment.newInstance();

        userProfileFrameLayout = findViewById(R.id.UserProfile_ProfileFrameLayout);
        topBar = findViewById(R.id.UserProfile_TopAppBar);
        topBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addUserProfileFragment();
    }

    public void addUserProfileFragment(){
        fragmentManager.beginTransaction()
                .add(R.id.UserProfile_ProfileFrameLayout, userProfileFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("addUserProfile")
                .commit();
    }


    public void addCreateFamilyFragment(){
        fragmentManager.beginTransaction()
                .add(R.id.UserProfile_SubFragmentFramLayout, createFamilyFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("addCreateFamily")
                .commit();
    }

    public void removeCreateFamilyFragment(){
        fragmentManager.beginTransaction()
                .remove(createFamilyFragment)
                .setReorderingAllowed(true)
                .addToBackStack("removeCreateFamily")
                .commit();
    }

    public void addJoinFamilyFragment() {
        fragmentManager.beginTransaction()
                .add(R.id.UserProfile_SubFragmentFramLayout, joinFamilyFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("addJoinFamily")
                .commit();
    }

    public void removeJoinFamilyFragment() {
        fragmentManager.beginTransaction()
                .remove(joinFamilyFragment)
                .setReorderingAllowed(true)
                .addToBackStack("removeJoinFamily")
                .commit();
    }

    public void addManageFamilyFragment() {
        fragmentManager.beginTransaction()
                .add(R.id.UserProfile_SubFragmentFramLayout, manageFamilyFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("addManageFamily")
                .commit();
    }

    public void removeManageFamilyFragment() {
        fragmentManager.beginTransaction()
                .remove(manageFamilyFragment)
                .setReorderingAllowed(true)
                .addToBackStack("removeManageFamily")
                .commit();
    }
}