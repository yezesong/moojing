package com.ik.moojing.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import com.ik.MoojingApp;
import com.ik.moojing.R;
import com.ik.moojing.entry.AppInfo;
import com.ik.moojing.entry.MemorandumBean;
import com.ik.moojing.entry.NewsItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesgoo on 2018/7/8.
 */

public class TestDatas {

    public static List<NewsItemBean> getTestNews() {
        //bean.setmHeaderImg(R.mipmap.new01);
        //bean.setmHeaderTitle("美国贸易霸凌主义破坏国际秩序 极限讹诈危害全球");

        List<NewsItemBean> items = new ArrayList<>();
        items.add(new NewsItemBean("俄罗斯世界杯最新消息", "2018年俄罗斯世界杯频爆冷门", "2018-06-06"));
        items.add(new NewsItemBean("广场舞跳出新情怀，一起来跳舞", "如今广场舞越来越受大叔大妈们的喜爱", "2018-06-12"));
        items.add(new NewsItemBean("日本大雨成灾致70人遇难，安倍被批应对不力", "据日本共同社7月8日报道，截止当天下午16时，从5日开始，波及日本西部地区的暴雨灾情已经造成了70人死亡", "2018-07-08"));
        items.add(new NewsItemBean("加渔船被美巡逻队拦截 专家：或引爆两国领土争端", "虽然北岩岛（North Rock）和玛基亚斯海豹岛（Machias Seal）上的主要“居民”是筑巢的海鹦，但这一带一直是美国和加拿大之间的争议水域。200多年来，其依靠丰富的龙虾资源，成为两国渔民共享的捕虾圣地。", "2018-07-8"));

        return items;
    }


    public static List<MemorandumBean> getMemorandTestBeans() {
        List<MemorandumBean> list = new ArrayList<>();
        list.add(new MemorandumBean(true, "工程会议", "7:20"));
        list.add(new MemorandumBean(true, "吃饭时间", "7:30"));
        list.add(new MemorandumBean(false, "午休时间", "8:20"));
        list.add(new MemorandumBean(false, "翻译机项目组开发会", "11:20"));
        list.add(new MemorandumBean(false, "下午茶", "13:20"));
        list.add(new MemorandumBean(true, "和王总见面", "16:20"));
        list.add(new MemorandumBean(true, "约会", "21:20"));
        return list;
    }

    //获取已安装应用列表,比较耗时的操作，放在子线程中执行
    public static void getPackages(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取已经安装的所有应用, PackageInfo　系统类，包含应用信息
                if (MoojingApp.mApps != null) {
                    MoojingApp.mApps.clear();
                }
                List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
                for (int i = 0; i < packages.size(); i++) {
                    PackageInfo packageInfo = packages.get(i);
                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) { //非系统应用
                        // AppInfo 自定义类，包含应用信息
                        AppInfo appInfo = new AppInfo();
                        appInfo.setAppName(
                                packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());//获取应用名称
                        appInfo.setPackageName(packageInfo.packageName); //获取应用包名，可用于卸载和启动应用
                        appInfo.setVersionName(packageInfo.versionName);//获取应用版本名
                        appInfo.setVersionCode(packageInfo.versionCode);//获取应用版本号
                        appInfo.setIconDrawable(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));//获取应用图标
                        MoojingApp.mApps.add(appInfo);
                    } else { // 系统应用

                    }
                }
            }
        }).start();

    }
}
