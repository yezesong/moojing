package com.ik.moojing.entry;

/**
 * Created by jesgoo on 2018/7/14.
 */

public class WeatherBean {
    private String time;
    private String weather;
    private String date;

    public WeatherBean(String time, String date, String weather) {
        this.time = time;
        this.date = date;
        this.weather = weather;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
