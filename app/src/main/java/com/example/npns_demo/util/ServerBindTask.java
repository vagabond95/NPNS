package com.example.npns_demo.util;

import android.os.AsyncTask;
import android.util.Log;

import com.example.npns_demo.thrift.HelloService;
import com.example.npns_demo.thrift.HelloServiceAPI;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class ServerBindTask extends AsyncTask<String,String,Boolean>{
    private final String TAG = ServerBindTask.class.getCanonicalName();
    private final String SUCCESS_NETWORK = "서버 바인딩 성공!";
    private final String FAIL_NETWORK = "서버 바인딩 실패...";

    TaskListener mListener;

    public ServerBindTask(TaskListener listener){
        mListener = listener;
    }

    //서버 바인딩 작업
    @Override
    protected Boolean doInBackground(String... strings) {
        HelloServiceAPI hserver = new HelloServiceAPI();
        HelloService.Processor processor = new HelloService.Processor(hserver);
        Boolean networkState = false;

        TServerTransport transport = null;
        try {
            transport = new TServerSocket(9091);
            networkState = true;
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        TServer server = new TSimpleServer(new TServer.Args(transport).processor(processor));
        Log.d(TAG,"start server");
        server.serve();
        return networkState;
    }

    @Override
    protected void onPostExecute(Boolean state) {
        if(mListener != null) {
            if(state) {
                mListener.taskCompleted(SUCCESS_NETWORK);
            } else {
                mListener.taskCompleted(FAIL_NETWORK);
            }
        }
    }

    public interface TaskListener {
         void taskCompleted(String result);
    }
}
