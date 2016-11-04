package com.feltsan.spedition.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 13.10.15.
 */
public abstract class DateHelper {

    public static long convertStringToLong(String _date) {
        if(_date.length()==0){
            return 0;
        }else {
            SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
            Date d = null;
            try {
                d = f.parse(_date);
                return d.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }

    }
    public static String convertLongToString(long _long) {
        if(_long ==0){
            return "";
        }else {

            Date date = new Date(_long);
            SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");
            return df2.format(date);
        }
    }
}
