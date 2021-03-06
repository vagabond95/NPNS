package com.naver.npns.network;

import android.content.Context;
import android.util.Log;

import com.naver.npns.model.ClientData;
import com.naver.npns.util.UuidHelper;

import java.io.IOException;
import java.net.Socket;

import static com.naver.npns.Global.HOST_CONNECT_PATH;
import static com.naver.npns.Global.HOST_URL;

public class ClientDataHelper extends Thread {

    private final String TAG = ClientDataHelper.class.getCanonicalName();

    private Context mContext;

    public ClientDataHelper(Context context) {
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
            ClientData info = new ClientData(url, uuid);

            HttpHelper httpHelper = new HttpHelper();
            httpHelper.post(HOST_URL + HOST_CONNECT_PATH, info.getJson());

            Log.d(TAG, "[HTTP] Json : " + info.getJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
