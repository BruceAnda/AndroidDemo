package com.zhaoliang.androiddemo;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.zhaoliang.androiddemo.day01.MainDay01Activity;
import com.zhaoliang.androiddemo.day02.MainDay02Activity;
import com.zhaoliang.androiddemo.day03.MainDay03Activity;
import com.zhaoliang.androiddemo.day04.MainDay04Activity;
import com.zhaoliang.androiddemo.day05.MainDay05Activity;
import com.zhaoliang.androiddemo.day06.MainDay06Activity;
import com.zhaoliang.androiddemo.day07.MainDay07Activity;
import com.zhaoliang.androiddemo.day08.MainDay08Activity;
import com.zhaoliang.androiddemo.day09.MainDay09Activity;
import com.zhaoliang.androiddemo.day10.MainDay10Activity;
import com.zhaoliang.androiddemo.day11.MainDay11Activity;
import com.zhaoliang.androiddemo.day12.MainDay12Activity;
import com.zhaoliang.androiddemo.day13.MainDay13Activity;
import com.zhaoliang.androiddemo.day14.MainDay14Activity;
import com.zhaoliang.androiddemo.day15.MainDay15Activity;

/**
 * AndroidDemo主页面
 */
public class MainActivity extends ListActivity {

    private static final int EXTERNAL_STORAGE_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{
                "Android应用开发-快速入门",
                "Android应用开发-数据存储和界面展现",
                "Android应用开发-数据存储和界面展现",
                "Android应用开发-网络编程",
                "Android应用开发-网络编程",
                "Android应用开发-页面跳转和数据传递",
                "Android应用开发-广播和服务",
                "Android应用开发-广播和服务",
                "Android应用开发-多媒体编程",
                "Android应用开发-内容提供者",
                "Android应用开发-新特性和知识点回顾",
                "Android项目开发—C语言",
                "Android项目开发—JNI与底层调用",
                "Android项目开发—JNI与底层调用",
                "Android项目开发—代码版本管理和实战"}));

        requestPermission();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, MainDay01Activity.class));
                break;
            case 1:
                startActivity(new Intent(this, MainDay02Activity.class));
                break;
            case 2:
                startActivity(new Intent(this, MainDay03Activity.class));
                break;
            case 3:
                startActivity(new Intent(this, MainDay04Activity.class));
                break;
            case 4:
                startActivity(new Intent(this, MainDay05Activity.class));
                break;
            case 5:
                startActivity(new Intent(this, MainDay06Activity.class));
                break;
            case 6:
                startActivity(new Intent(this, MainDay07Activity.class));
                break;
            case 7:
                startActivity(new Intent(this, MainDay08Activity.class));
                break;
            case 8:
                startActivity(new Intent(this, MainDay09Activity.class));
                break;
            case 9:
                startActivity(new Intent(this, MainDay10Activity.class));
                break;
            case 10:
                startActivity(new Intent(this, MainDay11Activity.class));
                break;
            case 11:
                startActivity(new Intent(this, MainDay12Activity.class));
                break;
            case 12:
                startActivity(new Intent(this, MainDay13Activity.class));
                break;
            case 13:
                startActivity(new Intent(this, MainDay14Activity.class));
                break;
            case 14:
                startActivity(new Intent(this, MainDay15Activity.class));
                break;
        }
    }

    public void requestPermission() {
        //判断当前Activity是否已经获得了该权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            //如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "please give me the permission", Toast.LENGTH_SHORT).show();
            } else {
                //进行权限请求
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE},
                        EXTERNAL_STORAGE_REQ_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_STORAGE_REQ_CODE: {
                // 如果请求被拒绝，那么通常grantResults数组为空
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //申请成功，进行相应操作
                } else {
                    //申请失败，可以继续向用户解释。
                }
                return;
            }
        }
    }
}
