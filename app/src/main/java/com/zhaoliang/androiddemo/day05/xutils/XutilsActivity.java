package com.zhaoliang.androiddemo.day05.xutils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zhaoliang.androiddemo.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class XutilsActivity extends Activity {

    String downloadUrl = "http://192.168.43.167:8080/file/data.rar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutils);
    }

    public void download(View view) {
        RequestParams entity = new RequestParams(downloadUrl);
        entity.setSaveFilePath(getFilesDir() + File.separator + "data.rar");
        x.http().get(entity, new DownloadCallback() {
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                System.out.println(total + ":" + current + ":" + isDownloading);
            }

            @Override
            public void onFinished() {
                Toast.makeText(XutilsActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStarted() {
                Toast.makeText(XutilsActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

class DownloadCallback implements Callback.CommonCallback<File>, Callback.ProgressCallback<File>, Callback.Cancelable {

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void onWaiting() {

    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {

    }

    @Override
    public void onSuccess(File result) {

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}