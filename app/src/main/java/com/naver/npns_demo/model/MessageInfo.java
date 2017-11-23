package com.naver.npns_demo.model;

public class MessageInfo {
    private int seq;
    private String title;
    private String body;

    public MessageInfo(int seq, String title, String body) {
        this.seq = seq;
        this.title = title;
        this.body = body;
    }

    public int getSeq() {
        return seq;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
