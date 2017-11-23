package com.naver.npns_demo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.naver.npns_demo.BaseApplication;
import com.naver.npns_demo.R;
import com.naver.npns_demo.model.PushReceiveService;
import com.naver.npns_demo.service.MainService;

import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getCanonicalName();

    @BindView(R.id.client_btn)
    Button mClientBtn;
    @BindView(R.id.server_btn)
    Button mServerBtn;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);

        ButterKnife.bind(this);
        initView();
    }

    public void initView() {

        mClientBtn.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                //Log.d(TAG, "uuid : " + UuidHelper.getUuid(getApplicationContext()));

                TTransport transport = new TSocket("localhost", BaseApplication.THRIFT_PORT);
                try {
                    transport.open();
                    Log.d(TAG, "client : open!");
                } catch (TTransportException e) {
                    e.printStackTrace();
                }
                TProtocol protocol = new TBinaryProtocol(transport);
                PushReceiveService.Client client = new PushReceiveService.Client(protocol);
                try {
                    Log.d(TAG, "client : "+client.ping());
                } catch (TException e) {
                    e.printStackTrace();
                }
            });

            thread.start();
        });

        mServerBtn.setOnClickListener(v -> {
            mPresenter.startServer();
        });
        Intent intent = new Intent(MainActivity.this, MainService.class);
        startService(intent);
    }
}

