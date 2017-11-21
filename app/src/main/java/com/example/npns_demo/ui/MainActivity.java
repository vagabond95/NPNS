package com.example.npns_demo.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.npns_demo.BaseApplication;
import com.example.npns_demo.R;
import com.example.npns_demo.client.MqttClientHelper;
import com.example.npns_demo.service.MessageService;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getCanonicalName();

    private Context mContext;

    @BindView(R.id.pub_msg_edt)
    EditText mPubMsgEditText;
    @BindView(R.id.pub_topic_edt)
    EditText mPubTopicEditText;
    @BindView(R.id.sub_topic_edt)
    EditText mSubTopicEditText;

    @BindView(R.id.pub_send_btn)
    Button mPubSendBtn;
    @BindView(R.id.sub_send_btn)
    Button mSubSendBtn;

    private MqttClientHelper mClientHelper;
    private MqttAndroidClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mContext = getApplicationContext();

        setClient();
        initView();
    }

    public void setClient() {
        mClientHelper = new MqttClientHelper(mContext, BaseApplication.BROKER_URL, "jhKim");
        mClient = mClientHelper.getClient();
    }

    public void initView() {
        mPubSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mPubMsgEditText.getText().toString();
                String topic = mPubTopicEditText.getText().toString();

                if (!message.isEmpty() && !topic.isEmpty()) {
                    try {
                        mClientHelper.publish(mClient, topic, 1, message);
                        mPubMsgEditText.setText("");
                        Toast.makeText(mContext, "publish message for "+topic, Toast.LENGTH_LONG).show();

                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(mContext,"check topic & message!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mSubSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = mSubTopicEditText.getText().toString();

                if(!topic.isEmpty()){
                    try {
                        mClientHelper.subscribe(mClient, topic, 1);
                        mSubTopicEditText.setText("");
                        Toast.makeText(mContext, "subscribe in "+topic,Toast.LENGTH_LONG).show();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(mContext, "check topic!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent = new Intent(MainActivity.this, MessageService.class);
        startService(intent);
    }
}

