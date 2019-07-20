package com.mahesh.assigment.utils;

import android.util.Log;
import java.text.DateFormat;
import java.util.Date;

public class Common {

    public final static String TAG_COMMON = "Common";

    public static final String APP_DATABASE_NAME = "local_database";
    public static final String URL = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=f27d3df679f64c3385dbdb7dd7969f90";

    public static synchronized StringBuilder writeMessage(StringBuilder message, String info) {
        return message.append(info).append("\n");
    }

    public static String getFormateDate(String unFormattedDate, DateFormat... dateFormate) {
        try {
            Date date = dateFormate[0].parse(unFormattedDate);
            return dateFormate[1].format(date);
        } catch (Exception e) {
            Log.e(TAG_COMMON, "getFormateDate", e);
        }
        return unFormattedDate;

    }
}
