package com.zhaoliang.androiddemo.day02.layout.tablelayout;

import android.app.Activity;
import android.os.Bundle;

import com.zhaoliang.androiddemo.R;

/**
 * 需求：使用表格布局
 * 思路：
 * 1.编写布局文件
 * 2.编写对应的Activity类
 */
public class TableLayoutDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout_demo);
    }
}
