package com.example.ui_control.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.ui_control.R;

public class SearchViewActivity extends AppCompatActivity {

    private  String[] mStrings = new String[]{"aaa","bbb","ccc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        final ListView lv = findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,mStrings));

        //设置ListView启用过滤器
        lv.setTextFilterEnabled(true);
        SearchView sv = findViewById(R.id.sv);
        //设置该SearchView默认是否自动缩小为图标
        sv.setIconifiedByDefault(false);
        //设置该SearchView显示搜索按钮
        sv.setSubmitButtonEnabled(true);
        //设置该SearchView默认显示的提示文本
//        sv.setQueryHint("查找");
        //为该SearchView组件设置事件监听器
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //实际应用中应该该方法内执行实际查询
                Toast.makeText(SearchViewActivity.this,"您的选择是"+query,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    lv.clearTextFilter();
                }else {
                    lv.setFilterText(newText);
                }
                return true;
            }
        });
    }
}