package com.zhaoliang.androiddemo.day01;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day01.dialer.DialerActivity;
import com.zhaoliang.androiddemo.day01.helloworld.HelloWorldActivity;
import com.zhaoliang.androiddemo.day01.onclick.OnClickActivity;
import com.zhaoliang.androiddemo.day01.smssender.SmsSenderActivity;

/**
 * Day01主页面
 * Day01任务：
 * 1.完成HelloWorld程序
 * 2.完成电话拨号器程序
 * 3.完成短信发送器程序
 * 4.总结Android中点击事件的写法
 */
public class MainDay01Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_day01);

        // 给列表填充数据
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{"HelloWorld", "电话拨号器", "短信发送器", "按钮点击事件的写法"}));
    }

    /**
     * 列表项点击事件
     *
     * @param l
     * @param v
     * @param position
     * @param id
     */
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, HelloWorldActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, DialerActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, SmsSenderActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, OnClickActivity.class));
                break;
        }
    }
}
