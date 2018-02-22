package com.mobdev.android_mvvm.networkplatform.api;

import com.mobdev.android_mvvm.domain.entities.Comment;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mobdev125 on 2/19/18.
 */

public interface CommentsNetwork {
    @GET("/comments")
    Flowable<List<Comment>> fetchComments();

    @GET("/comments/{commentId}")
    Flowable<Comment> fetchComment(@Path("commentId") String commentId);
}
