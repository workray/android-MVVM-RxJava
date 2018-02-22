package com.mobdev.android_mvvm.networkplatform.api;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by mobdev125 on 2/15/18.
 */

@GsonTypeAdapterFactory
public abstract class AutoValueGsonFactory implements TypeAdapterFactory {
    // Static factory method to access the package
    // private generated implementation
    public static TypeAdapterFactory create() {
        return new AutoValueGson_AutoValueGsonFactory();
    }
}
