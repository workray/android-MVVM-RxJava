package com.mobdev.android_mvvm.networkplatform.entities;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mobdev125 on 2/19/18.
 */

@AutoValue
public abstract class CompanyMapping {
    public static TypeAdapter<CompanyMapping> typeAdapter(Gson gson) {
        return new AutoValue_CompanyMapping.GsonTypeAdapter(gson);
    }

    @SerializedName("bs")
    public abstract String getBs();

    @SerializedName("catchPhrase")
    public abstract String getCatchPhrase();

    @SerializedName("name")
    public abstract String getName();
}
