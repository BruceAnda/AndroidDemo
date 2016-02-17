package com.zhaoliang.androiddemo.day01.helloworld;

import android.app.Activity;
import android.os.Bundle;

import com.zhaoliang.androiddemo.R;

/**
 * 需求：Android入门HelloWorld
 * 思路：
 * 1.创建一个Activity
 * 2.在布局中创建一个Textview用来显示HelloWorld的文字
 */
public class HelloWorldActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置要显示的内容
        setContentView(R.layout.activity_hello_world);
    }
}
