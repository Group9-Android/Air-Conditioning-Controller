package com.example.smallkun.aswitch.db;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vbn on 18/6/5.
 */

public class Province extends DataSupport {
    private int id;
    private String province_name;
    private int province_code;
    private List<City> cities = new ArrayList<City>();

    public int getProvince_code() {
        return province_code;
    }

    public void setProvince_code(int province_code){
        this.province_code = province_code;
    }

    public String getProvince_name(){
        return province_name;
    }

    public void setProvince_name(String province_name){
        this.province_name = province_name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public List<City> getCities(){
        return cities;
    }

    public void setCities(List<City> cities){
        this.cities = cities;
    }
}
