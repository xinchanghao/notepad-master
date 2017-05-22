# NotePad
This is an AndroidStudio rebuild of google SDK sample NotePad
一 功能扩展如下：

 1.增加时间戳显示
 2.添加笔记查询功能
 3.界面UI美化
 4.更改记事本的背景
 
二 主界面增加时间戳：
 
![主界面](https://github.com/xinchanghao/notepad-master/blob/master/app/src/main/res/drawable/1.png)  
在 NoteEditor.java文件中的updateNote中添加

  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
  values.put(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, df.format(new Date()));
 
三、点击+,新建note界面，UI美化：
 
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"

        >
        <EditText
            android:layout_width="match_parent"
            android:layout_height="40dp"
            android:layout_weight="1"
            android:layout_marginLeft="10dp"
            android:textColor="#000000"
            android:id="@+id/et_Search"
            android:hint="输入标题查找"
            android:textCursorDrawable="@drawable/contact_edit_edittext_normal"
            />
        <ImageView
            android:layout_width="30dp"
            android:layout_height="30dp"
            android:background="@drawable/search1"
            android:layout_marginRight="10dp"
            android:layout_marginTop="5dp"
            android:layout_marginLeft="10dp"
            android:id="@+id/iv_searchnotes"
            />

    </LinearLayout>

    <FrameLayout
    android:layout_width="match_parent"
    android:layout_weight="1"
    android:orientation="vertical"
    android:layout_height="wrap_content">
    <ListView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:cacheColorHint="#00000000"
        android:divider="#FFFFCC"
        android:dividerHeight="0dp"
        android:id="@id/android:list"

        >

    </ListView>
        <ImageButton
            android:id="@+id/fab"
            android:layout_width="60dp"
            android:layout_height="60dp"
            android:layout_gravity="bottom|center"
            android:layout_margin="45dp"
            android:background="@drawable/tianjia"
            android:backgroundTintMode="add"/>

![新建note界面](https://github.com/xinchanghao/notepad-master/blob/master/app/src/main/res/drawable/2.png)  

 
四、更换主题界面：
 
    public void ColorSelect(View view){
        String color;
        switch(view.getId()){
            case R.id.pink:
                Drawable btnDrawable1 = getResources().getDrawable(R.drawable.pink);
                ll_noteList.setBackgroundDrawable(btnDrawable1);
                lv_notesList.setBackgroundDrawable(btnDrawable1);
                break;
            case R.id.Yello:
                Drawable btnDrawable2 = getResources().getDrawable(R.drawable.yellow);
                ll_noteList.setBackgroundDrawable(btnDrawable2);
                lv_notesList.setBackgroundDrawable(btnDrawable2);
                break;
            case R.id.PaleVioletRed:
                Drawable btnDrawable3 = getResources().getDrawable(R.drawable.palevioletred);
                ll_noteList.setBackgroundDrawable(btnDrawable3);
                lv_notesList.setBackgroundDrawable(btnDrawable3);
                break;
            case R.id.LightGrey:
                Drawable btnDrawable4 = getResources().getDrawable(R.drawable.lightgrey);
                ll_noteList.setBackgroundDrawable(btnDrawable4);
                lv_notesList.setBackgroundDrawable(btnDrawable4);
                break;
            case R.id.MediumPurple:
                Drawable btnDrawable5 = getResources().getDrawable(R.drawable.mediumpurple);
                ll_noteList.setBackgroundDrawable(btnDrawable5);
                lv_notesList.setBackgroundDrawable(btnDrawable5);
                break;
            case R.id.DarkGray:
                Drawable btnDrawable6 = getResources().getDrawable(R.drawable.darkgray);
                ll_noteList.setBackgroundDrawable(btnDrawable6);
                lv_notesList.setBackgroundDrawable(btnDrawable6);
                break;
            case R.id.Snow:
                Drawable btnDrawable7 = getResources().getDrawable(R.drawable.snow);
                ll_noteList.setBackgroundDrawable(btnDrawable7);
                lv_notesList.setBackgroundDrawable(btnDrawable7);
                break;
        }

    }

![更换主题界面](https://github.com/xinchanghao/notepad-master/blob/master/app/src/main/res/drawable/3.png)  

![主题界面1](https://github.com/xinchanghao/notepad-master/blob/master/app/src/main/res/drawable/4.png)  

![主题界面2](https://github.com/xinchanghao/notepad-master/blob/master/app/src/main/res/drawable/5.png)  

![主题界面3](https://github.com/xinchanghao/notepad-master/blob/master/app/src/main/res/drawable/6.png) 



 
五、搜索笔记：
 
使用TextWatcher实现textview输入监听，使用数据库语句like实现模糊查找

private void addSearchView() {
            //给listview添加头部(search)
            View v=View.inflate(this, R.layout.notelistheader,null);
            getListView().addHeaderView(v);
            //给搜索框添加搜索功能
            final EditText et_Search=(EditText)v.findViewById(R.id.et_search);
            et_Search.addTextChangedListener(new TextWatcherForSearch(){
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    super.onTextChanged(charSequence, i, i1, i2);
                    if (charSequence.length()!=0 && et_Search.getText().toString().length()!=0){
                    String str_Search = et_Search.getText().toString();
                    Cursor search_cursor = managedQuery(
                                getIntent().getData(),            // Use the default content URI for the provider.
                                PROJECTION,                       // Return the note ID and title for each note.
                                NotePad.Notes.COLUMN_NAME_TITLE+" like ?",                             // No where clause, return all records.
                                new String[]{"%"+str_Search+"%"}, //匹配字符串条件                            // No where clause, therefore no where column values.
                        NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
                );
                adapter.swapCursor(search_cursor);//刷新listview

            }else {
                if (cursor!=null)//删除搜索框中的text后刷新listview
                adapter.swapCursor(cursor);//刷新listview
            }
        }
    });
}

 
![搜索界面](https://github.com/xinchanghao/notepad-master/blob/master/app/src/main/res/drawable/7.png)  

