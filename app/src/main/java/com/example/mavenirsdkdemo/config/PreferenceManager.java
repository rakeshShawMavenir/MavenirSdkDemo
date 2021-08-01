package com.example.mavenirsdkdemo.config;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static PreferenceManager mInstance;
    private SharedPreferences appPref;
    private static final String PREF_NAME = "MAV_SDK_DEMO_PREF";

    public static void initialise(Context context) {
        if (mInstance == null) {
            mInstance = new PreferenceManager(context);
        }

    }

    public static PreferenceManager getInstance() {
        return mInstance;
    }

    private PreferenceManager(Context context) {
        appPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

}
