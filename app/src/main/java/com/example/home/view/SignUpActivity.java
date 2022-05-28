package com.example.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.home.R;
import com.example.home.viewModel.SignUpViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText usernameText;
    private TextInputEditText passwordText;
    private TextInputEditText emailText;
    private TextInputEditText passwordConfirmText;
    private Button signUpButton;
    private ImageView backToLogin;
    SignUpViewModel signUpVM;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUpVM = new ViewModelProvider(this).get(SignUpViewModel.class);

        usernameText = findViewById(R.id.SignUp_NameEditTextField);
        passwordText = findViewById(R.id.SignUp_PasswordTextEditField);
        emailText = findViewById(R.id.SignUp_EmailEditTextField);
        passwordConfirmText = findViewById(R.id.SignUp_confirmPasswordEditTextField);
        signUpButton = findViewById(R.id.SignUpButton);
        backToLogin = findViewById(R.id.backToLogin);

        setOnClickListener();
        setEditTextListener();
    }




    private void setOnClickListener(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = signUpVM.validate();
                if(!result.equals("ok")){
                    makeToast(result);
                }
            }
        });

        Intent intent= new Intent(this, LoginActivity.class);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        signUpVM.getisAccountCreated().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                emailText.setText("");
                passwordText.setText("");
                passwordConfirmText.setText("");
                usernameText.setText("");
                if (aBoolean == true) {
                    Toast.makeText(getApplicationContext(), "Account was created!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setEditTextListener() {
        usernameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    signUpVM.setUsername(usernameText.getText().toString());
                }
            }
        });
        passwordText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    signUpVM.setPassword(passwordText.getText().toString());
                }
            }
        });
        emailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    signUpVM.setEmail(emailText.getText().toString());
                }
            }
        });
        passwordConfirmText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    signUpVM.setPasswordConfirm(passwordConfirmText.getText().toString());
                }
            }
        });
    }

    private void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
