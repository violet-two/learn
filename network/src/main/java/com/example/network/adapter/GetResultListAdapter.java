package com.example.network.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.network.R;
import com.example.network.domain.GetTextItem;

import java.util.ArrayList;
import java.util.List;

public class GetResultListAdapter extends RecyclerView.Adapter<GetResultListAdapter.InnerHolder> {

    private List<GetTextItem.DataBean> mData = new ArrayList<>();

    @NonNull
    @Override
    public GetResultListAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_text,parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GetResultListAdapter.InnerHolder holder, int position) {
        View itemView = holder.itemView;
        GetTextItem.DataBean dataBean = mData.get(position);
        TextView tv_title = itemView.findViewById(R.id.tv_title);
        tv_title.setText(dataBean.getTitle());

        TextView tv_userName = itemView.findViewById(R.id.tv_userName);
        tv_userName.setText(dataBean.getUserName());

        TextView tv_viewCount = itemView.findViewById(R.id.tv_viewCount);
        tv_viewCount.setText(String.valueOf(dataBean.getViewCount()));

        ImageView iv_item = itemView.findViewById(R.id.iv_item);
        Glide.with(itemView.getContext()).load("http://128.0.10.30:9102"+mData.get(position).getCover()).into(iv_item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(GetTextItem getTextItem) {
        mData.clear();
        mData.addAll(getTextItem.getData());
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder{
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
