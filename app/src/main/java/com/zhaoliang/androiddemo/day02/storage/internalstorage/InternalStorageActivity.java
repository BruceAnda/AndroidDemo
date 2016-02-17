package com.zhaoliang.androiddemo.day02.storage.internalstorage;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day02.storage.util.StorageUtil;

import java.io.File;
import java.util.Map;

public class InternalStorageActivity extends Activity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_remeber;
    private Button btn_login;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        path = getFilesDir() + File.separator + "internal.txt";

        findView();

        readAccount();

        setOnclickListener();
    }

    /**
     * 设置点击事件
     */
    private void setOnclickListener() {
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

    /**
     * 读取账户信息
     */
    private void readAccount() {
        Map<String, String> formStorage = StorageUtil.getFormStorage(path);
        if (formStorage != null) {
            et_username.setText(formStorage.get("username"));
            et_password.setText(formStorage.get("password"));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    et_username.setText("账户不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    et_password.setText("密码不能为空！");
                    return;
                }

                if (cb_remeber.isChecked()) {
                    boolean b = StorageUtil.saveToStorage(path, username, password);
                    if (b) {
                        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
