package com.ik.moojing.activity;

import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ik.moojing.R;
import com.ik.moojing.adapter.DragAdapter;
import com.ik.moojing.adapter.RecomendAdapter;
import com.ik.moojing.db.SqlLiteHelper;
import com.ik.moojing.entry.ChannelItem;
import com.ik.moojing.entry.ChannelManage;
import com.ik.moojing.widget.DragGridView;
import com.ik.moojing.widget.RecomendGridView;

import java.util.ArrayList;

public class ChannelManageActivity extends BasicActivity implements OnItemClickListener ,View.OnClickListener{

    private DragGridView useGridview;
    private RecomendGridView recommendGridview;
    private TextView editChannel;
    private TextView dragTip, mTvClose;
    private CheckBox mCheck;

    private DragAdapter useAdapter;
    private RecomendAdapter recommendAdapter;
    private ArrayList<ChannelItem> useList = new ArrayList<ChannelItem>();
    private ArrayList<ChannelItem> recommendList = new ArrayList<ChannelItem>();
    boolean isMove = false;


    @Override
    int getLayoutView() {
        return R.layout.activity_channel;
    }

    @Override
    void initDatas() {
        useGridview = (DragGridView)findViewById(R.id.use_gridview);
        recommendGridview = (RecomendGridView)findViewById(R.id.recommend_gridview);
        editChannel = (TextView)findViewById(R.id.edit_use_channel);
        dragTip = (TextView)findViewById(R.id.drag_tip);
        mTvClose = (TextView)findViewById(R.id.close_channel_btn);
        mTvClose.setOnClickListener(this);
        mCheck = (CheckBox)findViewById(R.id.edit_use_channel);
        mCheck.setOnClickListener(this);
        initData();
    }

    private void initData() {
        useList = ((ArrayList<ChannelItem>) ChannelManage.getManage(SqlLiteHelper.getSQLHelper()).getUserChannel());
        recommendList = ((ArrayList<ChannelItem>) ChannelManage.getManage(SqlLiteHelper.getSQLHelper()).getOtherChannel());
        useAdapter = new DragAdapter(this);
        useAdapter.setListDate(useList);
        useGridview.setAdapter(useAdapter);
        recommendAdapter = new RecomendAdapter(this, recommendList);
        recommendGridview.setAdapter(recommendAdapter);
        recommendGridview.setOnItemClickListener(this);
        useGridview.setOnItemClickListener(this);
    }

    private void closeChannel() {
        onBackPressed();
    }

    private void editChannel() {
        if (dragTip.getVisibility()==View.GONE){
            useAdapter.editChannel(true);
            editChannel.setText("完成");
            dragTip.setVisibility(View.VISIBLE);
        }else {
            useAdapter.editChannel(false);
            editChannel.setText("编辑");
            dragTip.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        if (isMove) {
            return;
        }
        switch (parent.getId()) {
            //position为 0的不可以进行任何操作
            case R.id.use_gridview:
                if (position != 0 ) {
                    final ImageView moveImageView = getView(view);
                    if (moveImageView != null) {
                        TextView newTextView = (TextView) view.findViewById(R.id.item_channel_name);
                        final int[] startLocation = new int[2];
                        newTextView.getLocationInWindow(startLocation);
                        final ChannelItem channel = ((DragAdapter) parent.getAdapter()).getItem(position);//获取点击的频道内容
                        recommendAdapter.setVisible(false);
                        //添加到最后一个
                        recommendAdapter.addItem(channel);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                try {
                                    int[] endLocation = new int[2];
                                    //获取终点的坐标
                                    recommendGridview.getChildAt(recommendGridview.getLastVisiblePosition()).getLocationInWindow(endLocation);
                                    MoveAnim(moveImageView, startLocation, endLocation, channel, useGridview);
                                    useAdapter.setRemove(position);
                                } catch (Exception localException) {
                                }
                            }
                        }, 50L);
                    }
                }
                break;
            case R.id.recommend_gridview:
                final ImageView moveImageView = getView(view);
                if (moveImageView != null) {
                    TextView newTextView = (TextView) view.findViewById(R.id.item_channel_name);
                    final int[] startLocation = new int[2];
                    newTextView.getLocationInWindow(startLocation);
                    final ChannelItem channel = ((RecomendAdapter) parent.getAdapter()).getItem(position);
                    useAdapter.setVisible(false);
                    //添加到最后一个
                    useAdapter.addItem(channel);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            try {
                                int[] endLocation = new int[2];
                                useGridview.getChildAt(useGridview.getLastVisiblePosition()).getLocationInWindow(endLocation);
                                MoveAnim(moveImageView, startLocation, endLocation, channel, recommendGridview);
                                recommendAdapter.setRemove(position);
                            } catch (Exception localException) {
                            }
                        }
                    }, 50L);
                }
                break;
            default:
                break;
        }
    }

    private void MoveAnim(View moveView, int[] startLocation, int[] endLocation, final ChannelItem moveChannel,
                          final GridView clickGridView) {
        int[] initLocation = new int[2];
        moveView.getLocationInWindow(initLocation);
        final ViewGroup moveViewGroup = getMoveViewGroup();
        final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
        TranslateAnimation moveAnimation = new TranslateAnimation(
                startLocation[0], endLocation[0], startLocation[1],
                endLocation[1]);
        moveAnimation.setDuration(300L);
        //动画配置
        AnimationSet moveAnimationSet = new AnimationSet(true);
        moveAnimationSet.setFillAfter(false);
        moveAnimationSet.addAnimation(moveAnimation);
        mMoveView.startAnimation(moveAnimationSet);
        moveAnimationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                isMove = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveViewGroup.removeView(mMoveView);
                if (clickGridView instanceof DragGridView) {
                    recommendAdapter.setVisible(true);
                    recommendAdapter.notifyDataSetChanged();
                    useAdapter.remove();
                } else {
                    useAdapter.setVisible(true);
                    useAdapter.notifyDataSetChanged();
                    recommendAdapter.remove();
                }
                isMove = false;
            }
        });
    }

    private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        view.setLayoutParams(mLayoutParams);
        return view;
    }

    private ViewGroup getMoveViewGroup() {
        ViewGroup moveViewGroup = (ViewGroup) getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(this);
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
    }

    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(this);
        iv.setImageBitmap(cache);
        return iv;
    }

    private void saveChannel() {
        ChannelManage.getManage(SqlLiteHelper.getSQLHelper()).deleteAllChannel();
        ChannelManage.getManage(SqlLiteHelper.getSQLHelper()).saveUserChannel(useAdapter.getChannnelLst());
        ChannelManage.getManage(SqlLiteHelper.getSQLHelper()).saveOtherChannel(recommendAdapter.getChannnelLst());
    }

    @Override
    public void onBackPressed() {
        saveChannel();
        if (useAdapter.isListChanged()) {
            //Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
            //setResult(NewsActivity.CHANNEL_RESULT, intent);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.close_channel_btn:
                closeChannel();
                break;
            case R.id.edit_use_channel:
                editChannel();
                break;
        }
    }
}
