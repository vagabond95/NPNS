package com.naver.npns_demo.model;

import com.naver.npns_demo.Global;

public class ClientInfo {
    private String ip;
    private String uuid;

    public ClientInfo(String ip, String uuid) {
        this.ip = ip;
        this.uuid = uuid;
    }

    public String getJson() {
        return Global.gson.toJson(this);
    }
}
