package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yang on 17-6-13.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String CREATE_PROVICE = "create table provice ("
            + "id integer primary key autoincrement, "
            + "proviceCode integer, "
            + " proviceName text)";
    public static final String CREATE_CITY = "create table city ("
            + "id integer primary key autoincrement, "
            + "cityCode integer, "
            + "proviceId integer,"
            + "cityName text)";
    public static final String CREATE_COUNTY = "create table county ("
            + "id integer primary key autoincrement, "
            + "cityId integer, "
            + "countyName text,"
            + " weatherId text)";
    public MySQLiteHelper(Context context) {
        super(context, "cool_weather", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
        db.execSQL(CREATE_PROVICE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
