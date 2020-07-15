package com.rumahdev.smartacn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Tito on 12/15/2016.
 */
public class SessionManagement {
    SharedPreferences pref;
    int PRIVATE_MODE = 0;
    Context _context;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "sessionTNT";
    private static final String IS_LOGIN = "IsLoggedIn";
    // User name (make variable public to access from outside)
    public static final String USER_NAME = "username";
    public static final String USER_MAIL = "email";
    public static final String USER_TELP = "telp";
    public static final String USER_ID = "iduser";
    public static final String USER_STAT = "status";
    public static final String USER_IDCARD = "idcard";
    public static final String USER_ADDRESS = "address";
    public static final String USER_DISPLAYNAME = "displayname";

    public static final String USER_TES1 = "tes1";
    public static final String USER_TES2 = "tes2";
    public static final String USER_TES3 = "tes3";

    public static final String USER_ESAI1 = "esai1";
    public static final String USER_ESAI2 = "esai2";
    public static final String USER_ESAI3 = "esau3";
    public static final String USER_ESAI4 = "esai4";
    public static final String USER_ESAI5 = "esai5";

    public SessionManagement(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String nama, String tes1, String tes2, String tes3, String esai1, String esai2, String esai3, String esai4, String esai5){
// Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(USER_NAME, nama);
        editor.putString(USER_TES1, tes1);
        editor.putString(USER_TES2, tes2);
        editor.putString(USER_TES3, tes3);

        editor.putString(USER_ESAI1, esai1);
        editor.putString(USER_ESAI2, esai2);
        editor.putString(USER_ESAI3, esai3);
        editor.putString(USER_ESAI4, esai4);
        editor.putString(USER_ESAI5, esai5);

// commit changes
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(USER_NAME, pref.getString(USER_NAME, null));
        user.put(USER_TES1, pref.getString(USER_TES1, null));
        user.put(USER_TES2, pref.getString(USER_TES2, null));
        user.put(USER_TES3, pref.getString(USER_TES3, null));

        user.put(USER_ESAI1, pref.getString(USER_ESAI1, null));
        user.put(USER_ESAI2, pref.getString(USER_ESAI2, null));
        user.put(USER_ESAI3, pref.getString(USER_ESAI3, null));
        user.put(USER_ESAI4, pref.getString(USER_ESAI4, null));
        user.put(USER_ESAI5, pref.getString(USER_ESAI5, null));



// user email id

// return user
        return user;
    }

    public boolean checkLogin(){
// Check login status
        if(!this.isLoggedIn())
// user is not logged in redirect him to Login Activity
            //Intent i = new Intent(_context, Login.class);
// Closing all the Activities
            //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
// Add new Flag to start new Activity
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
// Staring Login Activity
            //_context.startActivity(i);
            return false;

        else
            return true;
    }


    public void logoutUser(){
// Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
// After logout redirect user to Loing Activity
        Intent i = new Intent(_context, MainActivity.class);

// Closing all the Activities
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}