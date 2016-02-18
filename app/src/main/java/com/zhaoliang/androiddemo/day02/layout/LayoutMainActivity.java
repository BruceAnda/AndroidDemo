package com.zhaoliang.androiddemo.day02.layout;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day02.layout.absolutelayout.AbsoluteLayoutDemo;
import com.zhaoliang.androiddemo.day02.layout.framelayout.FrameLayoutDemo;
import com.zhaoliang.androiddemo.day02.layout.linearlayout.LinearLayoutDemo;
import com.zhaoliang.androiddemo.day02.layout.linearlayout.LinearLayoutDmeo2;
import com.zhaoliang.androiddemo.day02.layout.relativelayout.RelativeLayoutDemo;
import com.zhaoliang.androiddemo.day02.layout.relativelayout.RelativeLayoutDemo2;
import com.zhaoliang.androiddemo.day02.layout.tablelayout.TableLayoutDemo;

/**
 * 需求：使用Android中常见的布局
 * 思路：
 * 1.编写布局文件
 * 2.编写对应的Activity
 */
public class LayoutMainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_main);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{
                "LinearLayoutDemo",
                "LinearLayoutDemo2",
                "RelativeLayoutDemo",
                "RelativeLayoutDemo2",
                "FrameLayoutDemo",
                "TableLayoutDemo",
                "AbsoluteLayoutDemo"}));
    }

    /**
     * 列表项点击事件
     *
     * @param l
     * @param v
     * @param position
     * @param id
     */
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, LinearLayoutDemo.class));       // 界面跳转
                break;
            case 1:
                startActivity(new Intent(this, LinearLayoutDmeo2.class));
                break;
            case 2:
                startActivity(new Intent(this, RelativeLayoutDemo.class));
                break;
            case 3:
                startActivity(new Intent(this, RelativeLayoutDemo2.class));
                break;
            case 4:
                startActivity(new Intent(this, FrameLayoutDemo.class));
                break;
            case 5:
                startActivity(new Intent(this, TableLayoutDemo.class));
                break;
            case 6:
                startActivity(new Intent(this, AbsoluteLayoutDemo.class));
                break;
        }
    }
}
