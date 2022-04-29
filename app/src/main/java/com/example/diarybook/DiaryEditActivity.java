package com.example.diarybook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;

import com.example.diarybook.presenter.DiaryEditPresenter;
import com.example.diarybook.utils.ActivityUtils;

public class DiaryEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit);

        String diaryId = getIntent().getStringExtra(DiaryEditFragment.DIARY_ID);
        initToolBar(diaryId);
        initFragment(diaryId);
    }

    private void initFragment(String diaryId) {
        DiaryEditFragment diaryEditFragment = getDiaryEditFragment();
        if (diaryEditFragment == null) {
            diaryEditFragment = initEditDiaryFragment(diaryId);
        }
//        DiaryEditPresenter diaryEditPresenter = new DiaryEditPresenter(diaryId, diaryEditFragment);
        diaryEditFragment.setPresenter(new DiaryEditPresenter(diaryId, diaryEditFragment));
    }

    private DiaryEditFragment initEditDiaryFragment(String diaryId) {
        DiaryEditFragment diaryEditFragment = new DiaryEditFragment();
        if (getIntent().hasExtra(DiaryEditFragment.DIARY_ID)) {
            Bundle bundle = new Bundle();
            bundle.putString(DiaryEditFragment.DIARY_ID, diaryId);
            diaryEditFragment.setArguments(bundle);
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), diaryEditFragment, R.id.detail_content);
        return diaryEditFragment;
    }

    private DiaryEditFragment getDiaryEditFragment() {

        return (DiaryEditFragment) getSupportFragmentManager().findFragmentById(R.id.detail_content);
    }

    private void initToolBar(String diaryId) {
        Toolbar toolbar = findViewById(R.id.edit_toolbar);
        setSupportActionBar(toolbar);
        setToolbarTitle(TextUtils.isEmpty(diaryId));
    }

    private void setToolbarTitle(boolean isAdd) {
        if (isAdd) {
            getSupportActionBar().setTitle(R.string.add);
        } else {
            getSupportActionBar().setTitle(R.string.edit);
        }
    }

}