package com.example.smallkun.aswitch.db;

import org.litepal.crud.DataSupport;

/**
 * Created by vbn on 18/6/5.
 */

public class County extends DataSupport {
    private int id;
    private String county_name;
    private String weatherId;
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public void setWeatherId(String weatherId){
        this.weatherId = weatherId;
    }

    public String getWeatherId(){
        return weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

}

