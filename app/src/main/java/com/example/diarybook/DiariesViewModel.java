package com.example.diarybook;

import android.app.Activity;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.example.diarybook.DataCallback;
import com.example.diarybook.DiariesFragment;
import com.example.diarybook.EnApplication;
import com.example.diarybook.MainActivity;
import com.example.diarybook.R;
import com.example.diarybook.adapter.DiariesAdapter;
import com.example.diarybook.databinding.FragmentDiariesBinding;
import com.example.diarybook.model.enity.Diary;
import com.example.diarybook.model.repository.DiariesRepository;

import java.util.ArrayList;
import java.util.List;

public class DiariesViewModel extends BaseObservable {

    public final ObservableList<Diary> data = new ObservableArrayList<>();

    public final ObservableField<String> toastInfo = new ObservableField<>();
    public final ObservableField<DiariesAdapter> listAdapter = new ObservableField<>();

//    private final DiariesFragment mView;
    private final MainActivity mView;
    private final DiariesRepository mDiariesRepository;

//    public DiariesViewModel(DiariesFragment mView) {
//        this.mView = mView;
//        mDiariesRepository = DiariesRepository.getInstance();
//    }

    public DiariesViewModel(MainActivity mView) {
        this.mView = mView;
        mDiariesRepository = DiariesRepository.getInstance();
    }

    public void start() {
        initAdapter();
        loadDiaries();
    }

    /**
     * 从本地读取数据
     */
    public void loadDiaries() {
        mDiariesRepository.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onSuccess(List<Diary> diaryList) {
                updateDiaries(diaryList);
            }

            @Override
            public void onError() {
                toastInfo.set(EnApplication.get().getString(R.string.error));
            }
        });
    }


    private void updateDiaries(List<Diary> diaryList) {
        data.clear();
        data.addAll(diaryList);
    }

    private void initAdapter() {
        DiariesAdapter diariesAdapter = new DiariesAdapter();
        diariesAdapter.setOnLongClickListener(new DiariesAdapter.OnLongClickListener<Diary>() {
            @Override
            public boolean onLongClick(View v, Diary data) {
//                mView.gotoUpdateDiary(data.getId());
                return false;
            }
        });
        listAdapter.set(diariesAdapter);
    }


}
