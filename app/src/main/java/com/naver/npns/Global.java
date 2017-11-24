package com.naver.npns;

import android.app.Application;

import com.google.gson.Gson;

public class Global extends Application{
    public static final String HOST_URL = "http://10.83.36.184:18090/request_connect/";
    public static final int THRIFT_PORT = 10000;
    public static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        gson = new Gson();
    }
}
