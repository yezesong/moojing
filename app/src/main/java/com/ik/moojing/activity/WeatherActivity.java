package com.ik.moojing.activity;

import android.support.v4.view.ViewPager;

import com.ik.moojing.R;
import com.ik.moojing.adapter.WeatherDateAdapter;
import com.ik.moojing.widget.TabStrip;

/**
 * Created by jesgoo on 2018/7/13.
 */

public class WeatherActivity extends BasicActivity {
    private TabStrip mTabStrip;
    private WeatherDateAdapter mAdapter;
    private ViewPager mViewpager;

    @Override
    int getLayoutView() {
        return R.layout.weather_activity;
    }

    @Override
    void initDatas() {
        mTabStrip = (TabStrip) findViewById(R.id.tabstrip);
        mViewpager = (ViewPager) findViewById(R.id.view_pager);
        init();
    }

    private void init() {
        mAdapter = new WeatherDateAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mAdapter);
        mTabStrip.setViewPager(mViewpager);

    }
}
