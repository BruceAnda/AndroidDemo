package com.zhaoliang.androiddemo.day05.multithreadedhttp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhaoliang.androiddemo.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 多线程断点续传下载
 */
public class MultithreadedHttpActivity extends Activity {

    static int threadCount = 3;
    static int finishedThread = 0;
    int currentProgress;
    String fileName = "data.rar";
    String downloadUrl = "http://192.168.1.1:8080/file/data.rar";

    private ProgressBar progressBar;
    private TextView tvProgress;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tvProgress.setText((long) progressBar.getProgress() * 100 / progressBar.getMax() + "%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multithreaded_http);

        progressBar = (ProgressBar) findViewById(R.id.pb_progress);
        tvProgress = (TextView) findViewById(R.id.tv_progress);

    }

    public void download(View view) {
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(downloadUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    if (connection.getResponseCode() == 200) {
                        int contentLength = connection.getContentLength();
                        progressBar.setMax(contentLength);
                        File file = new File(Environment.getExternalStorageDirectory(), fileName);
                        // 生成临时文件
                        RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                        raf.setLength(contentLength);
                        raf.close();

                        int size = contentLength / threadCount;

                        for (int i = 0; i < threadCount; i++) {
                            int startIndex = i * size;
                            int endIndex = (i + 1) * size - 1;
                            if (i == threadCount - 1) {
                                endIndex = contentLength - 1;
                            }
                            new DownloadThread(startIndex, endIndex, i);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }.start();
    }

    class DownloadThread extends Thread {

        int startIndex;
        int endIndex;
        int threadId;

        public DownloadThread(int startIndex, int endIndex, int threadId) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.threadId = threadId;
        }

        @Override
        public void run() {
            try {
                File progressFile = new File(Environment.getExternalStorageDirectory(), threadId + ".txt");
                // 判断临时文件是否存在
                if (progressFile.exists()) {
                    FileInputStream fileInputStream = new FileInputStream(progressFile);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                    int lastProgress = Integer.parseInt(reader.readLine());
                    startIndex += lastProgress;

                    currentProgress += lastProgress;
                    progressBar.setProgress(currentProgress);

                    handler.sendEmptyMessage(1);
                    fileInputStream.close();
                }

                HttpURLConnection connection = null;
                URL url = new URL(downloadUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);

                if (connection.getResponseCode() == 206) {
                    InputStream inputStream = connection.getInputStream();
                    byte[] b = new byte[1024];
                    int len = 0;
                    int total = 0;
                    File file = new File(Environment.getExternalStorageDirectory(), fileName);
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
                    randomAccessFile.seek(startIndex);

                    while ((len = inputStream.read(b)) != -1) {
                        randomAccessFile.write(b, 0, len);

                        total += len;

                        currentProgress += len;
                        progressBar.setProgress(currentProgress);
                        handler.sendEmptyMessage(1);

                        RandomAccessFile progressRaf = new RandomAccessFile(progressFile, "rwd");
                        progressRaf.write(String.valueOf(total).getBytes());
                        progressRaf.close();
                    }

                    randomAccessFile.close();

                    finishedThread++;
                    synchronized (downloadUrl) {
                        if (finishedThread == threadCount) {
                            for (int i = 0; i < threadCount; i++) {
                                File f = new File(Environment.getExternalStorageDirectory(), i + ".txt");
                                f.delete();
                            }
                            finishedThread = 0;
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
