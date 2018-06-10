package com.example.smallkun.aswitch.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by vbn on 18/6/5.
 */

public class HttpUtil {
    /**
     * Created by vbn on 18/5/22.
     */
    public static void sendOkHttpRequest(String adress, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(adress).build();
        client.newCall(request).enqueue(callback);

    }

}
