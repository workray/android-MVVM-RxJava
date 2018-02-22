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
public abstract class CommentMapping {
    public static TypeAdapter<CommentMapping> typeAdapter(Gson gson) {
        return new AutoValue_CommentMapping.GsonTypeAdapter(gson);
    }

    @SerializedName("body")
    public abstract String getBody();

    @SerializedName("email")
    public abstract String getEmail();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("postId")
    @Nullable
    public abstract String getPostId();

    @SerializedName("id")
    @Nullable
    public abstract String getId();
}
