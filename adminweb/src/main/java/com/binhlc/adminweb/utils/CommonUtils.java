package com.binhlc.adminweb.utils;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    public static Integer convertTimeToHHmm(java.sql.Time time){
        if (time == null) {
            return null;
        }

        // Convert Time to LocalTime
        java.time.LocalTime localTime = time.toLocalTime();

        // Convert LocalTime to Integer in the format HHmm
        return localTime.getHour() * 100 + localTime.getMinute();
    }

    public static Date convertDateIntToDate(int dateInt){
        int day = dateInt % 100;
        int month = (dateInt / 100) % 100;
        int year = dateInt / 10000;
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        spf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try{
            Date date = spf.parse(year + "-" + month + "-" + day);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Time convertTimeIntToTime(int timeInt){
        int hour = timeInt / 100;
        int minute = timeInt % 100;
        LocalTime localTime = LocalTime.of(hour, minute); // hh:mm:00
        return Time.valueOf(localTime);
    }

    public static String convertMovieStatus(int status){
        switch (status){
            case 0:
                return "Disabled";
            case 1:
                return "Active";
            default:
                return "Đang cập nhật";
        }
    }

    public static String extractYoutubeId(String url) {
        String videoId = null;
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url); //url is youtube url for which you want to extract the video id.
        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }

//    public static void main(String[] args) {
//        System.out.println(extractYoutubeId("https://youtu.be/qn1t_biQigc?feature=shared"));
//    }

    public static String convertTimeStart(int time){
        int hour = time/100;
        int minute = time%100;
        return String.format("%02d:%02d", hour, minute);
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

    public static Date convertIntToDate(int dateAsInt) {
        // Extract year, month, and day from the integer
        int year = dateAsInt / 10000;
        int month = (dateAsInt % 10000) / 100 - 1; // Calendar month is 0-based
        int day = dateAsInt % 100;

        // Create a Calendar object and set the extracted values
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        // Return the Date object
        return calendar.getTime();
    }

//    public static void main(String[] args) {
//        System.out.println("Converted Date: " + convertIntToDate(20240616));
//    }


    public static String convertGender(int gender){
        switch (gender){
            case 1:
                return "Nam";
            case 2:
                return "Nữ";
            default:
                return "Đang cập nhật";
        }
    }

    public static String formatPhoneNumber(String phoneNumber){
        if(phoneNumber != null && phoneNumber.length() == 10) {
            return phoneNumber.replaceAll("(\\d{4})(\\d{3})(\\d{3})", "$1 $2 $3");
        }
        return phoneNumber;
    }

    public static String convertUserRank(int i){
        switch (i){
            case 1:
                return "Thành viên";
            case 2:
                return "VIP";
            default:
                return "Đang cập nhật";
        }
    }

    public static Integer convertSQlDateToYYYYmmDD(java.sql.Date date){
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
