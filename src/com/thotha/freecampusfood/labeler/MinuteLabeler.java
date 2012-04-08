package com.thotha.freecampusfood.labeler;

import java.util.Calendar;

import com.thotha.freecampusfood.TimeObject;

/**
 * A Labeler that displays minutes
 */
public class MinuteLabeler extends Labeler {
    private final String mFormatString;

    public MinuteLabeler(String formatString) {
        super(45, 60);
        mFormatString = formatString;
    }

    @Override
    public TimeObject add(long time, int val) {
        return timeObjectfromCalendar(Util.addMinutes(time, val, minuteInterval));
    }

    @Override
    protected TimeObject timeObjectfromCalendar(Calendar c) {
    	if (minuteInterval>1) {
    		int minutes = c.get(Calendar.MINUTE);
    		c.set(Calendar.MINUTE, minutes-(minutes%minuteInterval));
    	}
        return Util.getMinute(c, mFormatString, minuteInterval);
    }

}
