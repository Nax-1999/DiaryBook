package com.example.diarybook.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.example.diarybook.DataCallback;
import com.example.diarybook.contract.DiaryEditContract;
import com.example.diarybook.DiaryEditFragment;
import com.example.diarybook.model.datasource.DataSource;
import com.example.diarybook.model.enity.Diary;
import com.example.diarybook.model.repository.DiariesRepository;

public class DiaryEditPresenter implements DiaryEditContract.Presenter {

    private final DataSource<Diary> mDiariesRepository;
    private final DiaryEditFragment mView;
    private String mDiaryId;

    public DiaryEditPresenter(String mDiaryId, DiaryEditFragment mView) {
        this.mView = mView;
        this.mDiaryId = mDiaryId;
        mDiariesRepository = DiariesRepository.getInstance();
    }

    @Override
    public void start() {
        requestDiary();
    }



    private boolean isAddDiary() {
        return TextUtils.isEmpty(mDiaryId);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onResult(int requestCode, int resultCode) {
        if (Activity.RESULT_OK != resultCode) {
            return;
        }
        mView.showSuccess();
    }


    @Override
    public void saveDiary(String title, String description) {
        if (TextUtils.isEmpty(mDiaryId)) {
            createDiary(title, description);
        } else {
            updateDiary(title, description);
        }
    }

    @Override
    public void requestDiary() {
        if (isAddDiary()) {
            return;
        }
        mDiariesRepository.get(mDiaryId, new DataCallback<Diary>() {
            @Override
            public void onSuccess(Diary diary) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setTitle(diary.getTitle());
                mView.setDescription(diary.getDescription());
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

    private void updateDiary(String title, String description) {
        Diary diary = new Diary(title, description, mDiaryId);
        mDiariesRepository.update(diary);
        mView.showDiariesList();
    }

    private void createDiary(String title, String description) {
        Diary newDiary = new Diary(title, description);
        mDiariesRepository.update(newDiary);
        mView.showDiariesList();
    }


}
