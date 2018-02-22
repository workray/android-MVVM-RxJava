package com.mobdev.android_mvvm.ui.fragments.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobdev.android_mvvm.R;
import com.mobdev.android_mvvm.base.BaseNavigationFragment;
import com.mobdev.android_mvvm.base.BaseViewModel;
import com.mobdev.android_mvvm.databinding.FragmentNavigationBinding;
import com.mobdev.android_mvvm.databinding.FragmentTemplateBinding;

/**
 * Created by mobdev125 on 2/20/18.
 */

public abstract class NavigationFragment<T extends BaseViewModel> extends BaseNavigationFragment<FragmentNavigationBinding, T> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = (FragmentNavigationBinding) bindView(inflater, container, R.layout.fragment_navigation);
        }
        return binding.getRoot();
    }

    @Override
    protected int getContentId() {
        return R.id.content_navigation_frameLayout;
    }
}