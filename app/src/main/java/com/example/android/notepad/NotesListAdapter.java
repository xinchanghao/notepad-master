package com.example.android.notepad;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.notepad.bean.NoteBean;
import com.example.android.notepad.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import com.example.android.notepad.application.MyApplication;



/**
 * Created by qinghua on 2017/4/30.
 */

public class NotesListAdapter extends BaseAdapter {

    private Uri mUri;
    private String actionName;
    private Context context;
    public  Cursor cursor;
    private LayoutInflater inflater;
    private List<NoteBean> mDate;
    private List<NoteBean> searchData;//被搜所到数据
    private String background;


    private static final int COLUMN_INDEX_ID= 0;
    private static final int COLUMN_INDEX_TITLE = 1;
    private static final int COLUMN_INDEX_MODIFICATION_DATE = 3;


    public NotesListAdapter(Context context, Cursor cursor,Uri uri,String actionName) {

        background=MyApplication.getBackground();
        this.context=context;
        this.cursor = cursor;
        this.actionName=actionName;
        mUri=uri;
        inflater=LayoutInflater.from(context);
        mDate=new ArrayList<NoteBean>();
        readDate();
    }

    @Override
    public int getCount() {
        return mDate.size();
    }

    @Override
    public Object getItem(int position) {
        return  mDate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHold viewHold=null;
        if(convertView==null){
            viewHold=new ViewHold();
            convertView=inflater.inflate(R.layout.noteslistitem,null);
            viewHold.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            viewHold.tv_data= (TextView) convertView.findViewById(R.id.tv_data);
            viewHold.iv_editNote= (ImageView) convertView.findViewById(R.id.iv_editNote);
            viewHold.iv_deleteNote= (ImageView) convertView.findViewById(R.id.iv_deleteNote);
            viewHold.ll_notesItems= (LinearLayout) convertView.findViewById(R.id.ll_notesItems);
            convertView.setTag(viewHold);
        }else{
            viewHold= (ViewHold) convertView.getTag();
        }
        if(mDate.get(position)!=null){

            viewHold.tv_title.setText(mDate.get(position).getTitle());
            viewHold.tv_data.setText(DateUtil.StringToDate(mDate.get(position).getCreateTime()+""));
        }
        viewHold.ll_notesItems.setBackgroundColor(Color.parseColor(background));
        setClick(viewHold,position);
        return convertView;
    }

    /*
    为Item中的修改删除设置监听
     */
    private void setClick(ViewHold viewHold, final int position) {

        //为编辑控件设置监听
        viewHold.iv_editNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Constructs a new URI from the incoming URI and the row ID
                Uri uri = ContentUris.withAppendedId(mUri,Integer.parseInt(mDate.get(position).getCursor_id()));
                // Gets the action from the incoming Intent
                String action = actionName;
                // Handles requests for note data
                // Sends out an Intent to start an Activity that can handle ACTION_EDIT. The
                // Intent's data is the note ID URI. The effect is to call NoteEdit.
                Intent intent = new Intent(Intent.ACTION_EDIT, uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                context.startActivity(intent);
            }
        });

        //为删除控件设置监听
        viewHold.iv_deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteNote(Uri.parse(mUri+"/"+mDate.get(position).getCursor_id()));
                mDate.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    class  ViewHold{
        public TextView tv_title,tv_data;
        public ImageView iv_editNote,iv_deleteNote;
        public LinearLayout ll_notesItems;
    }

    /*
    读取数据库中的数据存到mDate中
     */
    public void readDate()
    {
        mDate.clear();
        while(cursor.moveToNext())
        {
            mDate.add(new NoteBean(cursor.getString(COLUMN_INDEX_TITLE),cursor.getString(COLUMN_INDEX_MODIFICATION_DATE),cursor.getString(COLUMN_INDEX_ID)));
            Log.d("hhh",cursor.getString(COLUMN_INDEX_TITLE)+","+cursor.getString(COLUMN_INDEX_MODIFICATION_DATE)+","+cursor.getString(COLUMN_INDEX_ID));
        }
    }

    /*
    读取数据库中的数据存到mDate中
 */
    public void readDate(Cursor cursor)
    {
        mDate.clear();
        while(cursor.moveToNext())
        {
            mDate.add(new NoteBean(cursor.getString(COLUMN_INDEX_TITLE),cursor.getString(COLUMN_INDEX_MODIFICATION_DATE),cursor.getString(COLUMN_INDEX_ID)));
            Log.d("hhh",cursor.getString(COLUMN_INDEX_TITLE)+","+cursor.getString(COLUMN_INDEX_MODIFICATION_DATE)+","+cursor.getString(COLUMN_INDEX_ID));
        }
    }

    /**
     * Take care of deleting a note.  Simply deletes the entry.
     */
    private void deleteNote(Uri noteUri) {
        context.getApplicationContext().getContentResolver().delete(
                noteUri,  // The URI of the provider
                null,     // No where clause is needed, since only a single note ID is being
                // passed in.
                null      // No where clause is used, so no where arguments are needed.
        );

    }

    public List<NoteBean> getmDate() {
        return mDate;
    }

    public void setmDate(List<NoteBean> mDate) {
        this.mDate = mDate;
    }
    public String getBackground() {return background;}

    public void setBackground(String background) {this.background = background;}

    public void Search(String searchTitle){
        searchData=new ArrayList<NoteBean>();
        for(NoteBean noteBean:mDate){
            if(noteBean.getTitle().indexOf(searchTitle)!=-1){
                searchData.add(noteBean);
            }
        }
        mDate.clear();
        mDate.addAll(searchData);
        notifyDataSetChanged();
    }
}
