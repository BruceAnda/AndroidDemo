package com.zhaoliang.androiddemo.day02.logcat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.zhaoliang.androiddemo.R;

/**
 * 需求：使用Android中Logcat输出日志
 * 思路：
 * 1.Android中提供了Log类来打印Log
 * 2.分别输出不同的log等级日志
 */
public class LogcatActivity extends Activity {

    private static final String TAG = "LogcatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logcat);

        Log.v(TAG, "Verbose");
        Log.d(TAG, "Debug");
        Log.i(TAG, "Info");
        Log.w(TAG, "Warn");
        Log.e(TAG, "Error");
    }
}
