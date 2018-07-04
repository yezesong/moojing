package com.ik.moojing.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ik.moojing.R;
import com.ik.moojing.activity.ChannelManageActivity;
import com.ik.moojing.utils.ChinaDate;

/**
 * Created by jesgoo on 2018/6/28.
 * 主界面
 */

public class FragmentMain extends LazyLoadFragment implements View.OnClickListener{
    private TextView mTvLauner;
    private ImageView mImgView;

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
    }

    private void addChannel() {
        startActivity(new Intent(getActivity(),ChannelManageActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_channel:
                addChannel();
                break;
        }
    }
}
