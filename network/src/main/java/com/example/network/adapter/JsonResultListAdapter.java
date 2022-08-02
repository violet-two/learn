package com.example.network.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.network.R;
import com.example.network.RetrofitActivity;
import com.example.network.domain.JsonResult;

import java.util.ArrayList;
import java.util.List;

public class JsonResultListAdapter extends RecyclerView.Adapter<JsonResultListAdapter.InnerHolder> {
    private static final String TAG = "JsonResultListAdapter";
    private List<JsonResult.DataBean> data = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_text,parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //绑定数据
        ImageView iv_item  = holder.itemView.findViewById(R.id.iv_item);
        TextView tv_title  = holder.itemView.findViewById(R.id.tv_title);
        TextView tv_userName  = holder.itemView.findViewById(R.id.tv_userName);

        JsonResult.DataBean dataBean = data.get(position);
        Log.d(TAG, "onBindViewHolder: "+dataBean.getCover());
        String url = "http://10.0.2.2:9102"+dataBean.getCover();
        Log.d(TAG, "url: "+url);
        Glide.with(holder.itemView.getContext())
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_item);
        tv_title.setText(dataBean.getTitle());
        tv_userName.setText(dataBean.getUserName());
    }


    public void setData(JsonResult jsonResult){
        data.clear();
        data.addAll(jsonResult.getData());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder{
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
