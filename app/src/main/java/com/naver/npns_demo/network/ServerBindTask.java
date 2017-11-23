package com.naver.npns_demo.network;

import android.os.AsyncTask;
import android.util.Log;

import com.naver.npns_demo.Global;
import com.naver.npns_demo.model.PushReceiveService;
import com.naver.npns_demo.model.PushRecvInterface;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class ServerBindTask extends AsyncTask<String,String,Boolean>{
    private final String TAG = ServerBindTask.class.getCanonicalName();

    //서버 바인딩 작업
    @Override
    protected Boolean doInBackground(String... strings) {
        Boolean networkState = false;

        PushRecvInterface pushApi = new PushRecvInterface();

        PushReceiveService.Processor processor = new PushReceiveService.Processor(pushApi);

        TServerTransport transport = null;
        try {
            transport = new TServerSocket(Global.THRIFT_PORT);
            networkState = true;
            Log.d(TAG, "success bind!");
        } catch (TTransportException e) {
            Log.d(TAG, "fail bind!");
        }

        TServer server = new TSimpleServer(new TServer.Args(transport).processor(processor));
        Log.d(TAG, "Start server");

        server.serve();

        return networkState;
    }
}
