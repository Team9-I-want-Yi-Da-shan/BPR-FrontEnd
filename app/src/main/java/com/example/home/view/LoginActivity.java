package com.example.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.example.home.R;
import com.example.home.viewModel.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    private boolean isLoading;
    private TextInputEditText usernameField;
    private TextInputEditText passwordField;
    private TextView signUpText;
    private LottieAnimationView loginButton;
    LoginViewModel loginVM;

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
//        setClickListeners();


       loginVM = new ViewModelProvider(this).get(LoginViewModel.class);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToSignUpView();
            }
        });

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
                String email = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                loginVM.login(email,password);
            }
        });

        loginVM.getisLoggedin().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if (aBoolean == true) {
                    Toast.makeText(getApplicationContext(), "Login successfully!", Toast.LENGTH_SHORT).show();
                    intentMainView();
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
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



//    private void setClickListeners(){
//        Intent intent = new Intent(this, MainActivity.class);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isLoading = true) {
//                    Toast.makeText(LoginActivity.this, "Login in process", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                isLoading = true;
//                loginButton.setAnimation(R.raw.button_loading);
//                loginButton.playAnimation();
//
//                startActivity(intent);
//
//                String username = usernameField.getText().toString();
//                String password = passwordField.getText().toString();
//
//                loginVM.login(username,password);
//            }
//        });
//
//    }

    public void intentToSignUpView(){
        Intent intent= new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void intentMainView(){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
