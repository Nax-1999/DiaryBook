package com.example.diarybook.view;

import androidx.annotation.NonNull;

import com.example.diarybook.presenter.BasePresenter;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(@NonNull T presenter);

}
