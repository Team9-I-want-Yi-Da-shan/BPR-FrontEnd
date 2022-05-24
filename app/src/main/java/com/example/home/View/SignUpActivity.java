package com.example.home.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.home.R;
import com.example.home.ViewModel.SignUpViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText usernameText;
    private TextInputEditText passwordText;
    private TextInputEditText emailText;
    private TextInputEditText passwordConfirmText;
    private Button signUpButton;
    private ImageView backToLogin;

    private void findViews(){
        usernameText = findViewById(R.id.SignUp_NameEditTextField);
        passwordText = findViewById(R.id.SignUp_PasswordTextEditField);
        emailText = findViewById(R.id.SignUp_EmailEditTextField);
        passwordConfirmText = findViewById(R.id.SignUp_confirmPasswordEditTextField);
        signUpButton = findViewById(R.id.SignUpButton);
        backToLogin = findViewById(R.id.backToLogin);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        findViews();


        SignUpViewModel signUpVM = new ViewModelProvider(this).get(SignUpViewModel.class);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String passwordConfirm = passwordConfirmText.getText().toString();

                if (username.equals("")){
                    Toast.makeText(getApplicationContext(), "You do not insert username!", Toast.LENGTH_SHORT).show();
                }if (email.equals("")){
                    Toast.makeText(getApplicationContext(), "You do not insert email!", Toast.LENGTH_SHORT).show();
                }if (password.equals("")){
                    Toast.makeText(getApplicationContext(), "You do not insert password!", Toast.LENGTH_SHORT).show();
                }if (passwordConfirm.equals("")){
                    Toast.makeText(getApplicationContext(), "You do not insert confirm!", Toast.LENGTH_SHORT).show();
                }if (!password.equals(passwordConfirm)){
                    Toast.makeText(getApplicationContext(), "Different password!", Toast.LENGTH_SHORT).show();
                }
                else {
                    signUpVM.signUp(username, email, password);
                }
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToLoginView();
            }
        });
    }

    public void intentToLoginView(){
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

