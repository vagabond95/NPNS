package com.naver.npns.model;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.naver.npns.Callback.OnMsgIncomedListener;
import com.naver.npns.message.Message;
import com.naver.npns.message.PushMessage;
import com.naver.npns.service.MainService;

import org.apache.thrift.TException;

public class PushServiceModule implements PushReceiveService.Iface{
    private final String TAG = PushServiceModule.class.getCanonicalName();
    private OnMsgIncomedListener mListener;

    public PushServiceModule(OnMsgIncomedListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public String ping() throws TException {
        Log.d(TAG, "[Thrift] : ping");
        return "pong!!";
    }

    @Override
    public boolean recv(PushMessage pushMsg) throws TException {
        try {
            int seq = pushMsg.getSeq();
            Message message = pushMsg.getMsg();
            String title = message.getTitle();
            String body = message.getMessage();

            new Thread() {
                public void run() {
                    android.os.Message msg = handler.obtainMessage();
                    msg.obj = message;
                    handler.sendMessage(msg);
                    Log.d(TAG, "[Thrift] : add push message to list");
                }

            }.start();

            MessageData messageData = new MessageData(seq,title,body);
            MainService.handlePusgMsg(messageData);
            Log.d(TAG, "[Thrift] : success recieve message");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    Handler handler = new Handler(Looper.getMainLooper()){
        public void handleMessage(android.os.Message msg) {
            Message msgData = (Message) msg.obj;
            mListener.onIncomedListener(msgData);
        }
    };
}
