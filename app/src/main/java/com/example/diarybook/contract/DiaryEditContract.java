package com.example.diarybook.contract;

import com.example.diarybook.adapter.DiariesAdapter;
import com.example.diarybook.model.enity.Diary;
import com.example.diarybook.presenter.BasePresenter;
import com.example.diarybook.view.BaseView;

public interface DiaryEditContract {
    interface View extends BaseView<Presenter> {
        void showDiariesList();//返回日记列表页面
        void setTitle(String title);
        void setDescription(String description);
        boolean isActive();//当前fragment是否添加到了activity上
        void showError();
    }

    interface Presenter extends BasePresenter {
        void saveDiary(String title, String description);//保存编辑的数据
        void requestDiary();//获取日记编辑页面的日记数据
    }
}
