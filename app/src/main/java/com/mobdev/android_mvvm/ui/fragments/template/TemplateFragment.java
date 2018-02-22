package com.mobdev.android_mvvm.ui.fragments.template;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobdev.android_mvvm.R;
import com.mobdev.android_mvvm.base.BaseFragment;
import com.mobdev.android_mvvm.base.BaseNavigationFragment;
import com.mobdev.android_mvvm.databinding.FragmentHomeBinding;
import com.mobdev.android_mvvm.databinding.FragmentTemplateBinding;
import com.mobdev.android_mvvm.ui.fragments.home.HomeFragmentView;
import com.mobdev.android_mvvm.ui.fragments.home.HomeFragmentViewModel;

/**
 * Created by mobdev125 on 2/20/18.
 */

public class TemplateFragment extends BaseNavigationFragment<FragmentTemplateBinding, TemplateFragmentViewModel> implements TemplateFragmentView {

    public static TemplateFragment newInstance(String navigation) {
        TemplateFragment fragment = new TemplateFragment();
        fragment.navigationType = navigation;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        viewModel = new TemplateFragmentViewModel();
        viewModel.attach(this);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.create, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = (FragmentTemplateBinding) bindView(inflater, container, R.layout.fragment_template);
            binding.textView.setText(navigationType);
        }
        return binding.getRoot();
    }

    @Override
    protected int getContentId() {
        return R.id.content_temp_frameLayout;
    }

    @Override
    public void bindViewModel() {

    }
}
