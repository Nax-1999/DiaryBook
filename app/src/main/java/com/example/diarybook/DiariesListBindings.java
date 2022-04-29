package com.example.diarybook;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diarybook.adapter.DiariesAdapter;
import com.example.diarybook.model.enity.Diary;

import java.util.List;

public class DiariesListBindings {

    @SuppressWarnings("unchecked")
    @BindingAdapter("data")
    public static void setData(RecyclerView recyclerView, List<Diary> data) {
        DiariesAdapter adapter = (DiariesAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            return;
        }
        adapter.update(data);
    }

}
