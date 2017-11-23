package com.naver.npns_demo.model;

import android.util.Log;

import org.apache.thrift.TException;

public class PushRecvInterface implements PushReceiveService.Iface{
    private final String TAG = PushRecvInterface.class.getCanonicalName();

    @Override
    public String ping() throws TException {
        Log.d(TAG, "pong");
        return "pong!!";
    }

    @Override
    public boolean recv(pushMessage msg) throws TException {
        return false;
    }
}
