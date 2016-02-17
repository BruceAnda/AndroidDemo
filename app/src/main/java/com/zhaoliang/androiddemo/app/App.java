package com.zhaoliang.androiddemo.app;

import android.app.Application;

import org.xutils.x;

/**
 * 应用程序，做一些初始化操作
 * Created by pro on 16/2/11.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initXutils();
    }

    /**
     * 初始化Xutils框架
     */
    private void initXutils() {
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
