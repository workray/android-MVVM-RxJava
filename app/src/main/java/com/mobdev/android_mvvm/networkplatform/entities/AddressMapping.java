package com.mobdev.android_mvvm.networkplatform.entities;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mobdev125 on 2/19/18.
 */

@AutoValue
public abstract class AddressMapping {
    public static TypeAdapter<AddressMapping> typeAdapter(Gson gson) {
        return new AutoValue_AddressMapping.GsonTypeAdapter(gson);
    }

    @SerializedName("city")
    public abstract String getCity();

    @SerializedName("geo")
    public abstract String getGeo();

    @SerializedName("street")
    public abstract String getStreet();

    @SerializedName("suite")
    public abstract String getSuite();

    @SerializedName("zipcode")
    public abstract String getZipcode();
}
