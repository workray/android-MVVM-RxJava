package com.mobdev.android_mvvm.ui.fragments.allposts;

import android.support.v7.widget.RecyclerView;

import com.mobdev.android_mvvm.databinding.PostsRowLayoutBinding;

/**
 * Created by mobdev125 on 2/19/18.
 */

class PostsViewHolder extends RecyclerView.ViewHolder {

    private PostsRowLayoutBinding binding;
    PostsViewHolder(PostsRowLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void update(PostItemViewModel item) {
        binding.setItem(item);
        binding.executePendingBindings();
    }
}
