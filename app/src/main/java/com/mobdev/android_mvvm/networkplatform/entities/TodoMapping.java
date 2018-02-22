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
public abstract class TodoMapping {
    public static TypeAdapter<TodoMapping> typeAdapter(Gson gson) {
        return new AutoValue_TodoMapping.GsonTypeAdapter(gson);
    }

    @SerializedName("completed")
    public abstract Boolean getCompleted();

    @SerializedName("title")
    public abstract String getTitle();

    @SerializedName("id")
    @Nullable
    public abstract String getId();

    @SerializedName("userId")
    @Nullable
    public abstract String getUserId();
}
