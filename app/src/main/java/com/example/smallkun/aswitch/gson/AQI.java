package com.example.smallkun.aswitch.gson;

/**
 * Created by vbn on 18/6/5.
 */

public class AQI {
    public AQICity city;
    public class AQICity {
        public String api;
        public String pm25;
    }
}
