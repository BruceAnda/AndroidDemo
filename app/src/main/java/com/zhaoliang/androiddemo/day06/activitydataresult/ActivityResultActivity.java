package com.zhaoliang.androiddemo.day06.activitydataresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhaoliang.androiddemo.R;

public class ActivityResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_result);
    }

    public void back(View view) {
        String trim = ((EditText) findViewById(R.id.et_result)).getText().toString().trim();
        Intent data = new Intent();
        data.putExtra("result", trim);
        setResult(RESULT_OK, data);
        finish();
    }
}
