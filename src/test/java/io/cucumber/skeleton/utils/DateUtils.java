package io.cucumber.skeleton.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static Date convertStringToDate(String enterDate) {
        Date date=null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
            date= formatter.parse(enterDate);

        } catch (Exception ex) {

        }
        return date;
    }


}
