package com.mobdev.android_mvvm.base;

/**
 * Created by mobdev125 on 2/15/18.
 */

public interface IView {
    void error(String errorMsg);
    void error(Throwable e);
    void error();
}