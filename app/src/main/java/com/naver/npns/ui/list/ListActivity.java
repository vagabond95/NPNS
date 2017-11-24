package com.naver.npns.ui.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naver.npns.R;
import com.naver.npns.message.Message;
import com.naver.npns.model.MessageInfo;
import com.naver.npns.model.OnMsgIncomedListener;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {

    private final String TAG = ListActivity.class.getCanonicalName();
    public static final String KEY_ADAPTER_EXTRA = "MSG_ADAPTER";

    @BindView(R.id.recycler_view)
    RecyclerView mPushListView;

    private RecyclerView.LayoutManager mLayoutManager;
    private MsgListAdapter mMsgListAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mMsgListAdpater = (MsgListAdapter) getIntent().getSerializableExtra(KEY_ADAPTER_EXTRA);

        ButterKnife.bind(this);

        initView();
    }

    public void initView() {
        mLayoutManager = new LinearLayoutManager(this);
        mPushListView.setLayoutManager(mLayoutManager);
        mPushListView.setHasFixedSize(true);
        mPushListView.scrollToPosition(0);
        mPushListView.setItemAnimator(new DefaultItemAnimator());
        mPushListView.setAdapter(mMsgListAdpater);

    }

    public static class MsgListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnMsgIncomedListener, Serializable {
        private static final String TAG = MsgListAdapter.class.getSimpleName();
        private ArrayList<MessageInfo> mMessageList = new ArrayList<>();

        public MsgListAdapter() {
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            MessageInfo item = mMessageList.get(position);

            ((CustomViewHolder) viewHolder).titleText.setText(item.getTitle());
            ((CustomViewHolder) viewHolder).bodyText.setText(item.getBody());

            viewHolder.itemView.setTag(item);
        }

        @Override
        public int getItemCount() {
            return mMessageList.size();
        }

        @Override
        public void onIncomedListener(Message msg) {
            //Todo : 메세지가 리스트에 들어갈수 있게 하는 로직 작성\
            Log.d(TAG, "Messeage" + msg.getTitle());
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {

            public TextView titleText, bodyText;

            public CustomViewHolder(View view) {
                super(view);

                titleText = view.findViewById(R.id.msg_title_txt);
                bodyText = view.findViewById(R.id.msg_body_txt);
            }
        }
    }
}
