package com.zhaoliang.androiddemo.day06.activitylunchmode;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day06.activitylunchmode.lunchmode.SingleInstanceActivity;
import com.zhaoliang.androiddemo.day06.activitylunchmode.lunchmode.SingleTaskActivity;
import com.zhaoliang.androiddemo.day06.activitylunchmode.lunchmode.SingleTopActivity;
import com.zhaoliang.androiddemo.day06.activitylunchmode.lunchmode.StandardActivity;

/**
 * Activity的启动模式
 */
public class ActivityLunchModeActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_lunch_mode);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{"Standard", "SingleTop", "SingleTask", "SingleInstance"}));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, StandardActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, SingleTopActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, SingleTaskActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, SingleInstanceActivity.class));
                break;
        }
    }
}
