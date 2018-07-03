package com.ik.moojing.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.ik.moojing.R;
import com.ik.moojing.fragment.FragmentHome;
import com.ik.moojing.fragment.FragmentMain;
import com.ik.moojing.widget.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BasicActivity {

    private VerticalViewPager mVp;

    @Override
    int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    void initDatas() {
        mVp = (VerticalViewPager) findViewById(R.id.vp);
        initData();
    }


    private void initData() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentHome());
        fragments.add(new FragmentMain());
        mVp.setOffscreenPageLimit(0);
        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }
}
