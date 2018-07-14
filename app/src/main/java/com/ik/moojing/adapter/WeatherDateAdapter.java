package com.ik.moojing.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ik.MoojingApp;
import com.ik.moojing.fragment.ListViewFragment;

/**
 * Created by jesgoo on 2018/7/14.
 */

public class WeatherDateAdapter extends FragmentPagerAdapter {

    public WeatherDateAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MoojingApp.mWeather.get(position);
    }

    @Override
    public int getCount() {
        return MoojingApp.mWeather.size();
    }

    @Override
    public Fragment getItem(int position) {
        return ListViewFragment.newInstance(position);
    }
}
