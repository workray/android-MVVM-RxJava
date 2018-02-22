package com.mobdev.android_mvvm.domain.usecases;

import com.mobdev.android_mvvm.domain.entities.Post;

import java.util.List;
import io.reactivex.Flowable;

/**
 * Created by mobdev125 on 2/19/18.
 */

public interface DomainPostsUseCase {
    Flowable<List<Post>> posts();
    Flowable<Void> save(Post post);
    Flowable<Void> delete(Post post);
}
