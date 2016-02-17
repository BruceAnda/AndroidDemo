package com.zhaoliang.androiddemo.day04.htmlviewer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.zhaoliang.androiddemo.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTMLViewerActivity extends Activity {

    String path = "https://www.baidu.com/";

    private TextView tv_html;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htmlviewer);

        tv_html = (TextView) findViewById(R.id.tv_html);

        new DownLoadHtmlTask().execute(path);
    }

    class DownLoadHtmlTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String htmlUrl = params[0];
            HttpURLConnection connection = null;
            try {
                URL url = new URL(htmlUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);
                connection.connect();
                if (connection.getResponseCode() == 200) {
                    return inputStreamToString(connection.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 输入流到字符串
         *
         * @param inputStream
         * @return
         * @throws IOException
         */
        private String inputStreamToString(InputStream inputStream) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, len);
            }
            return new String(byteArrayOutputStream.toByteArray());
        }

        @Override
        protected void onPostExecute(String s) {
            tv_html.setText(s);
        }
    }
}
