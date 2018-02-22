package com.mobdev.android_mvvm.networkplatform.api;

import com.mobdev.android_mvvm.domain.entities.Album;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mobdev125 on 2/19/18.
 */

public interface AlbumsNetwork {
    @GET("/albums")
    Flowable<List<Album>> fetchAlbums();

    @GET("/albums/{albumId}")
    Flowable<Album> getchAlbum(@Path("albumId") String albumId);
}
