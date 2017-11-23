package com.naver.npns_demo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.naver.npns_demo.R;
import com.naver.npns_demo.service.MainService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getCanonicalName();

    @BindView(R.id.http_req_btn)
    Button mHttpRequestBtn;
    @BindView(R.id.server_run_btn)
    Button mServerRunBtn;

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

        mHttpRequestBtn.setOnClickListener(v -> mPresenter.requestPost());

        mServerRunBtn.setOnClickListener(v -> mPresenter.runServer());
        Intent intent = new Intent(MainActivity.this, MainService.class);
        startService(intent);
    }
}

