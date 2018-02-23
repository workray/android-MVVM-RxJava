package com.mobdev.android_mvvm.ui.fragments.allposts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;
import com.mobdev.android_mvvm.R;
import com.mobdev.android_mvvm.base.BaseFragment;
import com.mobdev.android_mvvm.databinding.FragmentPostsBinding;
import com.mobdev.android_mvvm.domain.usecases.DomainPostsUseCase;
import com.mobdev.android_mvvm.ui.fragments.template.TemplateFragment;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by mobdev125 on 2/20/18.
 */

public class PostsFragment extends BaseFragment<FragmentPostsBinding, PostsFragmentViewModel> implements PostsFragmentView {

    private DomainPostsUseCase useCase;
    private PostsAdapter adapter;
    private LinearLayoutManager layoutManager;

    public static PostsFragment newInstance(DomainPostsUseCase useCase) {
        PostsFragment fragment = new PostsFragment();
        fragment.useCase = useCase;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        viewModel = new PostsFragmentViewModel(useCase);
        viewModel.attach(this);

        layoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    public void onDestroy() {
        binding.postsRecyclerView.setLayoutManager(null);
        binding.postsRecyclerView.setAdapter(null);
        if (adapter != null) {
            adapter.clear();
            adapter = null;
        }
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = (FragmentPostsBinding) bindView(inflater, container, R.layout.fragment_posts);
        }
        return binding.getRoot();
    }

    @Override
    public void bindViewModel() {

        init();

        /* Input */
        Observable<Boolean> empty = Observable.create(emitter -> emitter.onNext(isEmpty()));
        Observable<Boolean> pullToRefresh = RxSwipeRefreshLayout.refreshes(binding.postsSwipeRefreshLayout).map(ignored -> true);

        PostsFragmentViewModel.Input input = new PostsFragmentViewModel.Input();
        input.triggered = Observable.merge(empty, pullToRefresh);
        input.itemClick = adapter.getViewClickedOvservable();

        viewModel.transform(input);
    }

    boolean isEmpty() {
        if (adapter == null || adapter.getItemCount() == 0) {
            showLoadingPullToRefresh(true);
            return true;
        }
        return false;
    }

    @Override
    public void showLoadingPullToRefresh(boolean isShow) {
        binding.postsSwipeRefreshLayout.setRefreshing(isShow);
    }

    @Override
    public void init() {
        if (binding.postsRecyclerView.getLayoutManager() == null) {
            binding.postsRecyclerView.setLayoutManager(layoutManager);
        }

        if (adapter == null) {
            adapter = new PostsAdapter(Collections.emptyList());
        }

        if (binding.postsRecyclerView.getAdapter() == null) {
            binding.postsRecyclerView.setAdapter(adapter);
            showLoadingPullToRefresh(true);
        }
    }

    @Override
    public void load(List<PostItemViewModel> items) {
        adapter.setItems(items);
    }

    @Override
    public void toPost(PostItemViewModel item) {
        TemplateFragment fragment = TemplateFragment.newInstance("Post Details");
        navigationFragment.pushFragment(fragment);
    }
}
