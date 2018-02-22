package com.mobdev.android_mvvm.networkplatform.entities;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

/**
 * Created by mobdev125 on 2/19/18.
 */

@AutoValue
public abstract class PhotoMapping {
    public static TypeAdapter<PhotoMapping> typeAdapter(Gson gson) {
        return new AutoValue_PhotoMapping.GsonTypeAdapter(gson);
    }

    @SerializedName("albumId")
    @Nullable
    public abstract String getAlbumId();

    @SerializedName("thumbnailUrl")
    public abstract String getThumbnailUrl();

    @SerializedName("title")
    public abstract String getTitle();

    @SerializedName("id")
    @Nullable
    public abstract String getId();

    @SerializedName("url")
    public abstract String getUrl();
}
