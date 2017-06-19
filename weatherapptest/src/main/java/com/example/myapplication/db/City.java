package com.example.myapplication.db;


/**
 * Created by yang on 17-6-13.
 */

public class City {
    private int id;
    private int cityCode;
    private int proviceId;
    private String cityName;

    public City() {
    }

    public City(int id, int cityCode, int proviceId, String cityName) {
        this.id = id;
        this.cityCode = cityCode;
        this.proviceId = proviceId;
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProviceId() {
        return proviceId;
    }

    public void setProviceId(int proviceCode) {
        this.proviceId = proviceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
