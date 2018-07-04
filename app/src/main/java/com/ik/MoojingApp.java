package com.ik;

import android.app.Application;

import com.ik.moojing.db.SqlLiteHelper;
import com.ik.moojing.utils.imagecache.WYImageLoader;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * Created by jesgoo on 2018/6/29.
 */

public class MoojingApp extends Application {

    private static MoojingApp mInstance = null;

    public static synchronized MoojingApp getInstance() {
        if (mInstance == null) {
            mInstance = new MoojingApp();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ScreenAdapterTools.init(this);
        mInstance = this;
        init();
    }

    private void init() {
        WYImageLoader.init();
        SqlLiteHelper.getSQLHelper();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        WYImageLoader.clearMemoryCache();
        SqlLiteHelper.closeSql();
    }
}
