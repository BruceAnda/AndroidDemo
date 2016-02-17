package com.zhaoliang.androiddemo.day01.smssender;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import com.zhaoliang.androiddemo.R;

/**
 * 需求：完成发送短信的功能
 * 思路：
 * 1.编写布局界面
 * 2.编写对应的Activity
 * 3.编写发送短信的逻辑
 */
public class SmsSenderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_sender);
    }

    public void sendSms(View view) {
        // 1.添加发送短信的权限
        // 2.获取电话号码
        String phoneNum = ((EditText) findViewById(R.id.et_phone)).getText().toString().trim();
        // 3.获取短信内容
        String smsContent = ((EditText) findViewById(R.id.et_sms_content)).getText().toString().trim();
        // 4.调用短信api发送短信
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendMultipartTextMessage(phoneNum, null, smsManager.divideMessage(smsContent), null, null);
    }
}
