package com.naver.npns.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHelper {

    private final String TAG = HttpHelper.class.getCanonicalName();

    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient mClient;

    private String mHostUrl;

    public HttpHelper() {
        mClient = new OkHttpClient();
    }

    public void post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json; charset=utf-8")
                .build();

        mClient.newCall(request).enqueue(callbackAfterRequest);
    }

    private Callback callbackAfterRequest = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d(TAG, "response fail!");

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.d(TAG, "response success!");
        }
    };
}
