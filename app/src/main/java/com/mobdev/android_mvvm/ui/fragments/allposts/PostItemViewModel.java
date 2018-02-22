package com.mobdev.android_mvvm.ui.fragments.allposts;

import com.mobdev.android_mvvm.domain.entities.Post;

/**
 * Created by mobdev125 on 2/19/18.
 */

public class PostItemViewModel {
    private String title;
    private String subtitle;
    private Post post;

    public PostItemViewModel(Post post) {
        this.post = post;
        this.title = post.getTitle().toUpperCase();
        this.subtitle = post.getBody();
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
}
