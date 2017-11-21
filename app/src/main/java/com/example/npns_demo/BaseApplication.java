package com.example.npns_demo;

import android.app.Application;

/**
 * Created by JHKim on 2017-11-21.
 */

public class BaseApplication extends Application{
    public static final String BROKER_URL = "tcp://iot.eclipse.org:1883";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
