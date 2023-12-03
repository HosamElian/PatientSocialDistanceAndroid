package com.example.patientsocialdistance.utilities;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class Constants {
    public static  final String USERNAME= "USERNAME";
    public static  final String UserImage= "UserImage";

    public static String getUrl(String controllerName) {
        return String.format("https://192.168.1.4:7204/api/%s/", controllerName);
    }
    public static String getCurrentUsername(Context context) {
         SharedPreferences prefEditor = getDefaultSharedPreferences(context);
       String x = prefEditor.getString(USERNAME, null);
        Log.d("username-x", "getCurrentUsername: " + x);
        return x;
    }
    public static String getCurrentUserImage(Context context) {
         SharedPreferences prefsEditor = getDefaultSharedPreferences(context);
       return prefsEditor.getString(UserImage, null);
    }

    public static void saveUserName(Context context, String username){
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor prefsEditor = getDefaultSharedPreferences(context).edit();
        Log.d("username-username", "getCurrentUsername: " + username);

        prefsEditor.putString(USERNAME, username).apply();
    }
    public static void saveUserImage(Context context, String image){
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor prefsEditor = getDefaultSharedPreferences(context).edit();
        prefsEditor.putString(UserImage, image).apply();
    }


}
