package com.zhaoliang.androiddemo.day02.storage.sharedperferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day02.storage.util.StorageUtil;

import java.util.Map;

public class SharedPerferencesActivity extends Activity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_remeber;
    private Button btn_login;
    private SharedPreferences sharedperferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_perferences);

        sharedperferences = getSharedPreferences("sharedperferences", Context.MODE_PRIVATE);

        findView();

        setListener();

        readAccount();
    }

    /**
     * 读取账户信息
     */
    private void readAccount() {
        Map<String, String> fromSharedPerferences = StorageUtil.getFromSharedPerferences(sharedperferences);
        et_username.setText(fromSharedPerferences.get("username"));
        et_password.setText(fromSharedPerferences.get("password"));
    }

    /**
     * 设置点击事件
     */
    private void setListener() {
        btn_login.setOnClickListener(this);
    }

    /**
     * 查找控件
     */
    private void findView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_remeber = (CheckBox) findViewById(R.id.cb_remember);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    et_username.setError("账户不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    et_password.setError("密码不能为空！");
                    return;
                }

                if (cb_remeber.isChecked()) {
                    boolean b = StorageUtil.saveToSharedPerferences(sharedperferences, username, password);
                    if (b) {
                        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }
}
