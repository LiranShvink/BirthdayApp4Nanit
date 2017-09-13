package com.appandgo.birthday.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.appandgo.birthday.R;

import java.util.Random;

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
}
