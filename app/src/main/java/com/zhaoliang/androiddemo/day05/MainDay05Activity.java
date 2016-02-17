package com.zhaoliang.androiddemo.day05;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day05.asnychttpclient.AsnycHttpClientActivity;
import com.zhaoliang.androiddemo.day05.httpclientgetandpost.HttpClientActivity;
import com.zhaoliang.androiddemo.day05.multithreadedhttp.MultithreadedHttpActivity;
import com.zhaoliang.androiddemo.day05.xutils.XutilsActivity;

public class MainDay05Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_day05);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{"HttpClient", "AsyncHttpClient", "MultithreadedHttp", "Xutils"}));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, HttpClientActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, AsnycHttpClientActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, MultithreadedHttpActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, XutilsActivity.class));
                break;
        }
    }
}
