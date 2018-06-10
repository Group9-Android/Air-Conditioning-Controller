package com.example.smallkun.aswitch.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vbn on 18/6/5.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }
}

