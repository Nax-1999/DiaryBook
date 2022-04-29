package com.example.diarybook.presenter;

import android.view.View;

import com.example.diarybook.DataCallback;
import com.example.diarybook.contract.DiariesContract;
import com.example.diarybook.adapter.DiariesAdapter;
import com.example.diarybook.model.enity.Diary;
import com.example.diarybook.model.repository.DiariesRepository;

import java.util.ArrayList;
import java.util.List;

public class DiariesPresenter implements DiariesContract.Presenter {

    private final DiariesContract.View mView;
    private DiariesAdapter mListAdapter;
    private final DiariesRepository mDiariesRepository;

    public DiariesPresenter(DiariesContract.View mView) {
        this.mView = mView;
        mDiariesRepository = DiariesRepository.getInstance();
    }

    @Override
    public void start() {
        initAdapter();
        loadDiaries();
    }

    @Override
    public void destroy() {

    }


    @Override
    /**
     * 从本地读取数据
     */
    public void loadDiaries() {
        mDiariesRepository.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onSuccess(List<Diary> diaryList) {
                if (!mView.isActive()) {
                    return;
                }
                updateDiaries(diaryList);
            }

            @Override
            public void onError() {
                if (!mView.isActive()) {
                    return;
                }
                mView.showError();
            }
        });
    }

    @Override
    public void addDiary() {
        mView.gotoWriteDiary();
    }

    private void updateDiaries(List<Diary> diaryList) {
        mListAdapter.update(diaryList);
    }

    private void initAdapter() {
        mListAdapter = new DiariesAdapter(new ArrayList<Diary>());
        mListAdapter.setOnLongClickListener(new DiariesAdapter.OnLongClickListener<Diary>() {
            @Override
            public boolean onLongClick(View v, Diary data) {
                mView.gotoUpdateDiary(data.getId());
                return false;
            }
        });
        mView.setListAdapter(mListAdapter);
    }


}
