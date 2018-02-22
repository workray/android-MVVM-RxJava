package com.mobdev.android_mvvm.ui.activities.main;

import android.view.MenuItem;

import com.mobdev.android_mvvm.base.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by mobdev125 on 2/19/18.
 */

class MainActivityViewModel extends BaseViewModel<MainActivityView> {
    void transform(Input input) {

        addSubscription(input.menuItemTrigger
                .observeOn(AndroidSchedulers.mainThread())
                .map(menuItem -> view.navigationItemSelected(menuItem))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next -> view.closeDrawerLayout()));
    }

    static class Input {
        Observable<MenuItem> menuItemTrigger;
    }
}
