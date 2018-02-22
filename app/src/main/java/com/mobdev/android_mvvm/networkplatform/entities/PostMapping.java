package com.mobdev.android_mvvm.networkplatform.entities;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mobdev.android_mvvm.domain.entities.Post;
import com.mobdev.android_mvvm.networkplatform.entities.encodable.Encodable;

import io.reactivex.annotations.Nullable;

/**
 * Created by mobdev125 on 2/19/18.
 */

@AutoValue
public abstract class PostMapping {
    public static TypeAdapter<PostMapping> typeAdapter(Gson gson) {
        return new AutoValue_PostMapping.GsonTypeAdapter(gson);
    }

    @SerializedName("body")
    public abstract String getBody();

    @SerializedName("title")
    public abstract String getTitle();

    @SerializedName("id")
    @Nullable
    public abstract String getId();

    @SerializedName("userId")
    @Nullable
    public abstract String getUserId();

    @SerializedName("createdAt")
    @Nullable
    public abstract String getCreatedAt();

    public Post asDomain() {
        return new Post(getBody(), getTitle(), getId(), getUserId(), getCreatedAt());
    }
}
