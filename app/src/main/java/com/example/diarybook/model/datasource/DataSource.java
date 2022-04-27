package com.example.diarybook.model.datasource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.diarybook.DataCallback;

import java.util.List;

public interface DataSource<T> {

    void getAll(@NonNull DataCallback<List<T>> callBack);

    void get(@NonNull String id, @NonNull DataCallback<T> callBack);

    void update(@NonNull T diary);

    void clear();

    void delete(@NonNull String id);
}
