package com.zhaoliang.androiddemo.day06.activitylunchmode.lunchmode;

import android.app.Activity;
import android.os.Bundle;

import com.zhaoliang.androiddemo.R;

public class SingleTaskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
    }
}
