package com.zhaoliang.androiddemo.day06.jumpactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day06.secondactivity.SecondActivity;

/**
 * Activity的跳转
 */
public class JumpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
    }

    /**
     * 跳转至打电话的Activity
     * 跳转到其他应用的Activity
     * 通过指定Action和data
     *
     * @param view
     */
    public void click1(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:110"));
        startActivity(intent);
    }

    /**
     * 显示跳转到第二个Activity
     * 通过指定类名跳转
     *
     * @param view
     */
    public void click2(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    public void click3(View view) {

    }

    public void click4(View view) {

    }

    public void click5(View view) {

    }

    public void click6(View view) {

    }

    public void click7(View view) {

    }
}
