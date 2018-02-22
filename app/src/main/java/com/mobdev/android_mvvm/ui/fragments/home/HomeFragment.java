package com.mobdev.android_mvvm.ui.fragments.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView;
import com.mobdev.android_mvvm.R;
import com.mobdev.android_mvvm.base.BaseNavigationFragment;
import com.mobdev.android_mvvm.databinding.FragmentHomeBinding;
import com.mobdev.android_mvvm.networkplatform.usecases.NetworkUseCaseProvider;
import com.mobdev.android_mvvm.ui.fragments.navigation.postsnavigation.PostsNavigationFragment;
import com.mobdev.android_mvvm.ui.fragments.template.TemplateFragment;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;

/**
 * Created by mobdev125 on 2/20/18.
 */

public class HomeFragment extends BaseNavigationFragment<FragmentHomeBinding, HomeFragmentViewModel> implements HomeFragmentView {

    private PostsNavigationFragment networkNavigation;
    private TemplateFragment realmNavigation;

    private int currentTabItemId = -1;

    public static HomeFragment newInstance(@NonNull String navigation) {
        HomeFragment fragment = new HomeFragment();
        fragment.navigationType = navigation;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new HomeFragmentViewModel();
        viewModel.attach(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = (FragmentHomeBinding) bindView(inflater, container, R.layout.fragment_home);
        }
        return binding.getRoot();
    }

    @Override
    public void bindViewModel() {
/* Input */
        // Observable to open home fragment when open the app at first.
        Observable<MenuItem> firstNavigationItemSelection = Observable.create(emitter -> {
            if (binding.bottomNavigation.getSelectedItemId() == -1 ) {
                emitter.onNext(binding.bottomNavigation.getMenu().getItem(0));
            }
        });

        // Observable to change navigation fragment when select menu
        Observable<MenuItem> navigationItemSelections = RxBottomNavigationView.itemSelections(binding.bottomNavigation);

        HomeFragmentViewModel.Input input = new HomeFragmentViewModel.Input();
        input.menuItemTrigger = Observable.merge(firstNavigationItemSelection, navigationItemSelections);

        viewModel.transform(input);
    }

    @Override
    public void onStop() {
        super.onStop();
//        RxBottomNavigationView.itemSelections(binding.bottomNavigation).
    }

    @Override
    protected int getContentId() {
        return R.id.content_home_frameLayout;
    }

    @Override
    public boolean bottomNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (currentTabItemId == menuItem.getItemId()) {
            return true;
        }

        if (!menuItem.isChecked()) {
            menuItem.setChecked(true);
        }

        switch (menuItem.getItemId()) {
            case R.id.navigation_network:
                toNetwork(menuItem);
                currentTabItemId = R.id.nav_home;
                return true;
            case R.id.navigation_realm:
                toRealm(menuItem);
                currentTabItemId = R.id.nav_gallery;
                return true;
        }
        return false;
    }

    private void toNetwork(MenuItem menuItem) {
        if (networkNavigation == null) {
            networkNavigation = PostsNavigationFragment.newInstance(menuItem.getTitle().toString(), new NetworkUseCaseProvider());
        }
        replaceFragment(networkNavigation);
    }

    private void toRealm(MenuItem menuItem) {
        if (realmNavigation == null) {
            realmNavigation = TemplateFragment.newInstance(menuItem.getTitle().toString());
        }
        replaceFragment(realmNavigation);
    }
}
