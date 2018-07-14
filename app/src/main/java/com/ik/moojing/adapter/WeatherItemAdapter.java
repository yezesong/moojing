package com.ik.moojing.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ik.moojing.R;
import com.ik.moojing.callback.OnItemClickListener;
import com.ik.moojing.entry.NewsItemBean;
import com.ik.moojing.entry.WeatherBean;

import java.util.List;

/**
 * Created by jesgoo on 2018/7/8.
 */

public class WeatherItemAdapter extends RecyclerView.Adapter<WeatherItemAdapter.ViewHolder> {
    private List<WeatherBean> mNewsItemBean;

    private OnItemClickListener mListener;
    private int mTabposition;

    public WeatherItemAdapter(List<WeatherBean> beans, int tabposition) {
        mNewsItemBean = beans;
        mTabposition = tabposition;
    }

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int pos) {

        final WeatherBean data = mNewsItemBean.get(pos);
        //if (viewHolder instanceof ViewHolder) {
        viewHolder.textTime.setText(data.getTime());
        viewHolder.textDate.setText(data.getDate());
        viewHolder.textTemplate.setText(data.getWeather());

        if (mTabposition == 0) {
            viewHolder.textTime.setVisibility(View.VISIBLE);
            viewHolder.textDate.setVisibility(View.GONE);
        } else {
            viewHolder.textDate.setVisibility(View.VISIBLE);
            viewHolder.textTime.setVisibility(View.GONE);
        }
        if (mListener == null) return;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(pos);
            }
        });
        //}
    }

    @Override
    public int getItemCount() {
        return mNewsItemBean == null ? 0 : mNewsItemBean.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTime;
        TextView textDate;
        TextView textTemplate;

        public ViewHolder(View itemView) {
            super(itemView);
            textTime = (TextView) itemView.findViewById(R.id.tv_time);
            textDate = (TextView) itemView.findViewById(R.id.tv_date);
            textTemplate = (TextView) itemView.findViewById(R.id.tv_weather);
        }
    }
}
