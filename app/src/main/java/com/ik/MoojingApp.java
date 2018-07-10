package com.ik;

import android.app.Application;

import com.ik.moojing.db.SqlLiteHelper;
import com.ik.moojing.entry.AppInfo;
import com.ik.moojing.entry.MemorandumBean;
import com.ik.moojing.utils.TestDatas;
import com.ik.moojing.utils.imagecache.WYImageLoader;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesgoo on 2018/6/29.
 */

public class MoojingApp extends Application {

    private static MoojingApp mInstance = null;
    public static List<String> mCatalogs = new ArrayList<String>();
    public static List<MemorandumBean> mMemorandumBeans = new ArrayList<>();
    public static List<AppInfo> mApps = new ArrayList<>();

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
        mMemorandumBeans = TestDatas.getMemorandTestBeans();
        TestDatas.getPackages(this);
    }

    private void init() {
        WYImageLoader.init();
        SqlLiteHelper.getSQLHelper();

        mCatalogs.add("热门");
        mCatalogs.add("娱乐");
        mCatalogs.add("财经");
        mCatalogs.add("政治");
        mCatalogs.add("美食");
        mCatalogs.add("军事");
        mCatalogs.add("娱乐");
        mCatalogs.add("科技");
        mCatalogs.add("体育");
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
