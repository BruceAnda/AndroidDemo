package com.zhaoliang.androiddemo.day04.getandpost;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhaoliang.androiddemo.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetAndPostActivity extends Activity implements View.OnClickListener {

    private String path = "http://10.122.2.97:8080/login";

    private EditText et_username;
    private EditText et_password;
    private Button btn_get;
    private Button btn_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_and_post);

        findView();

        setOnClickListner();
    }

    private void setOnClickListner() {
        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
    }

    /**
     * 查找控件
     */
    private void findView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_post = (Button) findViewById(R.id.btn_post);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                new GETTAsk().execute(path, et_username.getText().toString(), et_password.getText().toString());
                break;
            case R.id.btn_post:
                new POSTTAsk().execute(path, et_username.getText().toString(), et_password.getText().toString());
                break;
        }
    }

    /**
     * HttpURLConnection GET方式提交数据
     */
    class GETTAsk extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            String getUrl = params[0] + "?username=" + params[1] + "&password=" + params[2];
            try {
                URL url = new URL(getUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.connect();
                if (connection.getResponseCode() == 200) {
                    return streamToString(connection.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                    connection = null;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(GetAndPostActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 流转换成字符串
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private String streamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(b)) != -1) {
            byteArrayOutputStream.write(b, 0, len);
        }
        return new String(byteArrayOutputStream.toByteArray());
    }

    /**
     * HttpUrlConnetioin POST方式提交数据
     */
    class POSTTAsk extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            String postUrl = params[0];
            String param = "username=" + params[1] + "&password=" + params[2];
            try {
                URL url = new URL(postUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setDoOutput(true);
                connection.getOutputStream().write(param.getBytes());
                connection.connect();
                if (connection.getResponseCode() == 200) {
                    return streamToString(connection.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                    connection = null;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(GetAndPostActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
