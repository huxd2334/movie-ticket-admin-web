package com.binhlc.adminweb.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    public static Integer convertTimeToHHmm(java.sql.Time time){
        SimpleDateFormat spf = new SimpleDateFormat("HHmm");
        String s = spf.format(time);
        try{
            int timeInt = Integer.parseInt(s);
            return timeInt;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatPrice(Long price){
        DecimalFormat fm = new DecimalFormat("###,###,###");
        return fm.format(price);
    }

    public static Date convertStringToDate(String dateStr){
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = spf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    // convert form yyyyMMdd to dd/MM/yyyy
    public static String convertToDDMMYYYY(int dateInt){
        int day = dateInt % 100;
        int month = (dateInt / 100) % 100;
        int year = dateInt / 10000;
        return String.format("%02d/%02d/%d", day, month, year);
    }



    public static Integer convertDateToYYYYmmDD(Date date){
        SimpleDateFormat spf = new SimpleDateFormat("yyyyMMdd");
        String s = spf.format(date);
        try{
            int dateInt = Integer.parseInt(s);
            return dateInt;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertToHourMinute(int time){
        int minutes = time % 60;
        int hour = time / 60;
        return hour + " giờ " + minutes+" phút";
    }

    public static String convertCensor(int age){
        switch (age){
            case 0:
                return "P";
            case 13:
                return "C13";
            case 16:
                return "C16";
            case 18:
                return "C18";
            default:
                return "P";
        }
    }

}
