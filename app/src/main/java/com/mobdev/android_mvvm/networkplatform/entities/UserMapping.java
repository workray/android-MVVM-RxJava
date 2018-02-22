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
public abstract class UserMapping {
    public static TypeAdapter<UserMapping> typeAdapter(Gson gson) {
        return new AutoValue_UserMapping.GsonTypeAdapter(gson);
    }

    @SerializedName("address")
    public abstract String getAddress();

    @SerializedName("company")
    public abstract String getCompany();

    @SerializedName("email")
    public abstract String getEmail();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("id")
    @Nullable
    public abstract String getId();

    @SerializedName("username")
    public abstract String getUsername();

    @SerializedName("website")
    public abstract String getWebsite();
}
