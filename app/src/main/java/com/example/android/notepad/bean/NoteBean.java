package com.example.android.notepad.bean;

import android.database.Cursor;



public class NoteBean {
    private String Title;//笔记的标题
    private String createTime;//笔记的创建时间
    private String Cursor_id;//所属的游标的position

    public NoteBean(String title, String createTime) {
        Title = title;
        this.createTime = createTime;
    }

    public NoteBean(String title, String createTime, String cursor_id) {
        Title = title;
        this.createTime = createTime;
        Cursor_id = cursor_id;
    }

    public NoteBean(String title, String createTime, String cursor_id, Cursor cursor) {
        Title = title;
        this.createTime = createTime;
        Cursor_id = cursor_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCursor_id() {
        return Cursor_id;
    }

    public void setCursor_id(String cursor_id) {
        Cursor_id = cursor_id;
    }


}
