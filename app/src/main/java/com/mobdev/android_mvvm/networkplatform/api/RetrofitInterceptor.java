package com.mobdev.android_mvvm.networkplatform.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mobdev125 on 2/15/18.
 */

public class RetrofitInterceptor implements Interceptor {

    private String token = null;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
//                .addHeader("Authorization", token)
                .build();
        return chain.proceed(request);
    }
}
