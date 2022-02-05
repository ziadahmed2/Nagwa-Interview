package com.example.nagwainterview;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class NagwaApplication extends Application implements HasActivityInjector {

    private static Context appContext;
    private static  NagwaApplication instance;

    @Inject
    DispatchingAndroidInjector<Activity> androidInjector;

    public static synchronized NagwaApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        instance = this;
    }

    public static Context getAppContext(){
        return appContext;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }
}
