package com.example.npns_demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.npns_demo.R;
import com.example.npns_demo.service.MainService;
import com.example.npns_demo.thrift.HelloService;
import com.example.npns_demo.thrift.HelloServiceAPI;
import com.example.npns_demo.util.UuidHelper;

import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.net.Socket;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getCanonicalName();

    @BindView(R.id.client_btn)
    Button mClientBtn;
    @BindView(R.id.server_btn)
    Button mServerBtn;

    private TCompactProtocol.Factory mProtocolFactory;
    private TAsyncClientManager mAsyncClientManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();
    }

    public void initView() {

        mClientBtn.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                Log.d(TAG, "uuid : "+ UuidHelper.getUuid(getApplicationContext()));

                TTransport transport = new TSocket("localhost",9091);
                try {
                    transport.open();
                    Log.d(TAG,"client : open!");
                } catch (TTransportException e) {
                    e.printStackTrace();
                }
                TProtocol protocol = new TBinaryProtocol(transport);
                HelloService.Client client = new HelloService.Client(protocol);
                try {
                    Log.d(TAG, "client : send!");
                    client.hello("client data!");
                } catch (TException e) {
                    e.printStackTrace();
                }
            });

            thread.start();
        });

        mServerBtn.setOnClickListener(v -> {

            Thread thread = new Thread(() -> {
                HelloServiceAPI hserver = new HelloServiceAPI();
                HelloService.Processor processor = new HelloService.Processor(hserver);
                TServerTransport transport = null;
                try {
                    transport = new TServerSocket(9091);
                } catch (TTransportException e) {
                    e.printStackTrace();
                }
                TServer server = new TSimpleServer(new TServer.Args(transport).processor(processor));
                Log.d(TAG,"start server");
                server.serve();
            });
            thread.start();
        });

        Intent intent = new Intent(MainActivity.this, MainService.class);
        startService(intent);
    }

    private HelloService.AsyncClient getAsyncClient() throws IOException {
        return new HelloService.AsyncClient(
                mProtocolFactory,
                mAsyncClientManager,
                new TNonblockingSocket("localhost", 7911));
    }
}

