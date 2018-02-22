package com.mobdev.android_mvvm.ui.activities.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import com.jakewharton.rxbinding2.support.design.widget.RxNavigationView;
import com.mobdev.android_mvvm.MyApplication;
import com.mobdev.android_mvvm.R;
import com.mobdev.android_mvvm.base.BaseNavigationActivity;
import com.mobdev.android_mvvm.base.BaseNavigationFragment;
import com.mobdev.android_mvvm.databinding.ActivityMainBinding;
import com.mobdev.android_mvvm.ui.fragments.home.HomeFragment;
import com.mobdev.android_mvvm.ui.fragments.template.TemplateFragment;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


public class MainActivity extends BaseNavigationActivity<ActivityMainBinding, MainActivityViewModel> implements MainActivityView {

    BaseNavigationFragment homeNavigation;
    BaseNavigationFragment galleryNavigation;
    private int currentMenuId = -1;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getComponent().inject(this);

        viewModel = new MainActivityViewModel();
        viewModel.attach(this);

        bindView(R.layout.activity_main);

        setupDrawerAndToggle();
    }

    private void setupDrawerAndToggle() {
        setSupportActionBar(binding.include.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.include.toolbar, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setDrawerIndicatorEnabled(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected int getContentFrameLayoutId() {
        return R.id.content_main_frameLayout;
    }

    @Override
    protected ActionBarDrawerToggle getDrawerToggle() {
        return drawerToggle;
    }

    @Override
    protected DrawerLayout getDrawer() {
        return binding.drawerLayout;
    }


    @Override
    public void bindViewModel() {

        // Observable to open home fragment when open the app at first.
        Observable<MenuItem> firstNavigationItemSelection = Observable.create(emitter -> {
            if (!binding.navView.isSelected()) {
                emitter.onNext(binding.navView.getMenu().getItem(0));
            }
        });

        // Observable to change navigation fragment when select menu
        Observable<MenuItem> navigationItemSelections = RxNavigationView.itemSelections(binding.navView);
        MainActivityViewModel.Input input = new MainActivityViewModel.Input();

        input.menuItemTrigger = Observable.merge(firstNavigationItemSelection, navigationItemSelections);

        viewModel.transform(input);
    }

    @Override
    public void closeDrawerLayout() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean navigationItemSelected(@NonNull MenuItem menuItem) {
        if (currentMenuId == menuItem.getItemId()) {
            return true;
        }

        if (!menuItem.isChecked()) {
            menuItem.setChecked(true);
        }

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                toHome(menuItem);
                currentMenuId = R.id.nav_home;
                return true;
            case R.id.nav_gallery:
                toGallery(menuItem);
                currentMenuId = R.id.nav_gallery;
                return true;
        }
        return false;
    }

    private void toHome(MenuItem menuItem) {
        if (homeNavigation == null) {
            homeNavigation = HomeFragment.newInstance(menuItem.getTitle().toString());
        }
        addFragment(homeNavigation);
    }

    private void toGallery(MenuItem menuItem) {
        if (galleryNavigation == null) {
            galleryNavigation = TemplateFragment.newInstance(menuItem.getTitle().toString());
        }
        addFragment(galleryNavigation);
    }
}
