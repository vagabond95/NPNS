package com.naver.npns_demo;

import android.app.Application;

/**
 * Created by JHKim on 2017-11-21.
 */

public class BaseApplication extends Application{
    public static final String SERVER_URL = "http://210.89.180.85:18090/";
    public static final int THRIFT_PORT = 10000;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
