package com.mobdev.android_mvvm.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;

import com.mobdev.android_mvvm.di.module.GlideApp;
import com.mobdev.android_mvvm.utils.AlertView;


/**
 * Created by mobdev125 on 2/15/18.
 */

public abstract class BaseActivity<B extends ViewDataBinding, T extends BaseViewModel> extends AppCompatActivity implements IView {
    protected T viewModel;
    protected B binding;

    /**
     * ViewModel must be initialized before bindView() is called
     *
     * @param layout layout for the activity
     */
    public final void bindView(int layout) {
        if (viewModel == null) {
            throw new IllegalStateException("viewModel must not be null and should be injected via activityComponent().inject(this)");
        }
        binding = DataBindingUtil.setContentView(this, layout);
    }

    public abstract void bindViewModel();

    @Override
    protected void onStart() {
        super.onStart();
        bindViewModel();
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.clearSubscriptions();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.detach();
        GlideApp.with(getApplicationContext()).pauseAllRequests();
    }

    @Override
    public void error(String errorMsg) {
        AlertView.showErrorMsg(this, errorMsg);
    }

    @Override
    public void error(Throwable e) {
        AlertView.showErrorMsg(this, e.getMessage());
    }

    @Override
    public void error() {
        AlertView.showErrorMsg(this, "Unknown Error!!!");
    }
}
