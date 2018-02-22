package com.mobdev.android_mvvm.networkplatform.entities;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mobdev125 on 2/19/18.
 */

@AutoValue
public abstract class AlbumMapping {
    public static TypeAdapter<AlbumMapping> typeAdapter(Gson gson) {
        return new AutoValue_AlbumMapping.GsonTypeAdapter(gson);
    }

    @SerializedName("title")
    public abstract String getTitle();

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("userId")
    public abstract String getUserId();
}
