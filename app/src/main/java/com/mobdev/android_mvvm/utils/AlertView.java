package com.mobdev.android_mvvm.utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import com.mobdev.android_mvvm.R;
import com.tapadoo.alerter.Alerter;

/**
 * Created by mobdev125 on 2/15/18.
 */

public class AlertView {
    public static void showErrorMsg(Activity activity, String messgeText) {
        Alerter.create(activity)
                .setTitle("Error")
                .setText(messgeText)
                .setIcon(ContextCompat.getDrawable(activity, R.drawable.ic_error))
                .setBackgroundColorRes(R.color.myRed)
                .show();
    }

    public static void showWarningMsg(Activity activity, String warningText) {
        Alerter.create(activity)
                .setTitle("Warning")
                .setText(warningText)
                .setIcon(ContextCompat.getDrawable(activity, R.drawable.ic_warning))
                .setBackgroundColorRes(R.color.myOrange)
                .show();
    }

    public static void showNoticeMsg(Activity activity, String noticeText) {
        Alerter.create(activity)
                .setTitle("Notice")
                .setText(noticeText)
                .setBackgroundColorRes(R.color.myPurple)
                .show();
    }
}