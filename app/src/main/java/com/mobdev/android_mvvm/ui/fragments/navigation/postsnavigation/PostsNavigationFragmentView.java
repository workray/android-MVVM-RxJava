package com.mobdev.android_mvvm.ui.fragments.navigation.postsnavigation;

import com.mobdev.android_mvvm.base.IView;
import com.mobdev.android_mvvm.domain.entities.Post;

/**
 * Created by mobdev125 on 2/20/18.
 */

public interface PostsNavigationFragmentView extends IView {
    void toCreatePost();
    void toPost(Post post);
    void toPosts();
}
