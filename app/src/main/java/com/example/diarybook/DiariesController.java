package com.example.diarybook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DiariesController {
    DiariesAdapter mListAdapter;
    DiariesRepository mDiariesRepository;
    Fragment mView;

    public DiariesController(DiariesFragment diariesFragment) {
        mDiariesRepository = DiariesRepository.getInstance();
        mView = diariesFragment;
        mView.setHasOptionsMenu(true);
        initAdapter();
    }

    private void initAdapter() {
        mListAdapter = new DiariesAdapter(new ArrayList<Diary>());
        mListAdapter.setOnLongClickListener(new DiariesAdapter.OnLongClickListener<Diary>() {
            @Override
            public boolean onLongClick(View v, Diary data) {
                showInputDialog(data);
                return false;
            }
        });
    }

    private void showInputDialog(Diary data) {
        final EditText editText = new EditText(mView.getContext());
        editText.setText(data.getDescription());
        new AlertDialog.Builder(mView.getContext()).setTitle(data.getTitle())
                .setView(editText)
                .setPositiveButton(EnApplication.get().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.setDescription(editText.getText().toString());
                        mDiariesRepository.update(data);
                        loadDiaries();
                    }
                })
                .setNegativeButton(EnApplication.get().getString(R.string.cancel), null)
                .show();
    }

    public void setDiariesList(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        recyclerView.setAdapter(mListAdapter);
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(mView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void loadDiaries() {
        mDiariesRepository.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onSuccess(List<Diary> diaryList) {
                processDiaries(diaryList);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    private void showError() {
        showMessage(mView.getString(R.string.error));
    }

    private void showMessage(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void processDiaries(List<Diary> diaryList) {
        mListAdapter.update(diaryList);
    }

    public void gotoWriteDiary() {
        showMessage(mView.getString(R.string.developing));
    }
}
