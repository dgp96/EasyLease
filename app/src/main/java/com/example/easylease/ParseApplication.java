package com.example.easylease;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models
        ParseObject.registerSubclass(User.class);


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Y1Fx3OJMkIxPVms92hTCEo1RL5ukp4zcjrLgLoSL")
                .clientKey("s1wyhxNI7nFROf7ZKyDBYMVSiMtjBdV5bZFPw5ed")
                .server("https://parseapi.back4app.com").build());
    }
}
