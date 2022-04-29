package com.example.diarybook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.diarybook.presenter.DiariesPresenter;
import com.example.diarybook.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initFragment();
    }

    private void initFragment() {
        DiariesFragment diariesFragment = getDiariesFragment();
        if (diariesFragment == null) {
            diariesFragment = new DiariesFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), diariesFragment, R.id.content);
        }
        diariesFragment.setPresenter(new DiariesPresenter(diariesFragment));
    }

    private DiariesFragment getDiariesFragment() {
        return (DiariesFragment) getSupportFragmentManager().findFragmentById(R.id.content);
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}