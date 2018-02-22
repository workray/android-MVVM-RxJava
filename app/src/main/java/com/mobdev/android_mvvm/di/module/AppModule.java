package com.mobdev.android_mvvm.di.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mobdev125 on 2/15/18.
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

//    // Dagger will only look for methods annotated with @Provides
//    @Provides
//    @Singleton
//    // Application reference must come from AppModule.class
//    SharedPreferences providesSharedPreferences(Application application) {
//        return PreferenceManager.getDefaultSharedPreferences(application);
//    }
}
