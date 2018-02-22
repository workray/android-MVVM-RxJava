package com.mobdev.android_mvvm.ui.fragments.navigation.postsnavigation;

import android.os.Bundle;

import com.mobdev.android_mvvm.domain.entities.Post;
import com.mobdev.android_mvvm.domain.usecases.DomainUseCaseProvider;
import com.mobdev.android_mvvm.ui.fragments.allposts.PostsFragment;
import com.mobdev.android_mvvm.ui.fragments.navigation.NavigationFragment;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by mobdev125 on 2/20/18.
 */

public class PostsNavigationFragment extends NavigationFragment<PostsNavigationFragmentViewModel> implements PostsNavigationFragmentView {

    private DomainUseCaseProvider useCaseProvider;

    public static PostsNavigationFragment newInstance(String navigation, DomainUseCaseProvider useCaseProvider) {
        PostsNavigationFragment fragment = new PostsNavigationFragment();
        fragment.navigationType = navigation;
        fragment.useCaseProvider = useCaseProvider;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new PostsNavigationFragmentViewModel(useCaseProvider.makePostsUseCase());
        viewModel.attach(this);

    }

    @Override
    public void bindViewModel() {
        // Observable to open posts fragment when open the fragment at first.
        Observable<Boolean> empty = Observable.create(emitter -> emitter.onNext(getCurrentFragment() == null));

        PostsNavigationFragmentViewModel.Input input = new PostsNavigationFragmentViewModel.Input();
        input.trigger = empty;

        viewModel.transform(input);
    }

    @Override
    public void clear() {
        super.clear();
        useCaseProvider = null;
    }

    @Override
    public void toCreatePost() {

    }

    @Override
    public void toPost(Post post) {
//        PostsFragment fragment = PostsFragment.newInstance(useCaseProvider.makePostsUseCase());
//        pushFragment(fragment);
    }

    @Override
    public void toPosts() {
        PostsFragment fragment = PostsFragment.newInstance(useCaseProvider.makePostsUseCase());
        pushFragment(fragment);
    }
}
