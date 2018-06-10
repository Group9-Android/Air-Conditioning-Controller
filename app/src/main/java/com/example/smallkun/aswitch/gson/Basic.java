package com.example.smallkun.aswitch.gson;

/**
 * Created by vbn on 18/6/5.
 */
import com.google.gson.annotations.SerializedName;
public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;

    public Update update;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}

