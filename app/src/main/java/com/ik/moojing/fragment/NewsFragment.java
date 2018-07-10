package com.ik.moojing.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.ik.moojing.R;
import com.ik.moojing.adapter.NewsItemAdapter;
import com.ik.moojing.adapter.SpaceItemDecoration;
import com.ik.moojing.callback.OnItemClickListener;
import com.ik.moojing.utils.TestDatas;
import com.ik.moojing.widget.RecyclerViewHeader;

public class NewsFragment extends Fragment implements OnItemClickListener {

    private static final String ARG_POSITION = "position";
    private RecyclerView mRecyclerView;
    private RecyclerViewHeader mRecyclerHeader;
    private LinearLayoutManager mLayoutManager;
    private NewsItemAdapter mAdapter;
    private int position;

    public static NewsFragment newInstance(int position) {
        NewsFragment f = new NewsFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.news_layout, container, false);
        setupViews(layout);
        return layout;
    }

    private void setupViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recy_view);
        mAdapter = new NewsItemAdapter(TestDatas.getTestNews());

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 8));
        mRecyclerHeader = (RecyclerViewHeader) view.findViewById(R.id.header);
        mRecyclerHeader.attachTo(mRecyclerView);
    }

    @Override
    public void onItemClick(int position) {

    }
}