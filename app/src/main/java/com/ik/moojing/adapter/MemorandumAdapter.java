package com.ik.moojing.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ik.moojing.R;
import com.ik.moojing.entry.MemorandumBean;

import java.util.List;

/**
 * Created by jesgoo on 2018/7/9.
 */

public class MemorandumAdapter extends BaseAdapter {
    private List<MemorandumBean> mDatas;
    private Context context;

    public MemorandumAdapter(Context context) {
        this.context = context;
    }

    public void updateDatas(List<MemorandumBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public MemorandumBean getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.memorand_item_layout, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MemorandumBean bean = getItem(i);
        viewHolder.content.setText(bean.getTime());
        viewHolder.title.setText(bean.getTitle());
        viewHolder.checkBox.setChecked(bean.isChecked());
        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView content;
        CheckBox checkBox;

        ViewHolder(View view) {
            view.setTag(this);
            title = (TextView) view.findViewById(R.id.memorand_title);
            content = (TextView) view.findViewById(R.id.memorand_time);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        }
    }
}
