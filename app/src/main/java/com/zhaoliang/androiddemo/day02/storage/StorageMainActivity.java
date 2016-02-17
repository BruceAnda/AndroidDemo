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

public class StorageMainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_main);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{"获取sd大小", "使用内部存储", "使用外部存储", "使用SharedPerferences"}));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, SdSizeActivity.class));
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
