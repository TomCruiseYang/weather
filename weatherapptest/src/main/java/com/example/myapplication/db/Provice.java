package com.example.myapplication.db;

/**
 * Created by yang on 17-6-13.
 */

public class Provice {
    private int id;
    private int proviceCode;
    private String proviceName;

    public Provice() {
    }

    public Provice(int id, int proviceCode, String proviceName) {
        this.id = id;
        this.proviceCode = proviceCode;
        this.proviceName = proviceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProviceCode() {
        return proviceCode;
    }

    public void setProviceCode(int proviceCode) {
        this.proviceCode = proviceCode;
    }

    public String getProviceName() {
        return proviceName;
    }

    public void setProviceName(String proviceName) {
        this.proviceName = proviceName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
