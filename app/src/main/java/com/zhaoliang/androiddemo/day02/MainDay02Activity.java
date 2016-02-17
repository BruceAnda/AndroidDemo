package com.zhaoliang.androiddemo.day02;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day02.layout.LayoutMainActivity;
import com.zhaoliang.androiddemo.day02.logcat.LogcatActivity;
import com.zhaoliang.androiddemo.day02.storage.StorageMainActivity;
import com.zhaoliang.androiddemo.day02.xml.XMLActivity;

public class MainDay02Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_day02);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{"常见布局", "Logcat", "存储数据", "读写XML"}));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, LayoutMainActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, LogcatActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, StorageMainActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, XMLActivity.class));
                break;
        }
    }
}
