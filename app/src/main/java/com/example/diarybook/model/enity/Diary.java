package com.example.diarybook.model.enity;

import androidx.annotation.Nullable;

import java.util.UUID;

public class Diary {
    private String id;
    private String title;
    private String description;

    public Diary(@Nullable String title, @Nullable String description) {
        this(title, description, UUID.randomUUID().toString());
    }

    public Diary(@Nullable String title, @Nullable String description, @Nullable String id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
