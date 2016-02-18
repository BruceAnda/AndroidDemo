package com.zhaoliang.androiddemo.day02.layout.linearlayout;

import android.app.Activity;
import android.os.Bundle;

import com.zhaoliang.androiddemo.R;

/**
 * 需求：使用线性布局
 * 思路：
 * 1.编写布局文件
 * 2.编写对应的Activity类，当启动Activity的时候显示对应的布局
 */
public class LinearLayoutDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_demo);
    }
}
