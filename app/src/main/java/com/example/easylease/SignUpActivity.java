package com.example.easylease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";
    private EditText etUsername;
    private EditText etPassword;
    private EditText etEmail;
    private EditText etNameSignUp;
    private EditText etAptNo;
    private Button btnSignUp;
    ParseObject aptObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etUsername = findViewById(R.id.etUsernameSignUp);
        etPassword = findViewById(R.id.etPasswordSignUp);
        etEmail = findViewById(R.id.etEmail);
        etNameSignUp = findViewById(R.id.etNameSignUp);
        etAptNo = findViewById(R.id.etAptNo);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onCLick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                String fname = etNameSignUp.getText().toString();
                String apt_no = etAptNo.getText().toString();
                signUpUser(username, password, email, fname, apt_no);
            }
        });
    }

    private void signUpUser(String username, String password, String email, String fname, String apt_no) {
        Log.i(TAG, "Attempting to SignUp user " + username);

        if(username.isEmpty()){
            Toast.makeText(SignUpActivity.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(SignUpActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.isEmpty()){
            Toast.makeText(SignUpActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(fname.isEmpty()){
            Toast.makeText(SignUpActivity.this, "Full Name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(apt_no.isEmpty()){
            Toast.makeText(SignUpActivity.this, "Apartment No. cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.put("full_name", fname);

        aptObject = Apartment.setApartmentObject(Integer.parseInt(apt_no));

        if(aptObject==null){
            Toast.makeText(SignUpActivity.this, "Apartment No. doesn't exist", Toast.LENGTH_SHORT).show();
            return;
        }


        user.put("Apartment_no",aptObject);
        // Set custom properties
        //user.put("phone", "650-253-0000");
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(SignUpActivity.this, "Signed up successfully", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Sign up successful");
                    goLoginActivity();

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(SignUpActivity.this, "Sign up unsuccessful", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Issue with signup: " + e);
                }
            }
        });
    }

    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    /*private void setApartmentObject(int apt_no){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Apartment");
        //ParseObject aptObject;
        query.whereEqualTo("Number", apt_no);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    aptObject = object;
                } else {
                    // Something is wrong
                    Log.i(TAG, "Error setting object " + e);
                    aptObject=null;
                }
            }
        });

        //return aptObject;
    }*/




}