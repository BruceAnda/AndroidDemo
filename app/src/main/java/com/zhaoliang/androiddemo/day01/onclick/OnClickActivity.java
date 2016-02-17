package com.zhaoliang.androiddemo.day01.onclick;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhaoliang.androiddemo.R;

/**
 * 需求:Android中点击事件的写法
 * 思路：
 * 1.编写布局界面
 * 2.编写对应的Activity
 * 3.编写点击事件的逻辑
 */
public class OnClickActivity extends Activity implements View.OnClickListener {

    private Button btn2, btn3, btn4; // 声明控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click);

        findView();
        setOnClickListner();
    }

    /**
     * 设置点击事件
     */
    private void setOnClickListner() {
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OnClickActivity.this, "第三种点击事件的写法", Toast.LENGTH_SHORT).show();
            }
        });
        btn4.setOnClickListener(new BtnOnclick());
    }

    /**
     * 查找控件
     */
    private void findView() {
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
    }

    /**
     * 第一种点击事件的写法
     *
     * @param view
     */
    public void btnClick(View view) {
        Toast.makeText(this, "第一种点击事件的写法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "第二种点击事件的写法", Toast.LENGTH_SHORT).show();
    }

    class BtnOnclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(OnClickActivity.this, "第四种点击事件的写法", Toast.LENGTH_SHORT).show();
        }
    }
}
