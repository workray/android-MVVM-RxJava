package com.mobdev.android_mvvm.ui.fragments.allposts;

import com.mobdev.android_mvvm.base.IView;

import java.util.List;

/**
 * Created by mobdev125 on 2/20/18.
 */

public interface PostsFragmentView extends IView {
    void showLoadingPullToRefresh(boolean isShow);
    void init();
    void load(List<PostItemViewModel> items);
    void toPost(PostItemViewModel item);
}
