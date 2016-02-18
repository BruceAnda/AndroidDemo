package com.zhaoliang.androiddemo.day02.layout.absolutelayout;

import android.app.Activity;
import android.os.Bundle;

import com.zhaoliang.androiddemo.R;

/**
 * 需求：使用绝对布局
 * 思路：
 * 1.编写布局文件
 * 2.编写对应的Activity类
 */
public class AbsoluteLayoutDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absolute_layout_demo);
    }
}
