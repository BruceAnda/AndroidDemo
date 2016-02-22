package com.zhaoliang.androiddemo.day06.secondactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.zhaoliang.androiddemo.R;

/**
 * 第二个Activity
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            System.out.println("--------" + data.toString());
        }
    }
}
