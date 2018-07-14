package com.ik.moojing.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ik.moojing.R;
import com.ik.moojing.adapter.SpaceItemDecoration;
import com.ik.moojing.adapter.WeatherItemAdapter;
import com.ik.moojing.callback.OnItemClickListener;
import com.ik.moojing.utils.TestDatas;

/**
 * Created by jesgoo on 2018/7/14.
 */

public class ListViewFragment extends Fragment implements OnItemClickListener {
    private static final String ARG_POSITION = "position";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private WeatherItemAdapter mAdapter;
    private int position;

    public static ListViewFragment newInstance(int position) {
        ListViewFragment f = new ListViewFragment();
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
        View layout = inflater.inflate(R.layout.weather_layout, container, false);
        setupViews(layout);
        return layout;
    }

    private void setupViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recy_view);
        mAdapter = new WeatherItemAdapter(TestDatas.getWeathers(), position);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 4));
    }

    @Override
    public void onItemClick(int position) {

    }
}
