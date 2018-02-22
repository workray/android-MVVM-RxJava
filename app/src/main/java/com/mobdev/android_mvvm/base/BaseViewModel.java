package com.mobdev.android_mvvm.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by mobdev125 on 2/15/18.
 */

public class BaseViewModel<T extends IView> {
    protected CompositeDisposable compositeDisposable;
    protected T view;

    public BaseViewModel() {
        compositeDisposable = new CompositeDisposable();
    }

    public void attach(T view) {
        this.view = view;
    }

    public void detach() {
        view = null;
    }

    public void addSubscription(Disposable d) {
        if (compositeDisposable != null) {
            compositeDisposable.add(d);
        }
    }
    public void clearSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void clear() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
        detach();
    }
}
