package com.zhaoliang.androiddemo.day01.dialer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zhaoliang.androiddemo.R;

/**
 * 需求：电话拨号器
 * 思路：
 * 1.编写界面
 * 2.编写对应Activity代码
 * 3.编写打电话的逻辑
 */
public class DialerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);
    }

    /**
     * 拨打电话按钮点击会调用此方法
     *
     * @param view 对应拨打电话的按钮
     */
    public void callPhone(View view) {
        // 1.添加拨打电话的权限
        // 2.获取电话号码
        String phoneNum = ((EditText) findViewById(R.id.et_phone)).getText().toString().trim();
        // 3.拨打电话
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNum));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "没有打电话的权限", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }
}
