package com.naver.npns_demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.net.Socket;

public class MainService extends Service {

    private final String TAG = MainService.class.getCanonicalName();

    public MainService() {
    }

    private String mClientUrl;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Service start");
        getClientUrl();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // get client ip address
    public void getClientUrl() {
        Thread thread = new Thread(() -> {
            try {
                Socket socket = new Socket("www.google.com", 80);
                String mClientUrl = socket.getLocalAddress().getHostAddress();
                Log.d(TAG, "local address is : " + mClientUrl);
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }
}
