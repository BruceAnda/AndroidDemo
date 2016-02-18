package com.zhaoliang.androiddemo.day02.layout.framelayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.zhaoliang.androiddemo.R;

/**
 * 需求：使用帧布局
 * 思路：
 * 1.编写布局文件
 * 2.编写对应的Activity类
 */
public class FrameLayoutDemo extends Activity {

    private static final int UPDATE_BG = 1;
    private TextView[] tvs = new TextView[7];
    private int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.WHITE};
    private int currentColor = 0;
    private int delayMillis = 500;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            // tvs[0].setBackgroundColor(colors[currentColor % 7]);
            for (int i = 0; i < tvs.length; i++) {
                tvs[i].setBackgroundColor(colors[(i + currentColor) % tvs.length]);
            }
            currentColor++;
            mHandler.sendEmptyMessageDelayed(UPDATE_BG, delayMillis);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout_demo);

        tvs[0] = (TextView) findViewById(R.id.tv1);
        tvs[1] = (TextView) findViewById(R.id.tv2);
        tvs[2] = (TextView) findViewById(R.id.tv3);
        tvs[3] = (TextView) findViewById(R.id.tv4);
        tvs[4] = (TextView) findViewById(R.id.tv5);
        tvs[5] = (TextView) findViewById(R.id.tv6);
        tvs[6] = (TextView) findViewById(R.id.tv7);
        mHandler.sendEmptyMessageDelayed(UPDATE_BG, delayMillis);
    }
}
