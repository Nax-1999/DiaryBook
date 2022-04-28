package com.example.diarybook.contract;

import com.example.diarybook.adapter.DiariesAdapter;
import com.example.diarybook.model.enity.Diary;
import com.example.diarybook.presenter.BasePresenter;
import com.example.diarybook.view.BaseView;

public interface DiariesContract {
    interface View extends BaseView<Presenter> {
        void gotoWriteDiary();
        void showInputDialog(final String title, final String desc);
        void showSuccess();
        void showError();
        boolean isActive();
        void setListAdapter(DiariesAdapter mListAdapter);

        void gotoUpdateDiary(String diaryId);
    }

    interface Presenter extends BasePresenter {
        void loadDiaries();
        void addDiary();
        void updateDiary(Diary diary);
        void onInputDialog(String desc);
    }
}
