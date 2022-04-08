package com.example.easylease;

import androidx.appcompat.app.AppCompatActivity;
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
import com.parse.SaveCallback;

public class ProfileActivity extends AppCompatActivity {

    public static final String TAG = "ProfileActivity";
    private EditText etName;
    private EditText etEmail_profile;
    private EditText etApartment;
    private Button btnUpdate;
    ParseObject aptObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        etName = findViewById(R.id.etName);
        etEmail_profile = findViewById(R.id.etEmail_profile);
        etApartment = findViewById(R.id.etApartment);

        loadDetails();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Updating profile...");

                updateProfile(etName.getText().toString(),etEmail_profile.getText().toString(),etApartment.getText().toString());
            }
        });
    }

    private void updateProfile(String name, String email, String apt_no) {
        ParseUser user = ParseUser.getCurrentUser();
        user.put("full_name",name);
        user.put("email",email);
        aptObject = Apartment.setApartmentObject(Integer.parseInt(apt_no));



        if(aptObject==null){
            Toast.makeText(getApplicationContext(), "Apartment No. doesn't exist", Toast.LENGTH_SHORT).show();
            return;
        }

        user.put("Apartment_no",aptObject);

        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(getApplicationContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                    loadDetails();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Couldn't update profile data, try again", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void loadDetails(){
        ParseUser user = ParseUser.getCurrentUser();
        /*User user = ParseUser.getCurrentUser();
        Apartment apt = new Apartment();*/
        etName.setText(user.get("full_name").toString());
        etEmail_profile.setText(user.getEmail().toString());
        ParseObject apt = (ParseObject) user.get("Apartment_no");
        //ParseObject apt = Apartment.setApartmentObject()
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Apartment");

        // The query will search for a ParseObject, given its objectId.
        // When the query finishes running, it will invoke the GetCallback
        // with either the object, or the exception thrown
        query.getInBackground(apt.getObjectId(), (object, e) -> {
            if (e == null) {
                //Object was successfully retrieved
                etApartment.setText(object.getString("Number"));
            } else {
                // something went wrong
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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