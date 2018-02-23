package com.mobdev.android_mvvm.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

/**
 * Created by mobdev125 on 2/15/18.
 */

public abstract class BaseNavigationFragment<B extends ViewDataBinding, T extends BaseViewModel> extends BaseFragment<B, T> {
    protected String navigationType;
    protected FragmentHandler fragmentHandler;

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null && getActivity() instanceof FragmentManager.OnBackStackChangedListener) {
            getChildFragmentManager().addOnBackStackChangedListener((FragmentManager.OnBackStackChangedListener) getActivity());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getActivity() != null && getActivity() instanceof FragmentManager.OnBackStackChangedListener) {
            getChildFragmentManager().removeOnBackStackChangedListener((FragmentManager.OnBackStackChangedListener) getActivity());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentHandler = new FragmentHandler(fragmentManager);
    }

    public void onBack() {
        Log.e(getTag(), "OnBack");
        if (fragmentHandler != null) fragmentHandler.onBack();
    }

    public boolean isBackable() {
        boolean isBack = fragmentHandler != null && fragmentHandler.isBackable();
        Log.e(getTag(), String.valueOf(isBack));
        return isBack;
    }

    @Override
    public void clear() {
        super.clear();
        if (getActivity() != null) {
            getChildFragmentManager().removeOnBackStackChangedListener((FragmentManager.OnBackStackChangedListener) getActivity());
        }
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
        if (getActivity() != null && getActivity() instanceof FragmentManager.OnBackStackChangedListener) {
            ((FragmentManager.OnBackStackChangedListener) getActivity()).onBackStackChanged();
        }
    }

    public void pushFragment(BaseFragment fragment) {
        fragment.navigationFragment = this;
        fragmentHandler.pushFragment(fragment, getContentId());
    }

    public void popFragment() {
        fragmentHandler.popFragment();
    }

    public BaseFragment getCurrentFragment() {
        return fragmentHandler.getCurrentFragment();
    }

    public String getNavigationType() {
        return navigationType;
    }

    protected abstract int getContentId();
}