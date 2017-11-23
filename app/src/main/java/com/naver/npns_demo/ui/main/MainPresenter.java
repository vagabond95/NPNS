package com.naver.npns_demo.ui.main;

import android.support.annotation.NonNull;

import com.naver.npns_demo.network.ServerBindTask;

public class MainPresenter {

    private MainActivity mView;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = view;
    }

    public void startServer() {
        ServerBindTask task = new ServerBindTask(result -> {
                /*mServerStateText.setText(result);
                Log.d(TAG,result);*/
        });
        task.execute();
    }
}
