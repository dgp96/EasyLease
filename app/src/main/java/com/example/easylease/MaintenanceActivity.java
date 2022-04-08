package com.example.easylease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MaintenanceActivity extends AppCompatActivity {

    public static final String TAG = "MaintenanceActivity";
    private EditText name,phone, email,category, permission, priority,description;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        name = findViewById(R.id.entry1);
        phone = findViewById(R.id.entry2);
        email = findViewById(R.id.entry3);
        category = findViewById(R.id.entry4);
        permission = findViewById(R.id.entry5);
        priority = findViewById(R.id.entry6);
        description = findViewById(R.id.entry7);
        submit = findViewById(R.id.submit_request);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onCLick login button");
                String fname = name.getText().toString();
                String phone_no = phone.getText().toString();
                String email_id = email.getText().toString();
                String cat = category.getText().toString();
                String perm = permission.getText().toString();
                String prior = priority.getText().toString();
                String desc = description.getText().toString();

                raiseRequest(fname,phone_no,email_id,cat,perm,prior,desc);
            }
        });
    }

    private void raiseRequest(String fname, String phone_no, String email_id, String cat, String perm, String prior, String desc) {
        ParseObject entity = new ParseObject("Requests");
        ParseUser user = ParseUser.getCurrentUser();

        entity.put("name", fname);
        entity.put("phone", phone_no);
        entity.put("email", email_id);
        entity.put("category", cat);
        entity.put("permission", perm);
        entity.put("priority", prior);
        entity.put("description", desc);
        entity.put("user", user);
        entity.put("apartment", user.get("Apartment_no"));

        // Saves the new object.
        // Notice that the SaveCallback is totally optional!
        /*entity.saveInBackground(e -> {
            if (e==null){
                //Save was done
            }else{
                //Something went wrong
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

        entity.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null){
                    //Save was done
                    Toast.makeText(getApplicationContext(),"Request submitted!",Toast.LENGTH_SHORT).show();
                    clearFields();
                }else{
                    //Something went wrong
                    Toast.makeText(getApplicationContext(),"Couldn't submit request, Try again",Toast.LENGTH_SHORT).show();
                }
            }
        } );
    }

    private void clearFields() {
        name.setText("");
        phone.setText("");
        email.setText("");
        category.setText("");
        permission.setText("");
        priority.setText("");
        description.setText("");

    }
}