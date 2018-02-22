package com.mobdev.android_mvvm.networkplatform.api;

import com.mobdev.android_mvvm.domain.entities.Todo;
import com.mobdev.android_mvvm.domain.entities.User;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mobdev125 on 2/19/18.
 */

public interface UsersNetwork {
    @GET("/users")
    Flowable<List<User>> fetchUsers();

    @GET("/users/{userId}")
    Flowable<User> fetchUser(@Path("userId") String userId);
}
