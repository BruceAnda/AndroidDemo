package com.zhaoliang.androiddemo.day03;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day03.dialog.DialogActivity;
import com.zhaoliang.androiddemo.day03.junit.JunitActivity;
import com.zhaoliang.androiddemo.day03.listview.ListViewActivity;
import com.zhaoliang.androiddemo.day03.sqlite.SqliteActivity;

/**
 * Android数据存储和界面展示（下）
 */
public class MainDay03Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_day03);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{"Junit测试", "Sqlite数据库", "ListView", "Dialog"}));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, JunitActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, SqliteActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, DialogActivity.class));
                break;
        }
    }
}
