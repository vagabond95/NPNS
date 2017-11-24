package com.naver.npns.ui.main;

import android.support.annotation.NonNull;

import com.naver.npns.model.OnMsgIncomedListener;
import com.naver.npns.network.ServerBindTask;
import com.naver.npns.network.ClientInfoHelper;
import com.naver.npns.ui.list.ListActivity;

public class MainPresenter {

    private final String TAG = MainPresenter.class.getCanonicalName();

    private MainActivity mView;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = view;
    }

    public void runServer(ListActivity.MsgListAdapter listener) {
        new ServerBindTask().execute(listener);
    }

    public void requestPost() {
        ClientInfoHelper clinetHelper = new ClientInfoHelper(mView.getApplicationContext());
        clinetHelper.start();
    }
}
