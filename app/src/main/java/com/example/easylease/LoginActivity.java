package com.example.easylease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUpPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*if (ParseUser.getCurrentUser() != null){
            goMainActivity();
        }*/

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUpPage = findViewById(R.id.btnSignUpPage);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onCLick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onCLick Signup button");
                goSignUpActivity();
            }
        });


    }

    private void loginUser(String username, String password){
        Log.i(TAG, "Attempting to login user " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    //TODO: better error handling

                    Toast.makeText(LoginActivity.this, "Issue with login!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Issue with login", e);
                    return;
                }
                // TODO: navigate to the main activity if the user has signed in properly
                goMainActivity();
                //Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Log.i(TAG, String.format("loginUser: login success with %s", username));
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goSignUpActivity() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
        finish();
    }
}