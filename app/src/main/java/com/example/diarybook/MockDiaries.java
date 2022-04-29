package com.example.diarybook;

import androidx.annotation.NonNull;

import com.example.diarybook.model.enity.Diary;

import java.util.LinkedHashMap;
import java.util.Map;

public class MockDiaries {
    private static final String DESCRIPTION = "架构模式yyds架构模式yyds架构模式yyds架构模式yyds架构模式yyds架构模式yyds架构模式yyds架构模式yyds架构模式yyds架构模式yyds架构模式yyds架构模式yyds架构模式yyds架构模式yyds";

    public static Map<String, Diary> mock() {
        return Mock(new LinkedHashMap<String, Diary>());
    }

    /**
     * 初始化默认日记数据并以map返回
     * @param date
     * @return
     */
    private static Map<String, Diary> Mock(Map<String, Diary> date) {
        Diary test1 = getDiary("2022-01-01 上班");
        date.put(test1.getId(), test1);
        Diary test2 = getDiary("2022-01-02 上班");
        date.put(test2.getId(), test2);
        Diary test3 = getDiary("2022-01-03 上班");
        date.put(test3.getId(), test3);
        Diary test4 = getDiary("2022-01-04 上班");
        date.put(test4.getId(), test4);
        Diary test5 = getDiary("2022-01-05 上班");
        date.put(test5.getId(), test5);
        Diary test6 = getDiary("2022-01-06 上班");
        date.put(test6.getId(), test6);
        Diary test7 = getDiary("2022-01-07 上班");
        date.put(test7.getId(), test7);
        Diary test8 = getDiary("2022-01-08 上班");
        date.put(test8.getId(), test8);
        Diary test9 = getDiary("2022-01-09 上班");
        date.put(test8.getId(), test9);
        Diary test10 = getDiary("2022-01-10 上班");
        date.put(test8.getId(), test10);
        return date;
    }

    @NonNull
    /**
     * 根据标题创建日记实体
     */
    private static Diary getDiary(String title) {
        return new Diary(title, DESCRIPTION);
    }


}
