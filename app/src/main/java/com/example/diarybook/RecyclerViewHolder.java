package com.example.diarybook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    private T mData;

    public RecyclerViewHolder(ViewGroup parent, @LayoutRes int resource) {
        super(LayoutInflater.from(parent.getContext()).inflate(resource, parent, false));
    }

    @CallSuper
    public void onBindView(T data) {
        mData = data;
    }
    public T getData() {
        return mData;
    }

}
