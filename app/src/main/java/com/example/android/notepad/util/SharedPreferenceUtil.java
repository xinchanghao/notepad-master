package com.example.android.notepad.util;

/**
 * Created by TALH on 2017/5/10.
 */
import android.content.Context;
import android.content.SharedPreferences;
import com.example.android.notepad.application.MyApplication;

/**
 * Created by qinghua on 2017/3/17.
 * 轻量级存储工具类
 */

public class SharedPreferenceUtil {

    private static String FILENAME = "Config";

    /**
     * CommitDate该方法是一个有返回值的同步的提交方式，true表示数据保存成功，false表示数据保存失败
     */
    public static boolean CommitDate(String key, String date) {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, date);
        return editor.commit();
    }

    /**
     * ApplyDate:是一个异步操作的保存数据的方法
     */
    public static void ApplyDate(String key, String date) {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, date);
        editor.apply();
    }

    /*
    *获取数据
     */
    public static String getDate(String key) {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        String str = sp.getString(key, "");
        if (!str.isEmpty()) {
            return str;
        } else {
            return null;
        }
    }
}