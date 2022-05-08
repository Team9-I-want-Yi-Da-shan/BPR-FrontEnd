package com.example.home.View;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.example.home.R;
import com.example.home.ViewModel.LoginViewModel;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    private TextInputLayout usernameField;
    private TextInputLayout passwordField;
    private LottieAnimationView loginButton;

    private boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        usernameField = findViewById(R.id.Login_UsernameTextField);
        passwordField = findViewById(R.id.Login_PasswordTextField);
        loginButton = findViewById(R.id.Login_Button);
        isLoading = false;

        LoginViewModel loginVM = new ViewModelProvider(this).get(LoginViewModel.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLoading = true) return;
                isLoading = true;
                loginButton.setAnimation(R.raw.button_loading);
                loginButton.playAnimation();

                String username = usernameField.toString();
                String password = passwordField.toString();
                loginVM.login(username,password);
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
//                startActivity(intent);
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




}
