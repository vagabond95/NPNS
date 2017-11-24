package com.naver.npns.model;

import android.util.Log;

import com.naver.npns.message.PushMessage;

import org.apache.thrift.TException;

public class PushRecvInterface implements PushReceiveService.Iface{
    private final String TAG = PushRecvInterface.class.getCanonicalName();
    private OnMsgIncomedListener mListener;

    public PushRecvInterface(OnMsgIncomedListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public String ping() throws TException {
        Log.d(TAG, "[Thrift] : ping");
        return "pong!!";
    }

    @Override
    public boolean recv(PushMessage msg) throws TException {
        try {
            mListener.onIncomedListener(msg.getMsg());
//            int seq = msg.getSeq();
//            Message message = msg.getMsg();
//            String title = message.getTitle();
//            String body = message.getMessage();
//
//            MessageInfo messageInfo = new MessageInfo(seq,title,body);
//            MainService.handlePusgMsg(messageInfo);
            Log.d(TAG, "[Thrift] : success recieve message");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
