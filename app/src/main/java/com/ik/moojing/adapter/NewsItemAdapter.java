package com.ik.moojing.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ik.moojing.R;
import com.ik.moojing.callback.OnItemClickListener;
import com.ik.moojing.entry.NewsItemBean;

import java.util.List;

/**
 * Created by jesgoo on 2018/7/8.
 */

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ViewHolder> {
    private List<NewsItemBean> mNewsItemBean;

    private OnItemClickListener mListener;

    public NewsItemAdapter(List<NewsItemBean> beans) {
        mNewsItemBean = beans;
    }

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_layout, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int pos) {

        final NewsItemBean data = mNewsItemBean.get(pos);
        //if (viewHolder instanceof ViewHolder) {
            viewHolder.textTitle.setText(data.getTitle());
            viewHolder.textContent.setText(data.getMessage());
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

        TextView textTitle;
        TextView textContent;

        public ViewHolder(View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.tv_title);
            textContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
