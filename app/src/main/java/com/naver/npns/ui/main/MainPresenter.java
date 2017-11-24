package com.naver.npns.ui.main;

import android.support.annotation.NonNull;

import com.naver.npns.network.ServerRunTask;
import com.naver.npns.network.ClientInfoHelper;

public class MainPresenter {

    private final String TAG = MainPresenter.class.getCanonicalName();

    private MainActivity mView;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = view;
    }

    public void runServer(MainListAdapter listener) {
        new ServerRunTask().execute(listener);
    }

    public void requestPost() {
        ClientInfoHelper clinetHelper = new ClientInfoHelper(mView.getApplicationContext());
        clinetHelper.start();
    }
}
