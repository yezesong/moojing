package com.ik.moojing.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class RecomendGridView extends GridView {

    public RecomendGridView(Context mContext, AttributeSet paramAttributeSet) {
        super(mContext, paramAttributeSet);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
