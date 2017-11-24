package com.naver.npns.ui.main;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naver.npns.callback.OnMsgIncomedListener;
import com.naver.npns.Global;
import com.naver.npns.R;
import com.naver.npns.message.Message;
import com.naver.npns.model.MessageData;

import java.util.ArrayList;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnMsgIncomedListener {
    private static final String TAG = MainListAdapter.class.getCanonicalName();
    private List<MessageData> mMessageList = new ArrayList<>();

    public MainListAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MessageData item = mMessageList.get(position);

        ((CustomViewHolder) viewHolder).titleText.setText(item.getTitle());
        ((CustomViewHolder) viewHolder).bodyText.setText(item.getBody());
        ((CustomViewHolder) viewHolder).timeText.setText(item.getTime());

        viewHolder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public void onIncomedListener(Message msg) {
        //Todo : 메세지가 리스트에 들어갈수 있게 하는 로직 작성
        String title = msg.getTitle();
        String body = msg.getMessage();
        String time = Global.timeHelper.getTime();

        mMessageList.add(0, new MessageData(title, body, time));
        notifyDataSetChanged();
        Log.d(TAG, "Messeage Title" + msg.getTitle());
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView titleText, bodyText, timeText;

        public CustomViewHolder(View view) {
            super(view);

            titleText = view.findViewById(R.id.msg_title_txt);
            bodyText = view.findViewById(R.id.msg_body_txt);
            timeText = view.findViewById(R.id.time_txt);
        }
    }
}
