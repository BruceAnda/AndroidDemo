package com.zhaoliang.androiddemo.day02.storage.sdsize;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.widget.TextView;

import com.zhaoliang.androiddemo.R;

public class SdSizeActivity extends Activity {

    private TextView internal_storage;
    private TextView external_storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd_size);
        external_storage = (TextView) findViewById(R.id.external_storage);

        internal_storage = (TextView) findViewById(R.id.internal_storage);

        StatFs statFs = new StatFs(getFilesDir().getAbsolutePath());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            long availableBlocksLong = statFs.getAvailableBlocksLong();
            long blockCountLong = statFs.getBlockCountLong();
            long blockSizeLong = statFs.getBlockSizeLong();

            internal_storage.setText("内存存储:total:" + Formatter.formatFileSize(this, blockSizeLong * blockCountLong) + "可用空间:" + Formatter.formatFileSize(this, blockSizeLong * availableBlocksLong));
        } else {
            int availableBlocks = statFs.getAvailableBlocks();
            int blockCount = statFs.getBlockCount();
            int blockSize = statFs.getBlockSize();
            internal_storage.setText("内部存储:total:" + Formatter.formatFileSize(this, blockSize * blockCount) + "可用空间:" + Formatter.formatFileSize(this, availableBlocks * blockSize));
        }

        StatFs exStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            long availableBlocksLong = exStatFs.getAvailableBlocksLong();
            long blockCountLong = exStatFs.getBlockCountLong();
            long blockSizeLong = exStatFs.getBlockSizeLong();
            external_storage.setText("存储卡:total:" + Formatter.formatFileSize(this, blockSizeLong * blockCountLong) + "可用空间:" + Formatter.formatFileSize(this, blockSizeLong * availableBlocksLong));
        } else {
            int availableBlocks = exStatFs.getAvailableBlocks();
            int blockCount = exStatFs.getBlockCount();
            int blockSize = exStatFs.getBlockSize();
            external_storage.setText("存储卡:total:" + Formatter.formatFileSize(this, blockSize * blockCount) + "可用空间:" + Formatter.formatFileSize(this, blockSize * availableBlocks));
        }
    }
}
