package com.ik.moojing.fragment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ik.MoojingApp;
import com.ik.moojing.R;
import com.ik.moojing.activity.ChannelManageActivity;
import com.ik.moojing.adapter.HorizontalListViewAdapter;
import com.ik.moojing.adapter.MemorandumAdapter;
import com.ik.moojing.adapter.TabStripAdapter;
import com.ik.moojing.callback.OnItemClickListener;
import com.ik.moojing.entry.MemorandumBean;
import com.ik.moojing.utils.ChinaDate;
import com.ik.moojing.widget.HorizontalListView;
import com.ik.moojing.widget.TabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesgoo on 2018/6/28.
 * 主界面
 */

public class FragmentMain extends LazyLoadFragment implements View.OnClickListener , OnItemClickListener {
    private TextView mTvLauner;
    private ImageView mImgView;
    private TabStripAdapter mAdapter;
    private TabStrip mTabStrip;
    private ViewPager mViewpager;
    private ImageView mImageView;
    private ListView mMemorandumlist;
    private MemorandumAdapter mMemorandumAdapter;
    private HorizontalListView mHorizontialListView;
    private HorizontalListViewAdapter mAppListAdapter;
    private ImageView mImgMoreApps;

    @Override
    protected int setContentView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void lazyLoad() {
        mTvLauner = findViewById(R.id.tv_lunar);
        mTvLauner.setText(ChinaDate.today());
        mImgView = findViewById(R.id.add_channel);
        mImgView.setOnClickListener(this);

        mImageView = findViewById(R.id.img_add);
        mImageView.setOnClickListener(this);

        mImgMoreApps = findViewById(R.id.more_apps);
        mHorizontialListView = findViewById(R.id.hori_listview);
        mMemorandumlist = findViewById(R.id.memorandum_list);
        mTabStrip = findViewById(R.id.tabstrip);
        mViewpager = findViewById(R.id.view_pager);
        init();
    }

    private void init() {
        mAdapter = new TabStripAdapter(getChildFragmentManager());
        mViewpager.setAdapter(mAdapter);
        mTabStrip.setViewPager(mViewpager);

        mMemorandumAdapter = new MemorandumAdapter(getActivity());
        mMemorandumlist.setAdapter(mMemorandumAdapter);
        mMemorandumAdapter.updateDatas(MoojingApp.mMemorandumBeans);

        mAppListAdapter = new HorizontalListViewAdapter(getActivity());
        mHorizontialListView.setAdapter(mAppListAdapter);
        mAppListAdapter.update(MoojingApp.mApps);
        mAppListAdapter.setOnItemClickListener(this);
    }

    private void addChannel() {
        startActivity(new Intent(getActivity(), ChannelManageActivity.class));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_channel:
                addChannel();
                break;
            case R.id.img_add://添加备忘录
                break;
            case R.id.more_apps:
                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}
