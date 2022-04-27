package com.example.diarybook;

import android.app.Application;
import android.content.Context;

public class EnApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context get() {
        return context;
    }
}
