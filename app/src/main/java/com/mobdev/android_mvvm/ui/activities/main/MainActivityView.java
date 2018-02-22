package com.mobdev.android_mvvm.ui.activities.main;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.mobdev.android_mvvm.base.IView;

/**
 * Created by mobdev125 on 2/20/18.
 */

public interface MainActivityView extends IView {
    boolean navigationItemSelected(@NonNull MenuItem menuItem);
    void closeDrawerLayout();
}
