package com.mobdev.android_mvvm.ui.fragments.navigation.postsnavigation;

import com.mobdev.android_mvvm.base.BaseViewModel;
import com.mobdev.android_mvvm.domain.usecases.DomainPostsUseCase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mobdev125 on 2/20/18.
 */

public class PostsNavigationFragmentViewModel extends BaseViewModel<PostsNavigationFragmentView> {
    private DomainPostsUseCase useCase;

    PostsNavigationFragmentViewModel(DomainPostsUseCase useCase) {
        this.useCase = useCase;
    }

    void transform(Input input) {
        addSubscription(input.trigger
                .observeOn(Schedulers.io())
                .filter(aBoolean -> aBoolean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next -> view.toPosts()));
    }

    @Override
    public void clear() {
        super.clear();
        useCase = null;
    }

    static class Input {
        Observable<Boolean> trigger;
    }
}
