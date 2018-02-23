package com.mobdev.android_mvvm.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;

/**
 * Created by mobdev125 on 2/20/18.
 */

public abstract class BaseNavigationActivity<B extends ViewDataBinding, T extends BaseViewModel> extends BaseActivity<B, T> implements FragmentManager.OnBackStackChangedListener {
    protected BaseNavigationFragment navigationFragment;

    private final View.OnClickListener navigationBackPressListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (navigationFragment != null && navigationFragment.isBackable()) {
                navigationFragment.onBack();
                syncDrawerToggleState();
            }
        }
    };

    private void syncDrawerToggleState() {
        ActionBarDrawerToggle drawerToggle = getDrawerToggle();
        if (drawerToggle == null || navigationFragment == null) {
            return;
        }

        Log.e("Activity", "syncDrawerToggleState");
        if (navigationFragment.isBackable()) {
            drawerToggle.setDrawerIndicatorEnabled(false);
            drawerToggle.setToolbarNavigationClickListener(navigationBackPressListener);
        } else {
            drawerToggle.setDrawerIndicatorEnabled(true);
            drawerToggle.setToolbarNavigationClickListener(drawerToggle.getToolbarNavigationClickListener());
        }
    }

    @Override
    public void onBackStackChanged() {
        syncDrawerToggleState();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getDrawer() != null) {
            getDrawer().addDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                }

                @Override
                public void onDrawerOpened(@NonNull View drawerView) {
                    syncDrawerToggleState();
                }

                @Override
                public void onDrawerClosed(@NonNull View drawerView) {
                    syncDrawerToggleState();
                }

                @Override
                public void onDrawerStateChanged(int newState) {

                }
            });
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        if (sendBackPressToDrawer()) {
            syncDrawerToggleState();
            return;
        }
        if (sendBackPressToFragmentOnTop()) {
            syncDrawerToggleState();
            return;
        }

        finish();
    }

    public void addFragment(BaseNavigationFragment fragment) {
        if (fragment == null || (navigationFragment != null && fragment.getNavigationType().equals(navigationFragment.getNavigationType()))) {
            return;
        }

        String tag = fragment.toTag();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (navigationFragment == null) {
            transaction.add(getContentFrameLayoutId(), fragment, tag);
            transaction.addToBackStack(null);
        } else {
            Fragment fragment1 = getSupportFragmentManager().findFragmentByTag(tag);
            if (fragment1 != null && fragment1.isAdded()) {
                transaction.show(fragment1);
            }
            else {
                transaction.add(getContentFrameLayoutId(), fragment, tag);
                transaction.addToBackStack(null);
            }
            transaction.hide(navigationFragment);
        }
        transaction.commit();

        navigationFragment = fragment;
        onBackStackChanged();
    }

    private boolean sendBackPressToDrawer() {
        DrawerLayout drawerLayout = getDrawer();
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    private boolean sendBackPressToFragmentOnTop() {
        Log.e("Activity", "sendBackPressToFragmentOnTop");
        if (navigationFragment != null && navigationFragment.isBackable()) {
            navigationFragment.onBack();
            return true;
        }
        return false;
    }

    protected abstract int getContentFrameLayoutId();

    protected abstract ActionBarDrawerToggle getDrawerToggle();

    protected abstract DrawerLayout getDrawer();
}
