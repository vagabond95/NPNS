package com.naver.npns.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.naver.npns.R;
import com.naver.npns.service.MainService;
import com.naver.npns.ui.list.ListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.naver.npns.ui.list.ListActivity.KEY_ADAPTER_EXTRA;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getCanonicalName();

    @BindView(R.id.http_req_btn)
    Button mHttpRequestBtn;
    @BindView(R.id.server_run_btn)
    Button mServerRunBtn;
    @BindView(R.id.show_list_btn)
    Button mShowListBtn;

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
        ListActivity.MsgListAdapter adapter = new ListActivity.MsgListAdapter();

        mHttpRequestBtn.setOnClickListener(v -> mPresenter.requestPost());

        mServerRunBtn.setOnClickListener(v -> mPresenter.runServer(adapter));

        mShowListBtn.setOnClickListener(v -> {
            Intent listIntent = new Intent(MainActivity.this, ListActivity.class);
            listIntent.putExtra(KEY_ADAPTER_EXTRA, adapter);
            startActivity(listIntent);
        });

        Intent intent = new Intent(MainActivity.this, MainService.class);
        startService(intent);
    }
}

