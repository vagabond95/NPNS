package com.naver.npns.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.naver.npns.R;
import com.naver.npns.service.MainService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getCanonicalName();

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.http_req_btn)
    Button mHttpRequestBtn;

    private MainPresenter mPresenter;
    private MainListAdapter mListAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);

        ButterKnife.bind(this);
        initView();
    }

    public void initView() {

        mListAdapter = new MainListAdapter();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.scrollToPosition(0);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mListAdapter);

        mHttpRequestBtn.setOnClickListener(v -> mPresenter.requestPost());

        Intent intent = new Intent(MainActivity.this, MainService.class);
        startService(intent);

        initNetwork();
    }

    public void initNetwork() {
        mPresenter.runServer(mListAdapter);
    }
}

