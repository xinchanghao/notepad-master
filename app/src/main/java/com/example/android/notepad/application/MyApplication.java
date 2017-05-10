package com.example.android.notepad.application;

/**
 * Created by TALH on 2017/5/10.
 */
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.android.notepad.util.SharedPreferenceUtil;

/**
 * Created by qinghua on 2017/5/8.
 */

public class MyApplication extends Application {

    private static Context context;
    private static String background="#ffffff";//背景颜色的十六进制值,默认为白色

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        readBackground();
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MyApplication.context = context;
    }

    public static String getBackground() {
        return background;
    }

    public static void setBackground(String background) {
        MyApplication.background = background;
    }

    /*
        读取配置文件中的背景颜色
         */
    public static void readBackground(){
        if(SharedPreferenceUtil.getDate("background")==null||SharedPreferenceUtil.getDate("background").equals("")){

        }
        else{
            background= SharedPreferenceUtil.getDate("background");
        }

    }

    /*
   读取配置文件中的背景颜色
  */
    public static void saveBackground(){
        SharedPreferenceUtil.CommitDate("background",background);
    }
}