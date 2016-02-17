package com.zhaoliang.androiddemo.day02.logcat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.zhaoliang.androiddemo.R;

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
