package com.mobdev.android_mvvm.ui.fragments.allposts;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.mobdev.android_mvvm.R;
import com.mobdev.android_mvvm.databinding.PostsRowLayoutBinding;

import java.util.Collections;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by mobdev125 on 2/19/18.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsViewHolder> {
    private List<PostItemViewModel> items = Collections.emptyList();
    private PublishSubject<PostItemViewModel> mViewClickSubject = PublishSubject.create();

    PostsAdapter(final List<PostItemViewModel> items) {
        this.items = items;
    }

    void setItems(List<PostItemViewModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    void clear() {
        items.clear();
        items = null;
        mViewClickSubject = null;
    }

    PublishSubject<PostItemViewModel> getViewClickedOvservable() {
        return mViewClickSubject;
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        PostsRowLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.posts_row_layout, parent, false);
        PostsViewHolder viewHolder = new PostsViewHolder(binding);
        RxView.clicks(viewHolder.itemView)
                .takeUntil(RxView.detaches(parent))
                .map(o -> items.get(viewHolder.getLayoutPosition()))
                .subscribe(mViewClickSubject);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        holder.update(items.get(position));
//        RxView.clicks(holder.itemView)
//                .takeUntil(holder.itemView.getParent())
//                .map(o -> items.get(position))
//                .subscribe(mViewClickSubject);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
