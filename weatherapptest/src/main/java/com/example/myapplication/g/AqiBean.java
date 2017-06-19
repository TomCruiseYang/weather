package com.example.myapplication.g;

/**
 * Created by yang on 17-6-15.
 */

class AqiBean {
    /**
     * city : {"aqi":"60","co":"0","no2":"14","o3":"95","pm10":"67","pm25":"15","qlty":"良","so2":"10"}
     */

    private CityBean city;

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }
}
