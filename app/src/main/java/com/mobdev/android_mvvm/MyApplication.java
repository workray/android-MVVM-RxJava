package com.mobdev.android_mvvm;

import android.app.Application;

import com.mobdev.android_mvvm.di.component.AppComponent;
import com.mobdev.android_mvvm.di.component.DaggerAppComponent;
import com.mobdev.android_mvvm.di.module.AppModule;
import com.mobdev.android_mvvm.di.module.NetworkModule;
import com.mobdev.android_mvvm.networkplatform.network.NetworkPlatformModule;


/**
 * Created by mobdev125 on 2/15/18.
 */

public class MyApplication extends Application {

    private static AppComponent component;

    @Override public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .networkPlatformModule(new NetworkPlatformModule())
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }


}