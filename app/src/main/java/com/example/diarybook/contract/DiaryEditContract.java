package com.example.diarybook.contract;

import com.example.diarybook.adapter.DiariesAdapter;
import com.example.diarybook.model.enity.Diary;
import com.example.diarybook.presenter.BasePresenter;
import com.example.diarybook.view.BaseView;

public interface DiaryEditContract {
    interface View extends BaseView<Presenter> {
        void showError();
        void showDiariesList();
        void setTitle(String title);
        void setDescription(String description);
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void saveDiary(String title, String description);
        void requestDiary();
    }
}
