package com.example.myapplication.g;

/**
 * Created by yang on 17-6-15.
 */

class HourlyForecastBean {
    /**
     * cond : {"code":"100","txt":"晴"}
     * date : 2016-08-30 12:00
     * hum : 47
     * pop : 0
     * pres : 1006
     * tmp : 29
     * wind : {"deg":"335","dir":"西北风","sc":"4-5","spd":"36"}
     */

    private CondBeanXX cond;
    private String date;
    private String hum;
    private String pop;
    private String pres;
    private String tmp;
    private WindBeanXX wind;

    public CondBeanXX getCond() {
        return cond;
    }

    public void setCond(CondBeanXX cond) {
        this.cond = cond;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public WindBeanXX getWind() {
        return wind;
    }

    public void setWind(WindBeanXX wind) {
        this.wind = wind;
    }
}
