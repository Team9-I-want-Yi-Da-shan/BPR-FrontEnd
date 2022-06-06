package com.example.home.view.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.example.home.R;
import com.example.home.model.User;
import com.example.home.view.MainActivity;
import com.example.home.viewModel.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity {

    LoginViewModel loginVM;
    private boolean isLoading;

    private TextInputEditText emailField;
    private TextInputEditText passwordField;
    private TextView signUpText;
    private LottieAnimationView loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isLoading = false;
        emailField = findViewById(R.id.Login_EmailTextField);
        passwordField = findViewById(R.id.Login_PasswordTextField);
        signUpText = findViewById(R.id.Login_SignUpTextView);
        loginButton = findViewById(R.id.Login_Button);

        loginVM = new ViewModelProvider(this).get(LoginViewModel.class);

        setOnClickListeners();
        setEditTextFocusListeners();
        setLoginResultOnChangeObservers();
        setObservers();


    }

    private void setObservers() {
        loginVM.getIsLoggedIn().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean.booleanValue() == true) {
                    saveUserInPreference();
                    //startMainActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void setOnClickListeners(){
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignUpActivity();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setAnimation(R.raw.button_loading);
                loginButton.playAnimation();

                String result = loginVM.validate();
                if(result.equals("ok")){
                    loginVM.login();
                }else {
                    makeToast(result);
                };
                }
        });

    }

    public void startSignUpActivity(){
        Intent intent= new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void startMainActivity(){
        Intent intent= new Intent(this, MainActivity.class);
        intent.putExtra("user",loginVM.getResponseUser());
        startActivity(intent);
        finish();
    }

    private void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void saveUserInPreference(){
        SharedPreferences prefs = getSharedPreferences("UserPreference", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        User user = loginVM.getResponseUser();
        editor.putInt("userId", user.getUserId());
        editor.putString("userName", user.getName());
        editor.putString("userEmail", user.getEmail());
        editor.putInt("familyId",user.getFamilyId());
        editor.apply();
    }

    private void setLoginResultOnChangeObservers(){
        loginVM.getCreateLoginMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                switch(s) {
                    case "default":
                        break;
                    case "Invalid login details":
                        makeToast("Invalid login details");
                        break;
                    case "Successfully logged in":
                        startMainActivity();
                        makeToast("Successfully logged in");
                        loginVM.setCreateLoginMessage("default");
                        break;

                    default:
                        makeToast(s);
                }
            }
        });
    }

    private void setEditTextFocusListeners() {
        emailField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    loginVM.setEmail(emailField.getText().toString());
                }
            }
        });

        passwordField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    loginVM.setPassword(passwordField.getText().toString());
                }
            }
        });
    }

}
