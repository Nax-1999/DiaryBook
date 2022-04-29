package com.example.diarybook.model.repository;

import androidx.annotation.NonNull;

import com.example.diarybook.DataCallback;
import com.example.diarybook.model.datasource.DataSource;
import com.example.diarybook.model.datasource.DiariesLocalDataSource;
import com.example.diarybook.model.enity.Diary;
import com.example.diarybook.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据仓库类，被controller持有，根据需要从cache或DataSource读取
 */
public class DiariesRepository implements DataSource<Diary> {

    private static volatile DiariesRepository mInstance;//数据仓库实例
    private final DataSource<Diary> mLocalDataSource;//本地数据源
    private Map<String, Diary> mMemoryCache;

    public DiariesRepository() {
        mMemoryCache = new LinkedHashMap<>();
        mLocalDataSource = DiariesLocalDataSource.get();
    }

    public static DiariesRepository getInstance() {
        if (mInstance == null) {
            synchronized (DiariesRepository.class) {
                if (mInstance == null) {
                    mInstance = new DiariesRepository();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getAll(@NonNull DataCallback<List<Diary>> callBack) {
        if (!CollectionUtils.isEmpty(mMemoryCache)) {
            callBack.onSuccess(new ArrayList<>(mMemoryCache.values()));
            return;
        }
        mLocalDataSource.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onSuccess(List<Diary> diaries) {
                updateMemoryCache(diaries);
                callBack.onSuccess(new ArrayList<>(mMemoryCache.values()));
            }

            @Override
            public void onError() {
                callBack.onError();
            }
        });
    }

    private void updateMemoryCache(List<Diary> diaries) {
        mMemoryCache.clear();
        for (Diary diary : diaries) {
            mMemoryCache.put(diary.getId(), diary);
        }
    }

    @Override
    public void get(@NonNull String id, @NonNull DataCallback<Diary> callBack) {
        Diary cacheDiary = getDiaryByIdFromMemory(id);
        if (cacheDiary != null) {
            callBack.onSuccess(cacheDiary);
            return;
        }
        mLocalDataSource.get(id, new DataCallback<Diary>() {
            @Override
            public void onSuccess(Diary diary) {
                mMemoryCache.put(diary.getId(), diary);
                callBack.onSuccess(diary);
            }

            @Override
            public void onError() {
                callBack.onError();
            }
        });
    }

    private Diary getDiaryByIdFromMemory(String id) {
        if (CollectionUtils.isEmpty(mMemoryCache)) {
            return null;
        } else {
            return mMemoryCache.get(id);
        }
    }

    @Override
    public void update(@NonNull Diary diary) {
        mLocalDataSource.update(diary);
        mMemoryCache.put(diary.getId(), diary);
    }

    @Override
    public void clear() {
        mLocalDataSource.clear();
        mMemoryCache.clear();
    }

    @Override
    public void delete(@NonNull String id) {
        mLocalDataSource.delete(id);
        mMemoryCache.remove(id);
    }
}
