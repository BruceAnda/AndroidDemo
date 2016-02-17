package com.zhaoliang.androiddemo.day04.openimageviewer;

import android.app.Activity;
import android.os.Bundle;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day04.openimageviewer.com.loopj.android.image.SmartImageView;

/**
 * 开源网络图片查看器
 */
public class OpenImageViewerActivity extends Activity {

    private SmartImageView smart_image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_image_viewer);

        smart_image_view = (SmartImageView) findViewById(R.id.smart_image_view);
        smart_image_view.setImageUrl("http://10.122.1.8:8080/images/img.jpg");
    }
}
