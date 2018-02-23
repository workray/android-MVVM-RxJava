package com.mobdev.android_mvvm.ui.fragments.allposts;

import com.mobdev.android_mvvm.base.BaseViewModel;
import com.mobdev.android_mvvm.domain.usecases.DomainPostsUseCase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by mobdev125 on 2/20/18.
 */

public class PostsFragmentViewModel extends BaseViewModel<PostsFragmentView> {
    private DomainPostsUseCase useCase;
    private Disposable postsDisposable;

    PostsFragmentViewModel(DomainPostsUseCase useCase) {
        this.useCase = useCase;
    }

    void transform(Input input) {

        addSubscription(input.triggered
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.computation())
                .filter(aBoolean -> aBoolean)
                .observeOn(Schedulers.io())
                .subscribe(aBoolean -> getPosts(),
                        throwable -> {
                            view.showLoadingPullToRefresh(false);
                            view.error(throwable);
                        }));

        addSubscription(input.itemClick
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postItemViewModel -> view.toPost(postItemViewModel)));
    }

    private void getPosts() {
        if (postsDisposable != null) {
            postsDisposable.dispose();
            postsDisposable = null;
        }
        postsDisposable = useCase.posts()
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(posts -> view.showLoadingPullToRefresh(false))
                .observeOn(Schedulers.io())
                .flatMapIterable(posts -> posts)
                .map(PostItemViewModel::new)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postItemViewModels -> view.load(postItemViewModels), throwable -> view.error(throwable));
    }

    @Override
    public void clear() {
        super.clear();
        postsDisposable.dispose();
    }

    static class Input {
        Observable<Boolean> triggered;
        Observable<PostItemViewModel> itemClick;
    }
}
