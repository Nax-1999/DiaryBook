package com.example.diarybook;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diarybook.adapter.DiariesAdapter;
import com.example.diarybook.contract.DiariesContract;

public class DiariesFragment extends Fragment implements DiariesContract.View {


    private DiariesContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diaries, container, false);
        this.mRecyclerView = root.findViewById(R.id.diaries_list);
        initDiariesList();
        setHasOptionsMenu(true);
        return root;
    }

    /**
     * 配置日记recyclerView
     */
    public void initDiariesList() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_write, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                mPresenter.addDiary();
                return true;
        }
        return false;
    }


    @Override
    public void setPresenter(@NonNull DiariesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }


    @Override
    public void gotoUpdateDiary(String diaryId) {
        Intent intent = new Intent(getContext(), DiaryEditActivity.class);
        intent.putExtra(DiaryEditFragment.DIARY_ID, diaryId);
        startActivity(intent);
    }


    @Override
    public void setListAdapter(DiariesAdapter mListAdapter) {
        mRecyclerView.setAdapter(mListAdapter);
    }

    
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void gotoWriteDiary() {
        Intent intent = new Intent(getContext(), DiaryEditActivity.class);
        startActivity(intent);
    }


    @Override
    public void showSuccess() {
        showMessage(getString(R.string.success));
    }

    @Override
    public void showError() {
        showMessage(getString(R.string.error));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


}