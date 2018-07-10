package com.ik.moojing.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ik.MoojingApp;
import com.ik.moojing.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;


public class TabStripAdapter extends FragmentPagerAdapter {

    public TabStripAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MoojingApp.mCatalogs.get(position);
    }

    @Override
    public int getCount() {
        return MoojingApp.mCatalogs.size();
    }

    @Override
    public Fragment getItem(int position) {
        return NewsFragment.newInstance(position);
    }
}
