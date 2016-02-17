package com.zhaoliang.androiddemo.day05.asnychttpclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.zhaoliang.androiddemo.R;

import cz.msebera.android.httpclient.Header;

/**
 * 使用AsnycHttpClient
 */
public class AsnycHttpClientActivity extends Activity implements View.OnClickListener {

    private String path = "http://10.122.2.31:8080/login";

    private EditText et_username;
    private EditText et_password;
    private Button btn_get;
    private Button btn_post;

    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asnyc_http_client);

        findView();

        setOnClickListner();

        client = new AsyncHttpClient();
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
                String getUrl = path + "?username=" + et_username.getText().toString() + "&password=" + et_password.getText().toString();
                client.get(getUrl, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Toast.makeText(AsnycHttpClientActivity.this, responseString, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_post:
                RequestParams params = new RequestParams();
                params.add("username", et_username.getText().toString());
                params.add("password", et_password.getText().toString());
                client.post(path, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Toast.makeText(AsnycHttpClientActivity.this, responseString, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
