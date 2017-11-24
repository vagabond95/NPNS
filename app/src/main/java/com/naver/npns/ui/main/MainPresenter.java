package com.naver.npns.ui.main;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.naver.npns.Global;
import com.naver.npns.network.ClientDataHelper;
import com.naver.npns.network.ServerRunTask;

import static com.naver.npns.Global.HOST_URL;

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
        ClientDataHelper clinetHelper = new ClientDataHelper(mView.getApplicationContext());
        clinetHelper.start();
        Toast.makeText(mView.getApplicationContext(), "Connect " + HOST_URL, Toast.LENGTH_LONG).show();
    }
}
