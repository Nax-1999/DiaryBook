package com.example.diarybook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diarybook.contract.DiaryEditContract;


public class DiaryEditFragment extends Fragment implements DiaryEditContract.View {
    public final static String DIARY_ID = "";

    private DiaryEditContract.Presenter mPresenter;

    private TextView mTitle;
    private TextView mDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diary_edit, container, false);
        mTitle = root.findViewById(R.id.edit_title);
        mDescription = root.findViewById(R.id.edit_description);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_done, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_done:
                mPresenter.saveDiary(mTitle.getText().toString(), mDescription.getText().toString());
                return true;
        }
        return false;
    }


    @Override
    public void showError() {
        showMessage(getString(R.string.error));
    }

    @Override
    public void showDiariesList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mPresenter.onResult(requestCode, resultCode);
    }

    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    public void showSuccess() {
        showMessage(getString(R.string.success));
    }

    @Override
    public void setPresenter(@NonNull DiaryEditContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}