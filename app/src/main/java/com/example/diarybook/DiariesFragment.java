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
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.diarybook.adapter.DiariesAdapter;
import com.example.diarybook.databinding.FragmentDiariesBinding;

public class DiariesFragment extends Fragment {

    private DiariesViewModel mViewModel;
//    private RecyclerView mRecyclerView;

    private FragmentDiariesBinding mDiariesBinding;

    //fixme databiding怎么把这个Fragment和layout文件绑定起来的呢？

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDiariesBinding = FragmentDiariesBinding.inflate(inflater, container, false);
        mDiariesBinding.setViewModel(mViewModel);
        mDiariesBinding.setLayoutManager(new LinearLayoutManager(getContext()));
        initDiariesList();
        setHasOptionsMenu(true);
//        mViewModel.start();
        return mDiariesBinding.getRoot();
    }

    public void setViewModel(DiariesViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    /**
     * 配置日记recyclerView
     */
    public void initDiariesList() {
        //添加分割线
        mDiariesBinding.diariesList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mDiariesBinding.diariesList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
        initToast();
    }

    private void initToast() {
        mViewModel.toastInfo.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                showMessage(mViewModel.toastInfo.get());
            }
        });
    }

    private void initRecyclerView() {
        mViewModel.listAdapter.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                setListAdapter(mViewModel.listAdapter.get());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_write, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                return true;
        }
        return false;
    }


    public void gotoUpdateDiary(String diaryId) {
        Intent intent = new Intent(getContext(), DiaryEditActivity.class);
        intent.putExtra(DiaryEditFragment.DIARY_ID, diaryId);
        //此处不能使用书中的startActivity方法，否则返回第一个activity时不会执行onActivityResult
        startActivityForResult(intent, 1);
    }


    public void setListAdapter(DiariesAdapter mListAdapter) {
//        mRecyclerView.setAdapter(mListAdapter);
        mViewModel.listAdapter.set(mListAdapter);
    }


    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    public void gotoWriteDiary() {
        Intent intent = new Intent(getContext(), DiaryEditActivity.class);
        startActivityForResult(intent, 2);
    }


    public void showSuccess() {
        showMessage(getString(R.string.success));
    }

    public void showError() {
        showMessage(getString(R.string.error));
    }

    public boolean isActive() {
        return isAdded();
    }



}