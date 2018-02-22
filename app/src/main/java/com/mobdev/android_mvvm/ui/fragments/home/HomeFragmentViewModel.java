package com.mobdev.android_mvvm.ui.fragments.home;

import android.view.MenuItem;

import com.mobdev.android_mvvm.base.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by mobdev125 on 2/20/18.
 */

public class HomeFragmentViewModel extends BaseViewModel<HomeFragmentView> {
    void transform(Input input) {

        addSubscription(input.menuItemTrigger
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(menuItem -> view.bottomNavigationItemSelected(menuItem)));
    }

    static class Input {
        Observable<MenuItem> menuItemTrigger;
    }
}
