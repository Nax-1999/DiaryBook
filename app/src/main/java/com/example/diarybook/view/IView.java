package com.example.diarybook.view;

import com.example.diarybook.adapter.DiariesAdapter;
import com.example.diarybook.presenter.IPresenter;

public interface IView extends BaseView<IPresenter>{
    void gotoWriteDiary();
    void showInputDialog(final String title, final String desc);
    void showSuccess();
    void showError();
    boolean isActive();
    void setListAdapter(DiariesAdapter mListAdapter);
}
