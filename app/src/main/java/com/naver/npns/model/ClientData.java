package com.naver.npns.model;

import com.naver.npns.Global;

public class ClientData {
    private String ip;
    private String uuid;

    public ClientData(String ip, String uuid) {
        this.ip = ip;
        this.uuid = uuid;
    }

    public String getJson() {
        return Global.gson.toJson(this);
    }
}
