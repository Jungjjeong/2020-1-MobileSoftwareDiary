package com.example.jiyoungdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_list = (Button)findViewById(R.id.button_list);

        //button_list.setOnClickListener(new View.OnClickListener()});
    }
    public void onClickInputButton(View v) {
        Intent intent = new Intent(getApplicationContext(),DiaryInputActivity.class);
        startActivity(intent);
    }
    public void onClickViewButton(View v) {
        Intent intent = new Intent(getApplicationContext(),DiaryViewActivity.class);
        startActivity(intent);
    }
}