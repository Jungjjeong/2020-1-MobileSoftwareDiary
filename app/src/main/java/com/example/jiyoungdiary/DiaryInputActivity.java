package com.example.jiyoungdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class DiaryInputActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    private Button button;
    private ImageView imageView;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_input);
        imageView = findViewById(R.id.image);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    imageView.setImageBitmap(img);
                    uri = data.getData();
                    System.out.println(uri);
                } catch (Exception e) {
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void addContent(View view) {
        ContentValues addValues = new ContentValues();
        addValues.put(MyContentProvider.NAME, ((EditText)findViewById(R.id.editText1)).getText().toString());
        addValues.put(MyContentProvider.DIARY_CONTENTS, ((EditText)findViewById(R.id.editText2)).getText().toString());
        addValues.put(MyContentProvider.LATITUDE, ((EditText)findViewById(R.id.latEdit)).getText().toString());
        addValues.put(MyContentProvider.LONGITUDE, ((EditText)findViewById(R.id.longEdit)).getText().toString());
        addValues.put(MyContentProvider.PICID, (uri).toString());

        getContentResolver().insert(MyContentProvider.CONTENT_URI, addValues);
        Toast.makeText(getBaseContext(), "일기가 저장되었습니다.", Toast.LENGTH_LONG).show();
    }

}