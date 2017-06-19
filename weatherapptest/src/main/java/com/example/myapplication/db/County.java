package com.example.myapplication.db;

/**
 * Created by yang on 17-6-13.
 */

public class County {
    private int id;
    private int cityId;
    private String weatherId;
    private String countyName;

    public County() {
    }

    public County(int id, int cityId, String weatherId, String countyName) {
        this.id = id;
        this.cityId = cityId;
        this.weatherId = weatherId;
        this.countyName = countyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}
