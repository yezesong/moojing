package com.ik.moojing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * Created by jesgoo on 2018/6/28.
 */

public abstract class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        ScreenAdapterTools.getInstance().loadView((ViewGroup) getWindow().getDecorView());
        initDatas();
    }

    abstract int getLayoutView();

    abstract void initDatas();
}
