package com.zhaoliang.androiddemo.day04.imageviewer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.zhaoliang.androiddemo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 带缓存的网络图片查看器
 */
public class ImageViewerActivity extends Activity {

    private static final String TAG = "ImageViewerActivity";
    private String path = "http://pic.mmfile.net/2016/02/21c01.jpg";
    private ImageView iv_image;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        iv_image = (ImageView) findViewById(R.id.iv_image);

        file = new File(getCacheDir(), getFileName());
        if (file.exists()) {
            iv_image.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            Log.i(TAG, "从缓存中读取的");
        } else {
            new ShowImageTask().execute(path);
            Log.i(TAG, "从网络下载的");
        }
    }

    private String getFileName() {
        return path.substring(path.lastIndexOf("/") + 1);
    }

    class ShowImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            String imagePath = params[0];
            HttpURLConnection connection = null;
            FileOutputStream fileOutputStream = null;
            try {
                URL url = new URL(imagePath);
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestMethod("GET");
                connection.connect();
                if (connection.getResponseCode() == 200) {
                    fileOutputStream = new FileOutputStream(file);
                    InputStream inputStream = connection.getInputStream();
                    byte[] b = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(b)) != -1) {
                        fileOutputStream.write(b, 0, len);
                    }
                    return BitmapFactory.decodeFile(file.getAbsolutePath());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 释放资源
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                    connection = null;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                iv_image.setImageBitmap(bitmap);
            }
        }
    }
}
