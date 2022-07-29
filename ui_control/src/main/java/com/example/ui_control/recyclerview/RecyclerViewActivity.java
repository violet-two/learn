package com.example.ui_control.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ui_control.R;
import com.example.ui_control.adapter.ListViewAdapter;
import com.example.ui_control.bean.Datas;
import com.example.ui_control.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewActivity";
    public RecyclerView mList;
    public List<ItemBean> mData;
    public SwipeRefreshLayout refresh_layout;
    public ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view);
        //找到控件
        mList = this.findViewById(R.id.recycler_view);
        refresh_layout = this.findViewById(R.id.refresh_layout);
        //准备数据
        initData();
        handlerDownPullUpdate();
    }

    private void handlerDownPullUpdate() {
        refresh_layout.setEnabled(true);
        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //在这里面去执行刷新数据的操作
                ItemBean data = new ItemBean();
                data.title="我是新添加的数据";
                data.icon=R.mipmap.pic_08;
                mData.add(0,data);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //更新列表
                        listViewAdapter.notifyDataSetChanged();
                        //刷新停止
                        refresh_layout.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < Datas.icons.length; i++) {
            ItemBean data = new ItemBean();
            data.icon = Datas.icons[i];
            data.title = "第"+i+"条目";
            mData.add(data);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mList.setLayoutManager(linearLayoutManager);

        listViewAdapter = new ListViewAdapter(mData);

        mList.setAdapter(listViewAdapter);

        if(listViewAdapter instanceof ListViewAdapter ){
            ((ListViewAdapter)listViewAdapter).setOnRefreshListener(new ListViewAdapter.OnRefreshListener() {
                @Override
                public void onPullRefresh(ListViewAdapter.loaderMoreHolder loaderMoreHolder) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Random random = new Random();
                            if(random.nextInt()%2==0){
                                //这里加载数据，同样需要在子线程中完成
                                ItemBean data = new ItemBean();
                                data.title="我是新添加的数据";
                                data.icon=R.mipmap.pic_10;
                                mData.add(data);
                                //更新列表
                                listViewAdapter.notifyDataSetChanged();
                                //刷新停止
                                loaderMoreHolder.update(ListViewAdapter.loaderMoreHolder.LOADER_SATE_NORMAL);
                            }else{
                                loaderMoreHolder.update(ListViewAdapter.loaderMoreHolder.LOADER_SATE_RELOAD);
                            }

                        }
                    },1000);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.list_view_vertical_stander:
                Log.d(TAG, "list_view_vertical_stander: ");
                break;
            case R.id.list_view_vertical_reverse:
                Log.d(TAG, "list_view_vertical_reverse: ");
                break;
            case R.id.list_view_horizontal_stander:
                break;
            case R.id.list_view_horizontal_reverse:
                break;


            case R.id.grid_view_vertical_stander:
                Log.d(TAG, "grid_view_vertical_stander: ");
                break;
            case R.id.grid_view_vertical_reverse:
                break;
            case R.id.grid_view_horizontal_stander:
                break;
            case R.id.grid_view_horizontal_reverse:
                break;


            case R.id.stagger_view_vertical_stander:
                Log.d(TAG, "stagger_view_vertical_stander: ");
                break;
            case R.id.stagger_view_vertical_reverse:
                break;
            case R.id.stagger_view_horizontal_stander:
                break;
            case R.id.stagger_view_horizontal_reverse:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}