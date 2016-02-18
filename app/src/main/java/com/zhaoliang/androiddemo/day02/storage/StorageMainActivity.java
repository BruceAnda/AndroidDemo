package com.zhaoliang.androiddemo.day02.storage;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day02.storage.externalstorage.ExternalStorageActivity;
import com.zhaoliang.androiddemo.day02.storage.internalstorage.InternalStorageActivity;
import com.zhaoliang.androiddemo.day02.storage.sdsize.SdSizeActivity;
import com.zhaoliang.androiddemo.day02.storage.sharedperferences.SharedPerferencesActivity;

/**
 * 需求：在Android设备中存储数据
 * 思路：
 * 1.编写布局文件
 * 2.编写对应的Activity类
 */
public class StorageMainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_main);

        /*
        给列表项填充数据
         */
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{"获取sd大小", "使用内部存储", "使用外部存储", "使用SharedPerferences"}));
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
                startActivity(new Intent(this, SdSizeActivity.class));      // 切换页面
                break;
            case 1:
                startActivity(new Intent(this, InternalStorageActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, ExternalStorageActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, SharedPerferencesActivity.class));
                break;
        }
    }
}
