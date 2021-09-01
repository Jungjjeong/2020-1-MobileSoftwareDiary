package com.example.jiyoungdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryDBManager extends SQLiteOpenHelper {
    static final String CONTENT_DB = "Contents5.db";
    static final String CONTENT_TABLE = "contents5";
    Context context = null;
    private static DiaryDBManager dbManager = null;
    static final String CREATE_DB = " CREATE TABLE " + CONTENT_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " name TEXT NOT NULL, diary_contents TEXT NOT NULL, latitude TEXT NOT NULL, longitude TEXT NOT NULL, picid TEXT NOT NULL);";

    public static DiaryDBManager getInstance(Context context) {
        if(dbManager == null) {
            dbManager = new DiaryDBManager(context, CONTENT_DB, null, 1);
        }
        return dbManager;
    }
    public DiaryDBManager(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        this.context = context;
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }
    public long insert(ContentValues addValue) {
        return getWritableDatabase().insert(CONTENT_TABLE, null, addValue);
    }
    public Cursor query(String [] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return getReadableDatabase().query(CONTENT_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
    public int delete(String whereClause, String[] whereArgs)
    {
        return getWritableDatabase().delete(CONTENT_TABLE, whereClause, whereArgs);
    }
}
