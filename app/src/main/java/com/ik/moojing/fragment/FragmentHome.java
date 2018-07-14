package com.ik.moojing.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ik.moojing.R;
import com.ik.moojing.adapter.TabStripAdapter;
import com.ik.moojing.utils.ChinaDate;

/**
 * Created by jesgoo on 2018/6/28.
 */

public class FragmentHome extends LazyLoadFragment implements View.OnClickListener {
    private ImageView mIvRobat;
    private TextView mTvChinaDay;

    @Override
    protected int setContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void lazyLoad() {
        mIvRobat = (ImageView) view.findViewById(R.id.img_robat);
        mIvRobat.setOnClickListener(this);
        mTvChinaDay = (TextView) view.findViewById(R.id.tv_lunar);
        mTvChinaDay.setText(ChinaDate.today());
    }

    @Override
    public void onClick(View view) {

    }
}
