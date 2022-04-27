package com.example.diarybook;

public interface DataCallback<T> {
    void onSuccess(T data);

    void onError();
}
