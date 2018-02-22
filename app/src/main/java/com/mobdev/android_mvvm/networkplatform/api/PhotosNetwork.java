package com.mobdev.android_mvvm.networkplatform.api;

import com.mobdev.android_mvvm.domain.entities.Comment;
import com.mobdev.android_mvvm.domain.entities.Photo;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mobdev125 on 2/19/18.
 */

public interface PhotosNetwork {
    @GET("/photos")
    Flowable<List<Photo>> fetchPhotos();

    @GET("/photos/{photoId}")
    Flowable<Photo> fetchPhoto(@Path("photoId") String photoId);
}
