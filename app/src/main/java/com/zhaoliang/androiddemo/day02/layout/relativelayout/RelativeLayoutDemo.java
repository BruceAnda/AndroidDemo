package com.zhaoliang.androiddemo.day02.layout.relativelayout;

import android.app.Activity;
import android.os.Bundle;

import com.zhaoliang.androiddemo.R;

/**
 * 需求：使用相对布局
 * 思路：
 * 1.编写布局文件
 * 2.编写对应的Activity
 */
public class RelativeLayoutDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout_demo);
    }
}
