package com.mobdev.android_mvvm.networkplatform.usecases;

import com.mobdev.android_mvvm.domain.usecases.DomainPostsUseCase;
import com.mobdev.android_mvvm.domain.usecases.DomainUseCaseProvider;
import com.mobdev.android_mvvm.networkplatform.api.PostsNetwork;
import com.mobdev.android_mvvm.networkplatform.network.NetworkPlatformModule;

import javax.inject.Inject;

/**
 * Created by mobdev125 on 2/19/18.
 */

public final class NetworkUseCaseProvider implements DomainUseCaseProvider {

    @Override
    public DomainPostsUseCase makePostsUseCase() {
        return new NetworkPostsUseCase();
    }
}
