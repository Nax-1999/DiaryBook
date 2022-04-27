package com.example.diarybook;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DiariesAdapter extends RecyclerView.Adapter<DiariesAdapter.DiaryHolder> {

    private List<Diary> mDiaries;
    private OnLongClickListener<Diary> mOnLongClickListener;

    public DiariesAdapter(List<Diary> diaries) {
        mDiaries = diaries;
    }

    public void update(List<Diary> diaries) {
        mDiaries = diaries;
        notifyDataSetChanged();
    }

    public void setOnLongClickListener(OnLongClickListener<Diary> mOnLongClickListener) {
        this.mOnLongClickListener = mOnLongClickListener;
    }

    public interface OnLongClickListener<T> {
        boolean onLongClick(View v, T data);
    }

    @NonNull
    @Override
    public DiaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiaryHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryHolder holder, int position) {
        final Diary diary = mDiaries.get(position);
        holder.onBindView(diary);
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mOnLongClickListener != null && mOnLongClickListener.onLongClick(v, diary);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDiaries.size();
    }

    public class DiaryHolder extends RecyclerViewHolder<Diary>{

        private View.OnLongClickListener mOnLongClickListener;

        public DiaryHolder(ViewGroup parent) {
            super(parent, R.layout.list_diary_item);
        }

        @Override
        public void onBindView(Diary diary) {
            super.onBindView(diary);
            TextView title = itemView.findViewById(R.id.title);
            TextView desc = itemView.findViewById(R.id.desc);
            title.setText(diary.getTitle());
            desc.setText(diary.getDescription());
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnLongClickListener != null && mOnLongClickListener.onLongClick(v);
                }
            });
        }

        public void setOnLongClickListener(View.OnLongClickListener mOnLongClickListener) {
            this.mOnLongClickListener = mOnLongClickListener;
        }
    }

}
