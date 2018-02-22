package com.mobdev.android_mvvm.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mobdev.android_mvvm.BuildConfig;
import com.mobdev.android_mvvm.networkplatform.api.AutoValueGsonFactory;
import com.mobdev.android_mvvm.networkplatform.api.PostsNetwork;
import com.mobdev.android_mvvm.networkplatform.api.RetrofitInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mobdev125 on 2/15/18.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().serializeNulls().registerTypeAdapterFactory(AutoValueGsonFactory.create()).create();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConvererFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder()
                .addInterceptor(new RetrofitInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, GsonConverterFactory factory) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.END_POINT)
                .client(okHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}