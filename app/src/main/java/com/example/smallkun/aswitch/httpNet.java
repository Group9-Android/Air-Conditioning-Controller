package com.example.smallkun.aswitch;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vbn on 18/6/14.
 */

public class httpNet {


    private static String tag = "wangmingzhi";
    private URL url;
    //private String username;
    // private String password;

    public httpNet(URL address)
    {
        //username=name;
        // password=word;
        url=address;

    }


    public  String loginOfPost() {
        HttpURLConnection conn = null;
        try {

            // URL url = new URL("http://112.74.47.100:8080/SharedUmbrellaSys_Server/logServlet?user_name="+URLEncoder.encode(username, "utf-8")+"&password="+URLEncoder.encode(password, "utf-8"));
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(3000);
            conn.setDoOutput(true);




            int responseCode= conn.getResponseCode();
            Log.i(tag, "1 "+Integer.toString(responseCode));

            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                String state = getStringFromInputStream(is);
                Log.i(tag, "网络连接成功");
                Log.i(tag, state);

                return state;


            } else {

                Log.i(tag, "网络连接失败");
                String state="没连上网";
                return state;

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        Log.i(tag, "网络。。。。");
        String state="returnfailure";
        System.out.print(state);
        return state;
    }

    public  String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        String result = baos.toString();
        is.close();
        baos.close();

        return result;
    }


}