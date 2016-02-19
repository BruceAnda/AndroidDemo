package com.zhaoliang.androiddemo.day03.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库打开帮助类
 * Created by pro on 16/2/3.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "person.db";      // 数据库名称
    private static final int DB_VERSION = 2;            // 数据库版本

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id Integer primary key autoincrement, name varchar(20), sex varchar(20), salary varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table person add column address varchar(20)");
    }
}
