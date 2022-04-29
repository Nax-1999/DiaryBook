package com.example.diarybook.presenter;

public interface BasePresenter {
    void start();
    void destroy();

    void onResult(int requestCode, int resultCode);
}
