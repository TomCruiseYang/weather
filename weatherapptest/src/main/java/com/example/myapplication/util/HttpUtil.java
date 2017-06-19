package com.example.myapplication.util;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yang on 17-6-13.
 */

public class HttpUtil {
    public static final String KEY="fa26cf7667844122a22d490771433186";
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
    public static void requestWeather(String cityName,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("https://free-api.heweather.com/v5/weather?city="+cityName+"&key="+KEY).build();
        client.newCall(request).enqueue(callback);
    }
    public static void requestNowWeather(String cityName,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("https://free-api.heweather.com/v5/now?city="+cityName+"&key="+KEY).build();
        client.newCall(request).enqueue(callback);
    }
    public static void requestForecastWeather(String cityName,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("https://free-api.heweather.com/v5/forecast?city="+cityName+"&key="+KEY).build();
        client.newCall(request).enqueue(callback);
    }
    public static void getBing(Callback callback){

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("http://guolin.tech/api/bing_pic").build();
        client.newCall(request).enqueue(callback);
    }
    public static void getphoto(String url,Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

}
