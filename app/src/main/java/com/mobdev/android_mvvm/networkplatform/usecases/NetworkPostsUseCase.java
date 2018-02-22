package com.mobdev.android_mvvm.networkplatform.usecases;

import com.mobdev.android_mvvm.MyApplication;
import com.mobdev.android_mvvm.domain.entities.Post;
import com.mobdev.android_mvvm.domain.usecases.DomainPostsUseCase;
import com.mobdev.android_mvvm.networkplatform.api.PostsNetwork;
import com.mobdev.android_mvvm.networkplatform.entities.PostMapping;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mobdev125 on 2/19/18.
 */

public class NetworkPostsUseCase implements DomainPostsUseCase {
    @Inject
    PostsNetwork api;

    NetworkPostsUseCase() {
        MyApplication.getComponent().inject(this);
    }

    @Override
    public Flowable<List<Post>> posts() {
        return api.fetchPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(list -> list)
                .map(PostMapping::asDomain)
                .toList()
                .toFlowable();
    }

    @Override
    public Flowable<Void> save(Post post) {
        return api.createPost(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(post1 -> null);
    }

    @Override
    public Flowable<Void> delete(Post post) {
        return api.deletePost(post.getUid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(post1 -> null);
    }
}
