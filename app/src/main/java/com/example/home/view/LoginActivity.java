package com.example.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.example.home.R;
import com.example.home.viewModel.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity {

    private boolean isLoading;
    LoginViewModel loginVM;

    private TextInputEditText usernameField;
    private TextInputEditText passwordField;
    private TextView signUpText;
    private LottieAnimationView loginButton;
    private void findViews(){
        usernameField = findViewById(R.id.Login_UsernameTextField);
        passwordField = findViewById(R.id.Login_PasswordTextField);
        signUpText = findViewById(R.id.Login_SignUpTextView);
        loginButton = findViewById(R.id.Login_Button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isLoading = false;
        findViews();
        setClickListeners();

        loginVM = new ViewModelProvider(this).get(LoginViewModel.class);

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
//        loginButton.startAnimation(animation);


//        home.addAnimatorListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });

    }



    private void setClickListeners(){
        Intent mainIntent = new Intent(this, MainActivity.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(isLoading = true) {
//                    Toast.makeText(LoginActivity.this, "Login in process", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                isLoading = true;
                loginButton.setAnimation(R.raw.button_loading);
                loginButton.playAnimation();

                startActivity(mainIntent);
//                String username = usernameField.toString();
//                String password = passwordField.toString();
//                loginVM.login(username,password);
            }
        });

        Intent signUpIntent= new Intent(this, SignUpActivity.class);
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signUpIntent);
            }
        });

    }






}
