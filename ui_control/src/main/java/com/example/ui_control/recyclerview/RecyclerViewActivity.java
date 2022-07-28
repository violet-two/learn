package com.example.ui_control.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ui_control.R;
import com.example.ui_control.adapter.ListViewAdapter;
import com.example.ui_control.bean.Datas;
import com.example.ui_control.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewActivity";
    private RecyclerView mList;
    private List<ItemBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view);
        //找到控件
        mList = (RecyclerView) this.findViewById(R.id.recycler_view);
        //准备数据
        initData();
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

        ListViewAdapter listViewAdapter = new ListViewAdapter(mData);

        mList.setAdapter(listViewAdapter);
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