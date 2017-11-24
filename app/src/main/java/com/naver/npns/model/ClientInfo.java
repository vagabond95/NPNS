package com.naver.npns.model;

import com.naver.npns.Global;

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
