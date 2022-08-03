package com.example.ui_control.popupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

import com.example.ui_control.R;

public class SubMenuActivity extends AppCompatActivity {

    private static final int  FONT_10 = 0X111;
    private static final int  FONT_12 = 0X112;
    private static final int  FONT_14 = 0X113;
    private static final int  FONT_16 = 0X114;
    private static final int  FONT_18 = 0X115;

    private static final int  PLAIN_ITEM = 0X11b;

    private static final int  FONT_RED = 0X116;
    private static final int  FONT_BLUE = 0X117;
    private static final int  FONT_GREEN = 0X118;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        textView = findViewById(R.id.test);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu fontMenu = menu.addSubMenu("字体大小");
        fontMenu.setIcon(R.drawable.img);
        fontMenu.setHeaderIcon(R.drawable.img);
        fontMenu.setHeaderTitle("选择字体大小");
        fontMenu.add(0,FONT_10,0,"10号字体");
        fontMenu.add(0,FONT_12,0,"12号字体");
        fontMenu.add(0,FONT_14,0,"14号字体");
        fontMenu.add(0,FONT_16,0,"16号字体");
        fontMenu.add(0,FONT_18,0,"18号字体");
        menu.add(0,PLAIN_ITEM,0,"普通菜单项");

        SubMenu colorMenu = menu.addSubMenu("字体颜色");
        colorMenu.setIcon(R.drawable.img);
        colorMenu.setHeaderIcon(R.drawable.img);
        colorMenu.setHeaderTitle("选择字体颜色");
        colorMenu.add(0,FONT_RED,0,"红色");
        colorMenu.add(0,FONT_BLUE,0,"蓝色");
        colorMenu.add(0,FONT_GREEN,0,"绿色");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case FONT_10:textView.setTextSize(10*2);
            break;
            case FONT_12:textView.setTextSize(12*2);
                break;
            case FONT_14:textView.setTextSize(14*2);
                break;
            case FONT_16:textView.setTextSize(16*2);
                break;
            case FONT_18:textView.setTextSize(18*2);
                break;
            case FONT_RED:textView.setTextColor(Color.RED);
                break;
            case FONT_GREEN:textView.setTextColor(Color.GREEN);
                break;
            case FONT_BLUE:textView.setTextColor(Color.BLUE);
                break;
        }
        return true;
    }
}