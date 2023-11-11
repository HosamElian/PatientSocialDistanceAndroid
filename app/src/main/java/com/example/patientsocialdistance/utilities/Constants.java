package com.example.patientsocialdistance.utilities;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Constants {
    public static  final String USERNAME= "USERNAME";

    public static String getUrl(String controllerName) {
        return String.format("https://192.168.1.3:7204/api/%s/", controllerName);
    }
    public static String getCurrentUsername(Context context) {
         SharedPreferences prefsEditor = getDefaultSharedPreferences(context);
       return prefsEditor.getString(USERNAME, null);
    }

    public static void saveUserName(Context context, String username){
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor prefsEditor = getDefaultSharedPreferences(context).edit();
        prefsEditor.putString(USERNAME, username).apply();
    }

}
