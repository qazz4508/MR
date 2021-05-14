package com.zq.jz.util;

import java.util.Calendar;

public class DateUtil {
    public static Calendar getCalendar(int y, int m, int d) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, y);
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, d);
        return calendar;
    }

    public static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH);
    }

    public static int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getYYYY_MM_DD(Calendar calendar) {
        return getYear(calendar) + "-" + (getMonth(calendar)+1) + "-" + getDay(calendar);
    }
}
