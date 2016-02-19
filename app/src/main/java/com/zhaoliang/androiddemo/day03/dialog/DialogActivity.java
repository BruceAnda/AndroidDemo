package com.zhaoliang.androiddemo.day03.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhaoliang.androiddemo.R;

/**
 * Android中对话框的使用
 */
public class DialogActivity extends Activity implements View.OnClickListener {

    private Button btn_dialog, btn_single_dialog, btn_multiple_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findView();

        setOnclickListener();
    }

    private void setOnclickListener() {
        btn_dialog.setOnClickListener(this);
        btn_single_dialog.setOnClickListener(this);
        btn_multiple_dialog.setOnClickListener(this);
    }

    /**
     * 查找控件
     */
    private void findView() {
        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        btn_single_dialog = (Button) findViewById(R.id.btn_single_dialog);
        btn_multiple_dialog = (Button) findViewById(R.id.btn_multiple_dialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                new AlertDialog.Builder(this).setTitle("葵花宝典").setMessage("欲练此功，必先自宫").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "自宫成功", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "不能练功", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.btn_single_dialog:
                final String[] items = {"男", "女", "保密"};
                final int checkedItem = 0;
                new AlertDialog.Builder(this).setTitle("选择性别").setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).show();
                break;
            case R.id.btn_multiple_dialog:
                final String[] items1 = {"苹果", "香蕉", "葡萄", "梨子"};
                final boolean[] checkedItems = {false, false, false, false};
                new AlertDialog.Builder(this).setTitle("选择你喜欢的水果").setMultiChoiceItems(items1, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < items1.length; i++) {
                            stringBuffer.append(checkedItems[i] ? items1[i] + " " : "");
                        }
                        Toast.makeText(DialogActivity.this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", null).show();
                break;
        }
    }
}
