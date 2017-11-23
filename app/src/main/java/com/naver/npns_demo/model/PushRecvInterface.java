package com.naver.npns_demo.model;

import android.util.Log;

import com.naver.npns_demo.message.Message;
import com.naver.npns_demo.message.PushMessage;
import com.naver.npns_demo.service.MainService;

import org.apache.thrift.TException;

public class PushRecvInterface implements PushReceiveService.Iface{
    private final String TAG = PushRecvInterface.class.getCanonicalName();

    @Override
    public String ping() throws TException {
        Log.d(TAG, "[Thrift] : ping");
        return "pong!!";
    }
    @Override
    public boolean recv(PushMessage msg) throws TException {
        try {
            int seq = msg.getSeq();
            Message message = msg.getMsg();
            String title = message.getTitle();
            String body = message.getMessage();

            MessageInfo messageInfo = new MessageInfo(seq,title,body);
            MainService.handlePusgMsg(messageInfo);
            Log.d(TAG, "[Thrift] : success recieve message");

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
