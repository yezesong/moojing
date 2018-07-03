package com.ik;

import android.app.Application;

import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * Created by jesgoo on 2018/6/29.
 */

public class MoojingApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ScreenAdapterTools.init(this);
    }
}
