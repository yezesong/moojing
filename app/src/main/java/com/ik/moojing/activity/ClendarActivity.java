package com.ik.moojing.activity;

import android.view.View;
import android.widget.TextView;

import com.ik.moojing.R;
import com.necer.ncalendar.calendar.MonthCalendar;
import com.necer.ncalendar.listener.OnMonthCalendarChangedListener;

import org.joda.time.LocalDate;

/**
 * Created by jesgoo on 2018/7/14.
 */

public class ClendarActivity extends BasicActivity {

    private MonthCalendar mMonthcalendar;
    private TextView mDateText;

    @Override
    int getLayoutView() {
        return R.layout.activity_calendar;
    }

    @Override
    void initDatas() {

        mDateText = (TextView) findViewById(R.id.tv_date);

        mMonthcalendar = (MonthCalendar) findViewById(R.id.monthcalendar);
        mMonthcalendar.setOnMonthCalendarChangedListener(new OnMonthCalendarChangedListener() {
            @Override
            public void onMonthCalendarChanged(LocalDate dateTime) {
                mDateText.setText(dateTime.toString());
            }
        });
    }

    public void toLastMonth(View view) {
        mMonthcalendar.toLastPager();
    }

    public void toNextMonth(View view) {
        mMonthcalendar.toNextPager();
    }

    public void toToday(View view) {
        mMonthcalendar.toToday();
    }

}
