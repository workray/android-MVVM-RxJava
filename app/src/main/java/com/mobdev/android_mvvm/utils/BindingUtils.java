package com.mobdev.android_mvvm.utils;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobdev.android_mvvm.di.module.GlideApp;
import com.mobdev.android_mvvm.di.module.GlideAppModule;

/**
 * Created by mobdev125 on 2/15/18.
 */

public class BindingUtils {
    @SuppressLint("SetTextI18n")
    @BindingAdapter("android:text")
    public static void setInt(TextView view, int value) {
        view.setText(Integer.toString(value));
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getInt(TextView view) {
        String num = view.getText().toString();
        if (num.isEmpty()) return 0;
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        if (view.getContext() != null) {
            GlideApp.with(view.getContext().getApplicationContext()).load(url).into(view);
        }
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView view, int resource) {
        view.setImageResource(resource);
    }
}