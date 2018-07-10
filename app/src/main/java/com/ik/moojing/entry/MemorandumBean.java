package com.ik.moojing.entry;

/**
 * 备忘录
 * Created by jesgoo on 2018/7/9.
 */

public class MemorandumBean {
    private boolean checked;
    private String title;
    private String time;

    public MemorandumBean(boolean checked, String title, String time) {
        this.checked = checked;
        this.time = time;
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
