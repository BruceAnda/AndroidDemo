package com.zhaoliang.androiddemo.day04;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day04.getandpost.GetAndPostActivity;
import com.zhaoliang.androiddemo.day04.htmlviewer.HTMLViewerActivity;
import com.zhaoliang.androiddemo.day04.imageviewer.ImageViewerActivity;
import com.zhaoliang.androiddemo.day04.newsclient.NewsClientActivity;
import com.zhaoliang.androiddemo.day04.openimageviewer.OpenImageViewerActivity;

public class MainDay04Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_day04);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{"网络图片查看器", "开源图片查看器", "HTML源码查看器", "简易新闻客户端", "GET和POST方式提交数据"}));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, ImageViewerActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, OpenImageViewerActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, HTMLViewerActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, NewsClientActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, GetAndPostActivity.class));
                break;
        }
    }
}
