package com.naver.npns.network;

import android.content.Context;
import android.util.Log;

import com.naver.npns.model.ClientInfo;
import com.naver.npns.util.UuidHelper;

import java.io.IOException;
import java.net.Socket;

public class ClientInfoHelper extends Thread {

    private final String TAG = ClientInfoHelper.class.getCanonicalName();

    private final String HOST_URL = "http://10.83.32.40:18090/request_connect/";

    private Context mContext;

    public ClientInfoHelper(Context context) {
        mContext = context;
    }

    @Override
    public void run() {
        String url = null;
        try {
            Socket socket = new Socket("www.google.com", 80);
            url = socket.getLocalAddress().getHostAddress();
            socket.close();
            String uuid = UuidHelper.getUuid(mContext);
            ClientInfo info = new ClientInfo(url, uuid);

            HttpHelper httpHelper = new HttpHelper();
            httpHelper.post(HOST_URL, info.getJson());

            Log.d(TAG, info.getJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
