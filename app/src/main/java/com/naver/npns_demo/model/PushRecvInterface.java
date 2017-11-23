package com.naver.npns_demo.model;

import org.apache.thrift.TException;

public class PushRecvInterface implements PushReceiveService.Iface{
    @Override
    public String ping() throws TException {
        return "ping!!";
    }

    @Override
    public boolean recv(pushMessage msg) throws TException {
        return false;
    }
}
