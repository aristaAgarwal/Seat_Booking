package com.dishIT.seatbooking.constants;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SuppressLint("ApplySharedPref")
public class AppPreferences {


    private static final String TAG = "AppPreferences";

    private static String uniqueID = null;
    private static String token = null;
    private final SharedPreferences sharedPrefs;


    public AppPreferences(Context context) {
        this.sharedPrefs = context.getSharedPreferences(PreferenceNames.APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPrefs.edit();
        prefsEditor.commit();
    }

    public void setToken(String token){
        SharedPreferences.Editor editor  =  sharedPrefs.edit();
        editor.putString(PreferenceNames.TOKEN,  token);
        editor.commit();
    }

    public String getToken() {
        try {
            token = sharedPrefs.getString(PreferenceNames.TOKEN, "");
        } catch (Exception ex) {
//                DLogger.Companion.e(TAG, ex);
        }
        return token;
    }


    /**
     * Set chosen email-id used while creating or forgot password/username
     */
    public void setEmailId(String email) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(PreferenceNames.CREATE_EMAIL_ID, email);
        editor.commit();
    }

    /**
     * Get chosen email-id used while creating or forgot password/username
     */
    public String getEmailId() {
        String email = "";
        try {
            email = sharedPrefs.getString(PreferenceNames.CREATE_EMAIL_ID, "");
        } catch (Exception ex) {
//                DLogger.Companion.e(TAG, ex);
        }
        return email;
    }
    /**
     * Set chosen email-id used while creating or forgot password/username
     */
    public void setUserName(String userName) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(PreferenceNames.USER_NAME, userName);
        editor.commit();
    }

    /**
     * Get chosen email-id used while creating or forgot password/username
     */
    public String getUserName() {
        String username = "";
        try {
            username = sharedPrefs.getString(PreferenceNames.USER_NAME, "");
        } catch (Exception ex) {
        }
        return username;
    }

    public void setFirstLaunch(Boolean isFirstLaunch) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(PreferenceNames.IS_FIRST_LAUNCH, isFirstLaunch);
        editor.apply();
    }

    public Boolean getFirstLaunch() {
        return sharedPrefs.getBoolean(PreferenceNames.IS_FIRST_LAUNCH, true);
    }

        static class PreferenceNames {

            private PreferenceNames() {
                throw new IllegalStateException("Utility Class");
            }

            public static final String TOKEN = "TOKEN";
            public static final String IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH";


            private static final String APP_SHARED_PREFS = "com.dish.mydish"; //  Name of the file -.xml

            private static final String CREATE_EMAIL_ID = "CREATE_EMAIL_ID";

            private static final String USER_NAME = "USER_NAME";;
        }



}
