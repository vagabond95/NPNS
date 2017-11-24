package com.naver.npns.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.naver.npns.model.MessageData;

public class MainService extends Service {

    private final String TAG = MainService.class.getCanonicalName();

    private static Context mContext;

    public MainService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    private static Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Log.d("Message", "[PUSH] Message handle");
            showNotification(msg);
        }
    };


    public static void handlePusgMsg(MessageData messageData) {
        Message msg = mHandler.obtainMessage();
        msg.obj = messageData;
        mHandler.sendMessage(msg);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static void showNotification(Message msg) {
        MessageData data = (MessageData) msg.obj;

        NotificationCompat.Builder mBuilder
                = new NotificationCompat.Builder(mContext)
                .setSmallIcon(android.support.v4.R.drawable.notification_icon_background)
                .setContentTitle(data.getTitle())
                .setContentText(data.getBody());

        NotificationManager notificationManager
                = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
        Log.d("Message", "[PUSH] Title : " + data.getTitle());
        Log.d("Message", "[PUSH] body : " + data.getBody());
    }
}
