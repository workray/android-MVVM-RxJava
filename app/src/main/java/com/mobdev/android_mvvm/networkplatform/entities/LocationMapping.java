package com.mobdev.android_mvvm.networkplatform.entities;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mobdev125 on 2/19/18.
 */

@AutoValue
public abstract class LocationMapping {
    public static TypeAdapter<LocationMapping> typeAdapter(Gson gson) {
        return new AutoValue_LocationMapping.GsonTypeAdapter(gson);
    }

    @SerializedName("longitude")
    public abstract Double getLongitude();

    @SerializedName("latitude")
    public abstract Double getLatitude();
}
