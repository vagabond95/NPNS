package com.naver.npns.network;

import android.os.AsyncTask;
import android.util.Log;

import com.naver.npns.Callback.OnMsgIncomedListener;
import com.naver.npns.Global;
import com.naver.npns.model.PushReceiveService;
import com.naver.npns.model.PushServiceModule;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class ServerRunTask extends AsyncTask<OnMsgIncomedListener, String, Boolean> {
    private final String TAG = ServerRunTask.class.getCanonicalName();

    //서버 바인딩 작업
    @Override
    protected Boolean doInBackground(OnMsgIncomedListener... listener) {
        Boolean networkState = false;

        PushServiceModule module = new PushServiceModule(listener[0]);

        PushReceiveService.Processor processor = new PushReceiveService.Processor(module);

        TServerTransport transport = null;
        try {
            transport = new TServerSocket(Global.THRIFT_PORT);
            networkState = true;
            Log.d(TAG, "[Server] Success Port bind!");
        } catch (TTransportException e) {
            Log.d(TAG, "[Server] fail bind!");
        }

        TServer server = new TSimpleServer(new TServer.Args(transport).processor(processor));
        Log.d(TAG, "[Server] Run!");

        server.serve();

        return networkState;
    }
}
