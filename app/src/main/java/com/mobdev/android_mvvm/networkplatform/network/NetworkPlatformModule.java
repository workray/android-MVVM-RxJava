package com.mobdev.android_mvvm.networkplatform.network;

import com.mobdev.android_mvvm.networkplatform.api.AlbumsNetwork;
import com.mobdev.android_mvvm.networkplatform.api.CommentsNetwork;
import com.mobdev.android_mvvm.networkplatform.api.PhotosNetwork;
import com.mobdev.android_mvvm.networkplatform.api.PostsNetwork;
import com.mobdev.android_mvvm.networkplatform.api.TodosNetwork;
import com.mobdev.android_mvvm.networkplatform.api.UsersNetwork;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by mobdev125 on 2/19/18.
 */

@Module
public class NetworkPlatformModule {
    @Provides
    @Singleton
    AlbumsNetwork provideAlbumsNetwork(Retrofit retrofit) {
        return retrofit.create(AlbumsNetwork.class);
    }

    @Provides
    @Singleton
    CommentsNetwork provideCommentsNetwork(Retrofit retrofit) {
        return retrofit.create(CommentsNetwork.class);
    }

    @Provides
    @Singleton
    PhotosNetwork providePhotosNetwork(Retrofit retrofit) {
        return retrofit.create(PhotosNetwork.class);
    }

    @Provides
    @Singleton
    PostsNetwork providePostsNetwork(Retrofit retrofit) {
        return retrofit.create(PostsNetwork.class);
    }

    @Provides
    @Singleton
    TodosNetwork provideTodosNetwork(Retrofit retrofit) {
        return retrofit.create(TodosNetwork.class);
    }

    @Provides
    @Singleton
    UsersNetwork provideUsersNetwork(Retrofit retrofit) {
        return retrofit.create(UsersNetwork.class);
    }
}
