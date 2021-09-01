package com.example.jiyoungdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class DiaryViewActivity extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_view);


        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        String[] columns = new String[]{"_id","name","diary_contents","latitude","longitude","picid"};

        ArrayList<Contents> ContentsInfo = new ArrayList<>();
        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI, columns, null, null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
                int _id = c.getInt(0);
                String name = c.getString(1);
                String diary_contents = c.getString(2);
                String latitude = c.getString(3);
                String longitude = c.getString(4);
                Uri picid = Uri.parse(c.getString(5));
                ContentsInfo.add(new Contents(_id,name,diary_contents,latitude,longitude,picid));
            }
        }
        c.close();

        MyAdapter myAdapter = new MyAdapter(ContentsInfo);
        myRecyclerView.setAdapter(myAdapter);
    }
}