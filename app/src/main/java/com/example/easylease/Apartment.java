package com.example.easylease;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("Apartment")
public class Apartment extends ParseObject {
    public static final String TAG = "ApartmentActivity";
    public static final String occupancy = "Occupancy";
    public static final String room_no = "Number";
    static ParseObject aptObject;

    public int getOccupancy(){
        return Integer.parseInt(getString(occupancy));
    }

    public int getRoom_no(){
        return Integer.parseInt(getString(room_no));
    }

    public void setRoom_no(int room){
        put(room_no,room);
    }

    public void setOccupancy(int occ){
        put(occupancy,occ);
    }

    public static ParseObject setApartmentObject(int apt_no){

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

        return aptObject;

    }
}
