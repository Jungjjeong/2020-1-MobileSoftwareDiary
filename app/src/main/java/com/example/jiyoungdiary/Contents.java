package com.example.jiyoungdiary;

import android.net.Uri;

import java.sql.Blob;

public class Contents {
    int id;
    String name;
    String diary_contents;
    String latitude; // 위도
    String longitude; // 경도
    Uri picid; // picture uri

    public Contents (int id,String name, String diary_contents,String latitude, String longitude, Uri picid){
        this.id = id;
        this.name = name;
        this.diary_contents = diary_contents;
        this.latitude = latitude;
        this.longitude = longitude;
        this.picid = picid;

    }
    public String getName(){
        return name;
    }
    public String getDiary_contents(){
        return diary_contents;
    }
    public String getLatitude(){ return latitude; }
    public String getLongitude() { return longitude; }
    public Uri getPicid(){
        return picid;
    }
}
