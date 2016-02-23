package com.zhaoliang.androiddemo.day06.jumpcatchdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zhaoliang.androiddemo.R;

/**
 * 跳转的时候携带数据
 */
public class JumpCatchDataActivity extends Activity {

    private TextView tv_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_catch_data);

        tv_data = (TextView) findViewById(R.id.tv_data);

        Intent intent = getIntent();
        String text = intent.getStringExtra("name") + ":" + intent.getStringExtra("sex") + ":" + intent.getStringExtra("address");

        tv_data.setText(text);
    }
}
