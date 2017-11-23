package com.naver.npns_demo.ui.main;

import android.support.annotation.NonNull;

import com.naver.npns_demo.network.ServerBindTask;
import com.naver.npns_demo.network.ClientInfoHelper;

public class MainPresenter {

    private final String TAG = MainPresenter.class.getCanonicalName();

    private MainActivity mView;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = view;
    }

    public void runServer() {
        new ServerBindTask().execute();
    }

    public void requestPost() {
        ClientInfoHelper clinetHelper = new ClientInfoHelper(mView.getApplicationContext());
        clinetHelper.start();
    }
}
