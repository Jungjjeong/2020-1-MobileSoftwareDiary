package com.example.jiyoungdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

public class ContentViewActivity extends AppCompatActivity {
    ImageView imageView;
    Double lati;
    Double longi;
    String place;
    Uri uri;
    protected void setImageURI(Uri uri) {
        try{
            InputStream in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_view);

        Intent intent = getIntent();
        TextView textView = (TextView)findViewById(R.id.name);
        TextView textView2 = (TextView)findViewById(R.id.content);
        TextView textView3 = (TextView)findViewById(R.id.latView);
        TextView textView4 = (TextView)findViewById(R.id.longvView);
        imageView = (ImageView)findViewById(R.id.imgView);

        textView.setText((intent.getStringExtra("name")));
        textView2.setText((intent.getStringExtra("diary_contents")));
        textView3.setText((intent.getStringExtra("latitude")));
        textView4.setText((intent.getStringExtra("longitude")));
        uri = getIntent().getParcelableExtra("picid");
        setImageURI(uri);
        lati = Double.parseDouble(intent.getStringExtra("latitude"));
        longi = Double.parseDouble(intent.getStringExtra("longitude"));
        place = intent.getStringExtra("name");
    }
    public void onClickGoogleButton(View v) {
        Intent intent = new Intent(getApplicationContext(),GoogleMapsActivity.class);
        intent.putExtra("latitude",lati);
        intent.putExtra("longitude",longi);
        intent.putExtra("name",place);
        startActivity(intent);
    }
}