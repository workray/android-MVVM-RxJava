package com.mobdev.android_mvvm.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by mobdev125 on 2/15/18.
 */

public abstract class BaseFragment<B extends ViewDataBinding, T extends BaseViewModel> extends Fragment implements IView {

    protected B binding;
    public T viewModel;

    public BaseNavigationFragment navigationFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (BuildConfig.DEBUG) {
            Log.i(getTag(), "onCreate");
        }
    }

    /**
     * ViewModel must be initialized before bindView() is called
     *
     * @param layout layout for the fragment
     */
    protected final ViewDataBinding bindView(LayoutInflater inflater, ViewGroup container, int layout) {
        if (viewModel == null) {
            throw new IllegalStateException("viewModel must not be null and should be injected via activityComponent().inject(this)");
        }
        return DataBindingUtil.inflate(inflater, layout, container, false);
    }

    public abstract void bindViewModel();

    @Override
    public void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) {
            Log.i(getTag(), "onStart");
        }
        bindViewModel();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) {
            Log.i(getTag(), "onStop");
        }
        if (viewModel != null) {
            viewModel.clearSubscriptions();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) {
            Log.i(getTag(), "onDestory");
        }
        clear();
    }

    public void clear() {
        if (BuildConfig.DEBUG) {
            Log.i(getTag(), "clear");
        }
        navigationFragment = null;
        binding = null;
        if (viewModel != null) {
            viewModel.clear();
            viewModel = null;
        }
    }

    @Override
    public void error(String errorMsg) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).error(errorMsg);
        }
    }

    @Override
    public void error(Throwable e) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).error(e);
        }
    }

    @Override
    public void error() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).error();
        }
    }

    public String toTag() {
        String tag = getTag();
        if (tag == null) {
            tag = toString();
        }
        return tag;
    }
}

