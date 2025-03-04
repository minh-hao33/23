package com.example.hrms.utils;
// Lớp tiện ích để xử lý các thao tác liên quan đến ngày tháng.
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    public static Date parseDate(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.parse(dateStr);
    }
}
