package com.naver.npns.model;

public class MessageData {
    private int seq;
    private String title;
    private String body;
    private String time;

    public MessageData(String title, String body, String time) {
        this.title = title;
        this.body = body;
        this.time = time;
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

    public String getTime() {
        return time;
    }
}
