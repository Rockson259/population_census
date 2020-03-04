package com.example.population_census;


import android.app.Application;
import timber.log.Timber;

public class App extends Application {

    private static String mCurrentUserId;

    @Override
    public void onCreate () {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }


    }


    public static String getCurrentUserId() {
        return mCurrentUserId != null ? mCurrentUserId : "";
    }

    public static void setCurrentUserId(String mCurrentUserId) {
        App.mCurrentUserId = mCurrentUserId;
    }
}