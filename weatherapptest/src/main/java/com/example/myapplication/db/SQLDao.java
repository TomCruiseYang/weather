package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 17-6-13.
 */

public class SQLDao {
    private MySQLiteHelper mMySQLiteHelper;
    private SQLiteDatabase mSQLiteDatabase;
    public SQLDao(Context context) {
        mMySQLiteHelper = new MySQLiteHelper(context);
        mSQLiteDatabase=mMySQLiteHelper.getReadableDatabase();
    }
    public long addData(String area,ContentValues values){
        long insert =mSQLiteDatabase.insert(area,null,values);
        return insert;
    }
    public List queryData(String area,int lid){
        switch (area){
            case "provice":
                List<Provice> proviceList = new ArrayList<>();
                Cursor c = mSQLiteDatabase.rawQuery("select * from provice", null);
                while (c.moveToNext())
                {
                    Provice provice = new Provice();
                    provice.setProviceName(c.getString(c.getColumnIndex("proviceName")));
                    provice.setProviceCode(c.getInt(c.getColumnIndex("proviceCode")));
                    proviceList.add(provice);
                }
                c.close();
                return proviceList;
            case "city":
                List<City> cityList = new ArrayList<>();
                Cursor c2 = mSQLiteDatabase.query("city",null,"proviceId=?",new String[]{""+lid},null,null,null);
                while (c2.moveToNext())
                {
                    City city = new City();
                    city.setCityName(c2.getString(c2.getColumnIndex("cityName")));
                    city.setCityCode(c2.getInt(c2.getColumnIndex("cityCode")));
                    city.setProviceId(c2.getInt(c2.getColumnIndex("proviceId")));
                    cityList.add(city);
                }
                c2.close();
                return cityList;
            case "county":
                List<County> list = new ArrayList<>();
                Cursor c3 = mSQLiteDatabase.query("county", null,"cityId=?",new String[]{lid+""},null,null,null);
                while (c3.moveToNext())
                {
                    County county = new County();
                    county.setCountyName(c3.getString(c3.getColumnIndex("countyName")));
                    county.setWeatherId(c3.getString(c3.getColumnIndex("weatherId")));
                    county.setCityId(c3.getInt(c3.getColumnIndex("cityId")));
                    list.add(county);
                }
                c3.close();
                return list;
        }
        return null;
    }
    public void closeDb(){
        mSQLiteDatabase.close();
    }
}
