package com.example.ui_control.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_control.R;
import com.example.ui_control.bean.ItemBean;

import java.util.List;

public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected final List<ItemBean> mData;
//    private AdapterView.OnItemClickListener mOnItemClickListener;


    protected RecyclerViewBaseAdapter(List<ItemBean> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent, viewType);
        return new InnerHolder(view);
    }

    protected abstract View getSubView(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((InnerHolder) holder).setData(mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

//    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
//        //设置一个监听,其实,就是要设置一个接口,一个回调的接口
//        this.mOnItemClickListener = listener;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView mIcon;
        private TextView mTitle;
        private int mPosition;

        public InnerHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.icon);
            mTitle = (TextView) itemView.findViewById(R.id.title);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mOnItemClickListener != null) {
//                        mOnItemClickListener.onItemClick(mPosition);
//                    }
//                }
//            });
        }

            public void setData(ItemBean itemBean, int position) {
                this.mPosition = position;
                mIcon.setImageResource(itemBean.icon);
                mTitle.setText(itemBean.title);
            }
    }
}
