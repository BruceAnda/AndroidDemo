package com.zhaoliang.androiddemo.day06;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day06.activitydataresult.ActivityResultActivity;
import com.zhaoliang.androiddemo.day06.activitylifecycle.ActivityLifecycleActivity;
import com.zhaoliang.androiddemo.day06.activitylunchmode.ActivityLunchModeActivity;
import com.zhaoliang.androiddemo.day06.jumpactivity.JumpActivity;
import com.zhaoliang.androiddemo.day06.jumpcatchdata.JumpCatchDataActivity;
import com.zhaoliang.androiddemo.day06.secondactivity.SecondActivity;
import com.zhaoliang.androiddemo.day06.vandhscreen.ScreenOraintationActivity;

public class MainDay06Activity extends ListActivity {

    private static final int REQUEST_CODE = R.layout.activity_main_day06;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_day06);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, new String[]{
                "创建第二个Activity",
                "Activity的跳转",
                "Activity在跳转时携带数据",
                "Activity的声明周期",
                "Activity的启动模式",
                "Activity横竖屏切换",
                "Activity获取返回值"
        }));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, JumpActivity.class));
                break;
            case 2:
                Intent intent = new Intent(this, JumpCatchDataActivity.class);
                intent.putExtra("name", "赵亮");
                intent.putExtra("sex", "男");
                intent.putExtra("address", "北京");
                startActivity(intent);
                break;
            case 3:
                startActivity(new Intent(this, ActivityLifecycleActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, ActivityLunchModeActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, ScreenOraintationActivity.class));
                break;
            case 6:
                startActivityForResult(new Intent(this, ActivityResultActivity.class), REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
        }
    }
}
