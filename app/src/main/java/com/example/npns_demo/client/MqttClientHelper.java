package com.example.npns_demo.client;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

/**
 * Created by JHKim on 2017-11-20.
 */

public class MqttClientHelper {

    private final String TAG = MqttClientHelper.class.getCanonicalName();

    private MqttAndroidClient mMqttClient;


    //일단 MqttClientHelper object를 생성하면, broker와 connect를 시도한다.
    public MqttClientHelper(Context context, @NonNull String hostUrl, @NonNull String clientId) {

        mMqttClient = new MqttAndroidClient(context, hostUrl, clientId);

        try {
            IMqttToken token = mMqttClient.connect(getConnectionOpt());

            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    mMqttClient.setBufferOpts(getDisconnectedOpt());

                    Log.d(TAG, "Success connect");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    Log.d(TAG, "Failure connect" + exception.toString());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(@NonNull MqttAndroidClient client) throws MqttException {
        IMqttToken mqttToken = client.disconnect();

        mqttToken.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                Log.d(TAG, "disconnected 성공");
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                Log.d(TAG, "disconnected 실패 : " + throwable.toString());
            }
        });
    }

    public MqttAndroidClient getClient() {
        return mMqttClient;
    }

    public void publish(@NonNull MqttAndroidClient client, @NonNull String topic, int qos, @NonNull String msg)
            throws MqttException, UnsupportedEncodingException {

        byte[] payload = msg.getBytes("UTF-8");
        MqttMessage message = new MqttMessage(payload);

        message.setQos(qos);
        message.setRetained(true);

        client.publish(topic, message);
    }

    public void subscribe(@NonNull MqttAndroidClient client, @NonNull final String topic, int qos) throws MqttException {
        IMqttToken token = client.subscribe(topic, qos);

        token.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                Log.d(TAG, "Subscribe Successfully " + topic);
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                Log.e(TAG, "Subscribe Failed " + topic);
            }
        });
    }

    public void unSubscribe(@NonNull MqttAndroidClient client, @NonNull final String topic) throws MqttException {
        IMqttToken token = client.unsubscribe(topic);

        token.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                Log.d(TAG, "UnSubscribe 성공 : " + topic);
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                Log.e(TAG, "UnSubscribe 실패 : " + topic);
            }
        });
    }

    /**
     * cleanSession : 연결이 끊어졌을 때 client의 subscription 정보 보존 유/무
     * true - 정보 reset
     * false - 정보 유지
     *
     * AutomaticReconnect : 연결이 끊어졌을 때 reconnect 시도 유/무
     * true - reconnect 시도
     * false - reconnect 시도 x
     */
    @NonNull
    private MqttConnectOptions getConnectionOpt() {
        MqttConnectOptions connectOpt = new MqttConnectOptions();

        connectOpt.setAutomaticReconnect(true);
        connectOpt.setCleanSession(false);

        return connectOpt;
    }

    /**
     * disconnected 됐을 때, 전달 될 message
     * 아직 정확한 용도 모르겠음!
     */
    @NonNull
    private DisconnectedBufferOptions getDisconnectedOpt() {
        DisconnectedBufferOptions disconnectOpt = new DisconnectedBufferOptions();

        disconnectOpt.setBufferSize(100);
        disconnectOpt.setBufferEnabled(true);
        disconnectOpt.setPersistBuffer(false);
        disconnectOpt.setDeleteOldestMessages(false);

        return disconnectOpt;
    }
}
