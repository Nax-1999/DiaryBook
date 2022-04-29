package com.example.diarybook.model.datasource;

import androidx.annotation.NonNull;

import com.example.diarybook.DataCallback;
import com.example.diarybook.MockDiaries;
import com.example.diarybook.model.enity.Diary;
import com.example.diarybook.utils.CollectionUtils;
import com.example.diarybook.utils.GsonUtils;
import com.example.diarybook.utils.SharedPreferencesUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 本地数据处理层，直接操作sp
 */
public class DiariesLocalDataSource implements DataSource<Diary> {

    private static volatile DiariesLocalDataSource mInstance;

    private static final String DIARY_DATA = "diary_data";
    private static final String ALL_DIARY = "all_diary";

    private static Map<String, Diary> LOCAL_DATA = new LinkedHashMap<>();

    private static SharedPreferencesUtils mSpUtils;

    private DiariesLocalDataSource() {
        mSpUtils = SharedPreferencesUtils.getInstance(DIARY_DATA);
        //从sp读取全部日记json
        String diaryStr = mSpUtils.get(ALL_DIARY);
        //将json解析全部日记map
        LOCAL_DATA = json2Obj(diaryStr);
        LinkedHashMap<String, Diary> diariesObj = json2Obj(diaryStr);
        if (!CollectionUtils.isEmpty(diariesObj)) {
            LOCAL_DATA = diariesObj;
        } else {
            LOCAL_DATA = MockDiaries.mock();
        }
    }

    public static DiariesLocalDataSource get() {
        if (mInstance == null) {
            synchronized (DiariesLocalDataSource.class) {
                if (mInstance == null) {
                    mInstance = new DiariesLocalDataSource();
                }
            }
        }
        return mInstance;
    }

    /**
     * 将全部日记map转换成json
     */
    private String obj2Json() {
        return GsonUtils.toJson(LOCAL_DATA);
    }

    /**
     * 从json解析全部日记信息
     * @param diaryStr
     * @return
     */
    private LinkedHashMap<String, Diary> json2Obj(String diaryStr) {
        return GsonUtils.fromJson(diaryStr, new TypeToken<LinkedHashMap<String, Diary>>() {}.getType());
    }

    @Override
    public void getAll(@NonNull DataCallback<List<Diary>> callBack) {
        if (LOCAL_DATA.isEmpty()) {
            callBack.onError();
        } else {
            callBack.onSuccess(new ArrayList<>(LOCAL_DATA.values()));
        }
    }

    @Override
    public void get(@NonNull String id, @NonNull DataCallback<Diary> callBack) {
        Diary diary = LOCAL_DATA.get(id);
        if (diary == null) {
            callBack.onError();
        } else {
            callBack.onSuccess(diary);
        }
    }

    @Override
    public void update(@NonNull Diary diary) {
        LOCAL_DATA.put(diary.getId(), diary);
        mSpUtils.put(ALL_DIARY, obj2Json());
    }

    @Override
    public void clear() {
        LOCAL_DATA.clear();
        mSpUtils.remove(ALL_DIARY);
    }

    @Override
    public void delete(@NonNull String id) {
        LOCAL_DATA.remove(id);
        mSpUtils.put(ALL_DIARY, obj2Json());
    }

}
