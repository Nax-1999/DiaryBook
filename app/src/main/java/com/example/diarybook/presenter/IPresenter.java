package com.example.diarybook.presenter;

import com.example.diarybook.model.enity.Diary;

public interface IPresenter extends BasePresenter{
    void loadDiaries();
    void addDiary();
    void updateDiary(Diary diary);
    void onInputDialog(String desc);
}
