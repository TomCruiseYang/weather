package com.example.myapplication.g;

/**
 * Created by yang on 17-6-15.
 */

class BasicBean {
    /**
     * city : 青岛
     * cnty : 中国
     * id : CN101120201
     * lat : 36.088000
     * lon : 120.343000
     * prov : 山东
     * update : {"loc":"2016-08-30 11:52","utc":"2016-08-30 03:52"}
     */

    private String city;
    private String cnty;
    private String id;
    private String lat;
    private String lon;
    private String prov;
    private UpdateBean update;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public UpdateBean getUpdate() {
        return update;
    }

    public void setUpdate(UpdateBean update) {
        this.update = update;
    }
}
