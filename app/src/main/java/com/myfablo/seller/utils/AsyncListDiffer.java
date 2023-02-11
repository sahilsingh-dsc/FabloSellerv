package com.myfablo.seller.utils;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AsyncListDiffer<T> {

    private final RecyclerView.Adapter adapter;
    private List<T> items;
    private final DiffUtil.ItemCallback<T> diffCallback;

    public AsyncListDiffer(RecyclerView.Adapter adapter, DiffUtil.ItemCallback<T> diffCallback) {
        this.adapter = adapter;
        this.diffCallback = diffCallback;
    }

    public void submitList(final List<T> newItems) {
        if (items == null) {
            items = newItems;
            adapter.notifyDataSetChanged();
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return items.size();
                }

                @Override
                public int getNewListSize() {
                    return newItems.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return diffCallback.areItemsTheSame(
                            items.get(oldItemPosition), newItems.get(newItemPosition));
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return diffCallback.areContentsTheSame(
                            items.get(oldItemPosition), newItems.get(newItemPosition));
                }
            });
            items = newItems;
            result.dispatchUpdatesTo(adapter);
        }
    }

    public List<T> getCurrentList() {
        return items;
    }
}