package com.example.smallkun.aswitch.db;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vbn on 18/6/5.
 */

public class City extends DataSupport {
    private int provinceId;
    private int id;
    private int city_code;
    private String city_name;
    private List<County> counties = new ArrayList<County>();

    public List<County> getCounties() {

        return counties;
    }

    public void setCounties(List<County> counties) {
        this.counties = counties;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getCity_code() {
        return city_code;
    }

    public void setCity_code(int city_code) {
        this.city_code = city_code;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvince(int provinceId) {
        this.provinceId = provinceId;
    }
}
