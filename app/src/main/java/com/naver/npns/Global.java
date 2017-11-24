package com.naver.npns;

import android.app.Application;

import com.google.gson.Gson;
import com.naver.npns.util.TimeHelper;

public class Global extends Application{
    public static final String HOST_URL = "http://10.83.36.184:18090";
    public static final String HOST_CONNECT_PATH = "/request_connect";
    public static final int THRIFT_PORT = 10000;
    public static Gson gson;
    public static TimeHelper timeHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        gson = new Gson();
        timeHelper = new TimeHelper();
    }
}
