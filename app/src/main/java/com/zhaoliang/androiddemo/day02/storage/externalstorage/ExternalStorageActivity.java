package com.zhaoliang.androiddemo.day02.storage.externalstorage;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day02.storage.util.StorageUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 需求：使用外部存储保存数据
 * 思路：
 * 1.外部存储路径指的是SD卡路径，可以通过Environment.getExternalStorageDirectory()来获得
 * 2.编写布局
 * 3.编写对应的Activity
 * 4.获取界面数据
 * 5.在外部存储中创建文件
 * 6.写入外部存储数据
 */
public class ExternalStorageActivity extends Activity implements View.OnClickListener {

    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;

    /**
     * 声明控件
     */
    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_remeber;
    private Button btn_login;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "external.txt";       // 外部存储路径

        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        findView();

        initListener();

        readAccount();
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

    /**
     * 设置点击事件
     */
    private void initListener() {
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
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    }
                    boolean b = StorageUtil.saveToStorage(path, username, password);
                    if (b) {
                        Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
            } else {
                // Permission Denied
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
