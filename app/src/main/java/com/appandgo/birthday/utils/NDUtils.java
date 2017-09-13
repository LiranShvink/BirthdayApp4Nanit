package com.appandgo.birthday.utils;

import android.content.Context;

import com.appandgo.birthday.R;

import java.util.Random;

/**
 * Created by naor on 13/09/2017.
 */

public class NDUtils {

    public  static int getRandomThemeColor(Context context)
    {
        int[] mThemeColorArr;
        final Random r = new Random();
        mThemeColorArr = context.getResources().getIntArray(R.array.app_theme_list);

        int randomIdx = r.nextInt(mThemeColorArr.length);
        return mThemeColorArr[randomIdx];
    }
}
