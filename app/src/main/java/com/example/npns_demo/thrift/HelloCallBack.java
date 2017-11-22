package com.example.npns_demo.thrift;

import android.util.Log;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

/**
 * Created by JHKim on 2017-11-22.
 */

public class HelloCallBack implements AsyncMethodCallback<HelloService.AsyncClient.hello_call> {

    private final String TAG = HelloCallBack.class.getCanonicalName();

    @Override
    public void onComplete(HelloService.AsyncClient.hello_call response) {
        try {
            String result = response.getResult();
            Log.d(TAG,"CALLBACK : "+result);
        } catch (TException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Exception exception) {
        Log.d(TAG,"FAIL CALLBACK");
    }
}
