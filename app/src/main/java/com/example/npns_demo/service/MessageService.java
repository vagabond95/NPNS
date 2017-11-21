package com.example.npns_demo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.npns_demo.BaseApplication;
import com.example.npns_demo.R;
import com.example.npns_demo.client.MqttClientHelper;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MessageService extends Service {

    private final String TAG = MessageService.class.getCanonicalName();

    private MqttClientHelper mClientHelper;
    private MqttAndroidClient mAndroidClient;

    public MessageService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mClientHelper = new MqttClientHelper(getApplicationContext(), BaseApplication.BROKER_URL,"jhKim");
        mAndroidClient = mClientHelper.getClient();

        mAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
            }

            @Override
            public void connectionLost(Throwable throwable) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage msg) throws Exception {
                showNotification(topic, new String(msg.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });
    }

    private void showNotification(@NonNull String topic, @NonNull String msg) {
        Notification.Builder builder =
                new Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(topic)
                        .setContentText(msg);

        NotificationManager NotiManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotiManager.notify(0, builder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}