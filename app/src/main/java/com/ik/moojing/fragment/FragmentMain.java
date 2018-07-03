package com.ik.moojing.fragment;

import android.widget.TextView;

import com.ik.moojing.R;
import com.ik.moojing.utils.ChinaDate;

/**
 * Created by jesgoo on 2018/6/28.
 * 主界面
 */

public class FragmentMain extends LazyLoadFragment {
    private TextView mTvLauner;

    @Override
    protected int setContentView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void lazyLoad() {
        mTvLauner = findViewById(R.id.tv_lunar);
        mTvLauner.setText(ChinaDate.today());
    }
}
