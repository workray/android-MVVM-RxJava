package com.mobdev.android_mvvm.networkplatform.api;

import com.mobdev.android_mvvm.domain.entities.Post;
import com.mobdev.android_mvvm.networkplatform.entities.PostMapping;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by mobdev125 on 2/19/18.
 */

public interface PostsNetwork {
    @GET("/posts")
    Flowable<List<PostMapping>> fetchPosts();

    @GET("/posts/{postId}")
    Flowable<Post> fetchPost(@Path("postId") String postId);

    @POST("/posts")
    Flowable<Post> createPost(@Body Post post);

    @DELETE("/posts/{postId}")
    Flowable<Post> deletePost(@Path("postId") String postId);
}
