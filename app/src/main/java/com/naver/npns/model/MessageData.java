package com.naver.npns.model;

public class MessageData {
    private int seq;
    private String title;
    private String body;

    public MessageData(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public MessageData(int seq, String title, String body) {
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
