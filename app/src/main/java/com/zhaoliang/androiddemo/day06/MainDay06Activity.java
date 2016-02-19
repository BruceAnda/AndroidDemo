package com.zhaoliang.androiddemo.day06;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day06.jumpactivity.JumpActivity;
import com.zhaoliang.androiddemo.day06.secondactivity.SecondActivity;

public class MainDay06Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_day06);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{
                "创建第二个Activity",
                "Activity的跳转",
                "Activity在跳转时携带数据",
                "Activity的声明周期",
                "Activity的启动模式",
                "Activity横竖屏切换",
                "Activity获取返回值"
        }));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, JumpActivity.class));
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
        }
    }
}
