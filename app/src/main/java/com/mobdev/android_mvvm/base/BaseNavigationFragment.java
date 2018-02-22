package com.mobdev.android_mvvm.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

/**
 * Created by mobdev125 on 2/15/18.
 */

public abstract class BaseNavigationFragment<B extends ViewDataBinding, T extends BaseViewModel> extends BaseFragment<B, T> {
    protected String navigationType;
    protected FragmentHandler fragmentHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getChildFragmentManager();
        if (getActivity() instanceof FragmentManager.OnBackStackChangedListener) {
            fragmentManager.addOnBackStackChangedListener((FragmentManager.OnBackStackChangedListener) getActivity());
        }
        fragmentHandler = new FragmentHandler(fragmentManager);
    }

    public boolean onBack(boolean isBack) {
        return fragmentHandler.onBack(isBack);
    }

    public void clear() {
        super.clear();
        if (fragmentHandler != null) {
            fragmentHandler.removeAllFragments();
            fragmentHandler = null;
        }
        navigationType = null;
    }

    public BaseFragment gotoFirstFragment() {
        return fragmentHandler.gotoFirstFragment();
    }

    public void replaceFragment(BaseFragment fragment) {
        if (fragment == null) {
            return;
        }

        fragment.navigationFragment = this;
        fragmentHandler.replaceFragment(fragment, getContentId());
    }

    public void pushFragment(BaseFragment fragment) {
        fragment.navigationFragment = this;
        fragmentHandler.pushFragment(fragment, getContentId());
    }

    public BaseFragment getCurrentFragment() {
        return fragmentHandler.getCurrentFragment();
    }

    public String getNavigationType() {
        return navigationType;
    }

    protected abstract int getContentId();
}