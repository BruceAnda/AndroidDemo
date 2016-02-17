package com.zhaoliang.androiddemo.day05.httpclientgetandpost;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhaoliang.androiddemo.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HttpClientActivity extends Activity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private Button btn_get;
    private Button btn_post;

    private String path = "http://10.122.2.31:8080/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client);

        findView();

        setOnClickListner();
    }

    /**
     * 设置点击事件
     */
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
                new GETTask().execute(path, et_username.getText().toString(), et_password.getText().toString());
                break;
            case R.id.btn_post:
                new POSTTask().execute(path, et_username.getText().toString(), et_password.getText().toString());
                break;
        }
    }

    /**
     * GET请求
     */
    class GETTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String getUrl = params[0] + "?username=" + params[1] + "&password=" + params[2];
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(getUrl);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == 200) {
                    return streamToString(response.getEntity().getContent());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(HttpClientActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 流到字符串的转换
     *
     * @param content
     * @return
     * @throws IOException
     */
    private String streamToString(InputStream content) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = content.read(b)) != -1) {
            outputStream.write(b, 0, len);
        }
        return new String(outputStream.toByteArray());
    }

    /**
     * POST请求
     */
    class POSTTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(params[0]);

            BasicNameValuePair basicNameValuePair = new BasicNameValuePair("username", params[1]);
            BasicNameValuePair basicNameValuePair1 = new BasicNameValuePair("password", params[2]);
            List<BasicNameValuePair> pairs = new ArrayList<>();
            pairs.add(basicNameValuePair);
            pairs.add(basicNameValuePair1);
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
                post.setEntity(entity);
                HttpResponse response = client.execute(post);
                if (response.getStatusLine().getStatusCode() == 200) {
                    return streamToString(response.getEntity().getContent());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(HttpClientActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
