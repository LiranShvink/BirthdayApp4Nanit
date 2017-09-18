package com.appandgo.birthday.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.appandgo.birthday.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

/**
 * Created by naor on 13/09/2017.
 */

public class NDUtils {

    public static int getRandomThemeColor(Context context)
    {
        final Random r = new Random();
        int[] themeColorArr = context.getResources().getIntArray(R.array.app_theme_list);

        int randomIdx = r.nextInt(themeColorArr.length);
        return themeColorArr[randomIdx];
    }

    public static Drawable convertNumberToImageDigit(Context context, int number)
    {
        TypedArray digitsArr = context.getResources().obtainTypedArray(R.array.digits_list);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(digitsArr.getResourceId(number, -1), context.getTheme());
        } else {
            return context.getResources().getDrawable( digitsArr.getResourceId(number, -1) );
        }
    }

    @NonNull
    public static String getDateFormatToShowAsDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.LONG);
        formatter.setTimeZone(calendar.getTimeZone());

        return formatter.format(calendar.getTime());
    }

    @NonNull
    public static String getDateFormatToShowAsTimeline(int year, int month, int day) {

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH) + 1 ;
        int thisDay = calendar.get(Calendar.DAY_OF_MONTH);

        if ( (thisYear - year) > 1 )
        {
            return "Years old";
        }
        else if ( (thisYear - year) == 1 )
        {
            return "Year old";
        }
        else if ( (thisMonth - month) > 1 )
        {
            return "Months old";
        }
        else if ( (thisMonth - month) == 1 )
        {
            return "month old";
        }
        else if ( (thisDay - day) > 0 )
        {
            return "Days old";
        }
        return "Day old";
    }

    public static int getDateAsSingleNumber(int year, int month, int day) {

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH) + 1 ;
        int thisDay = calendar.get(Calendar.DAY_OF_MONTH);

        if ( (thisYear - year) > 0 )
        {
            return (thisYear - year);
        }
        else if ( (thisMonth - month) > 0 )
        {
            return (thisMonth - month);
        }

        else if ( (thisDay - day) > 0 )
        {
            return (thisDay - day);
        }
        return 0;
    }
}
