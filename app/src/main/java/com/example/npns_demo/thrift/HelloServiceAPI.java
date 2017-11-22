package com.example.npns_demo.thrift;

import android.util.Log;

import org.apache.thrift.TException;

/**
 * Created by JHKim on 2017-11-22.
 */

public class HelloServiceAPI implements HelloService.Iface{

    @Override
    public String hello(String name) throws TException {
        Log.d(HelloServiceAPI.class.getCanonicalName(), name + "get from java!");
        return name + "get from java!";
    }

}
