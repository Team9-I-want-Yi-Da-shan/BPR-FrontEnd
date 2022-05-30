package com.example.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.home.R;
import com.example.home.viewModel.SignUpViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText usernameText;
    private TextInputEditText emailText;
    private TextInputEditText passwordText;
    private TextInputEditText passwordConfirmText;
    private Button signUpButton;
    private ImageView backToLogin;
    SignUpViewModel signUpVM;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUpVM = new ViewModelProvider(this).get(SignUpViewModel.class);

        usernameText = findViewById(R.id.SignUp_NameEditTextField);
        emailText = findViewById(R.id.SignUp_EmailEditTextField);
        passwordText = findViewById(R.id.SignUp_PasswordTextEditField);
        passwordConfirmText = findViewById(R.id.SignUp_confirmPasswordEditTextField);
        signUpButton = findViewById(R.id.SignUpButton);
        backToLogin = findViewById(R.id.backToLogin);

        setOnClickListener();
    }




    private void setOnClickListener(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                String passwordConfirm = passwordConfirmText .getText().toString();
                String result = signUpVM.validateAndSignUp(username,email,password,passwordConfirm);
                if(!result.equals("ok")){
                    makeToast(result);
                }
            }
        });


        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signUpVM.getisAccountCreated().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                usernameText.setText("");
                emailText.setText("");
                passwordText.setText("");
                passwordConfirmText.setText("");
                if (aBoolean == true) {
                    makeToast("Account was created!");
                    finish();
                } else {
                    makeToast("Something went wrong.");
                }
            }
        });
    }

    private void startLoginActivity(){
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
