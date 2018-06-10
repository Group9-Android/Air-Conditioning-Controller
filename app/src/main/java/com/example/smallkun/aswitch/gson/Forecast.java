package com.example.smallkun.aswitch.gson;

/**
 * Created by vbn on 18/6/5.
 */
import com.google.gson.annotations.SerializedName;
public class Forecast {
    public String date;
    @SerializedName("temp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature {
        public String max;
        public String min;
    }
    public class More{
        @SerializedName("txt_d")
        public String info;
    }
}
