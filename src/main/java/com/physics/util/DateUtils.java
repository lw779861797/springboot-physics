package com.physics.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    public static String getWeekCounterByEventStartDate(String event_s_date, String dailyDate) throws ParseException {
        Calendar c_base = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar tempC = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int week = 0;
        try {
            c_base.setTime(df.parse(event_s_date));
            c2.setTime(df.parse(dailyDate));
            Long time1 = c2.getTimeInMillis();
            Long time2 = c_base.getTimeInMillis();
            Long dayDiffer = Math.abs(time1 - time2) / (1000 * 60 * 60 * 24);//毫秒*秒*分*小时
            int curTodayWeek = c_base.get(Calendar.DAY_OF_WEEK) - 1;
            int differIsWeek = 7 - curTodayWeek;
            tempC = (Calendar) c_base.clone();
            if (differIsWeek != 0) {
                week++;
            }
            if (differIsWeek != 7) {
                tempC.add(Calendar.DAY_OF_YEAR, differIsWeek);
            }
            time1 = c2.getTimeInMillis();
            time2 = tempC.getTimeInMillis();
            dayDiffer = Math.abs(time1 - time2) / (1000 * 60 * 60 * 24);//毫秒*秒*分*小时
            if (dayDiffer.intValue() == 1) {//差一天
                week++;
            } else if (dayDiffer.intValue() < 7) {
                week++;
            } else {
                DecimalFormat decimalFormat = new DecimalFormat("#.0");
                String dayNum = decimalFormat.format((double) dayDiffer.intValue() / 7);
                int day = Integer.parseInt(dayNum.substring(0, dayNum.indexOf(".")));
                week += day;
                int dec = Integer.parseInt(dayNum.substring(dayNum.indexOf(".") + 1, dayNum.length()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(week);
    }
}
