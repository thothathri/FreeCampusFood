package com.thotha.freecampusfood.labeler;

import java.util.Calendar;

import com.thotha.freecampusfood.TimeObject;

/**
 * A Labeler that displays months
 */
public class YearLabeler extends Labeler {
    private final String mFormatString;

    public YearLabeler(String formatString) {
        super(200, 60);
        mFormatString = formatString;
    }

    @Override
    public TimeObject add(long time, int val) {
        return timeObjectfromCalendar(Util.addYears(time, val));
    }

    @Override
    protected TimeObject timeObjectfromCalendar(Calendar c) {
        return Util.getYear(c, mFormatString);
    }
}