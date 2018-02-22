package com.mobdev.android_mvvm.networkplatform.api;

import com.mobdev.android_mvvm.domain.entities.Photo;
import com.mobdev.android_mvvm.domain.entities.Todo;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mobdev125 on 2/19/18.
 */

public interface TodosNetwork {
    @GET("/todos")
    Flowable<List<Todo>> fetchTodos();

    @GET("/todos/{todoId}")
    Flowable<Todo> fetchPhoto(@Path("todoId") String todoId);
}
