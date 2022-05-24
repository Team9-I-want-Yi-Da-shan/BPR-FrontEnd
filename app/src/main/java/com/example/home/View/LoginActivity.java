package com.example.home.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.example.home.R;
import com.example.home.ViewModel.LoginViewModel;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    private boolean isLoading;

    private TextInputLayout usernameField;
    private TextInputLayout passwordField;
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



        LoginViewModel loginVM = new ViewModelProvider(this).get(LoginViewModel.class);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToSignUpView();
            }
        });

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
        Intent intent = new Intent(this, MainActivity.class);
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

                startActivity(intent);


//                String username = usernameField.toString();
//                String password = passwordField.toString();
//                loginVM.login(username,password);
            }
        });
    }

    public void intentToSignUpView(){
        Intent intent= new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }





}
