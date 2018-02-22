package com.mobdev.android_mvvm.di.component;



import com.mobdev.android_mvvm.di.module.AppModule;
import com.mobdev.android_mvvm.di.module.NetworkModule;
import com.mobdev.android_mvvm.di.scope.AppScope;
import com.mobdev.android_mvvm.networkplatform.network.NetworkPlatformModule;
import com.mobdev.android_mvvm.networkplatform.usecases.NetworkPostsUseCase;
import com.mobdev.android_mvvm.networkplatform.usecases.NetworkUseCaseProvider;
import com.mobdev.android_mvvm.ui.activities.main.MainActivity;
import com.mobdev.android_mvvm.ui.fragments.allposts.PostsFragment;
import com.mobdev.android_mvvm.ui.fragments.home.HomeFragment;
import com.mobdev.android_mvvm.ui.fragments.template.TemplateFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mobdev125 on 2/15/18.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, NetworkPlatformModule.class})
@AppScope
public interface AppComponent {

    void inject(MainActivity activity);

    void inject(HomeFragment fragment);
    void inject(TemplateFragment fragment);
    void inject(PostsFragment fragment);
//    void inject(RetrofitInterceptor interceptor);

    void inject(NetworkPostsUseCase useCase);
}