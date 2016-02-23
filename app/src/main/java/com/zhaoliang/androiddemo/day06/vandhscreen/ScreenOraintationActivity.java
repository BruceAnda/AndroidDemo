package com.zhaoliang.androiddemo.day06.vandhscreen;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.zhaoliang.androiddemo.R;

public class ScreenOraintationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_oraintation);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}
