package com.mobdev.android_mvvm.ui.fragments.home;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.mobdev.android_mvvm.base.IView;

/**
 * Created by mobdev125 on 2/20/18.
 */

public interface HomeFragmentView extends IView {
    boolean bottomNavigationItemSelected(@NonNull MenuItem menuItem);
}
