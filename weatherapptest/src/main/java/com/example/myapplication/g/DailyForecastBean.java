package com.example.myapplication.g;

/**
 * Created by yang on 17-6-15.
 */

public class DailyForecastBean {
    /**
     * astro : {"mr":"03:09","ms":"17:06","sr":"05:28","ss":"18:29"}
     * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
     * date : 2016-08-30
     * hum : 45
     * pcpn : 0.0
     * pop : 8
     * pres : 1005
     * tmp : {"max":"29","min":"22"}
     * vis : 10
     * wind : {"deg":"339","dir":"北风","sc":"4-5","spd":"24"}
     */

    private AstroBean astro;
    private CondBeanX cond;
    private String date;
    private String hum;
    private String pcpn;
    private String pop;
    private String pres;
    private TmpBean tmp;
    private String vis;
    private WindBeanX wind;

    public AstroBean getAstro() {
        return astro;
    }

    public void setAstro(AstroBean astro) {
        this.astro = astro;
    }

    public CondBeanX getCond() {
        return cond;
    }

    public void setCond(CondBeanX cond) {
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

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
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

    public TmpBean getTmp() {
        return tmp;
    }

    public void setTmp(TmpBean tmp) {
        this.tmp = tmp;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public WindBeanX getWind() {
        return wind;
    }

    public void setWind(WindBeanX wind) {
        this.wind = wind;
    }
}
