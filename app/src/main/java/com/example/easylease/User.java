package com.example.easylease;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseObject {

    //public static final String KEY_DESCRIPTION = "description";
    //public static final String KEY_IMAGE = "image";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";

    public String getUsername(){
        return getString(KEY_USERNAME);
    }

    public void setUsername(String username){
        put(KEY_USERNAME, username);      //put associates key with description from Parser
    }

    public ParseFile getEmail(){
        return getParseFile(KEY_EMAIL);
    }

    //setter now
    public void setEmail(String email){
        put(KEY_EMAIL, email);
    }
}
