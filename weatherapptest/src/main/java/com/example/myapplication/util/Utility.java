package com.example.myapplication.util;

import android.text.TextUtils;

import com.example.myapplication.db.City;
import com.example.myapplication.db.County;
import com.example.myapplication.db.Provice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 17-6-13.
 */

public class Utility {
    public static List<Provice> handProviceData(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvices = new JSONArray(response);
                List<Provice> list = new ArrayList<>();
                for(int i=0;i<allProvices.length();i++){
                    JSONObject jsonObject=allProvices.getJSONObject(i);
                    Provice provice = new Provice();
                    provice.setProviceCode(jsonObject.getInt("id"));
                    provice.setProviceName(jsonObject.getString("name"));
                    list.add(provice);
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static List<City> handCityData(String response,int proviceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCitys = new JSONArray(response);
                List<City> list = new ArrayList<>();
                for(int i=0;i<allCitys.length();i++){
                    JSONObject jsonObject=allCitys.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setCityName(jsonObject.getString("name"));
                    city.setProviceId(proviceId);
                    list.add(city);
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static List<County> handCountyData(String response, int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCountys = new JSONArray(response);
                List<County> list = new ArrayList<>();
                for(int i=0;i<allCountys.length();i++){
                    JSONObject jsonObject=allCountys.getJSONObject(i);
                    County county = new County();
                    county.setCityId(cityId);
                    county.setCountyName(jsonObject.getString("name"));
                    county.setWeatherId(jsonObject.getString("weather_id"));
                    list.add(county);
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
