package com.appandgo.birthday;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appandgo.birthday.utils.NDUtils;

import java.util.Random;

/**
 * Created by naor on 13/09/2017.
 */

public class NDBaseActivity extends AppCompatActivity {

    protected int selectedTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        setRandomTheme();

    }

    protected void setRandomTheme()
    {
        selectedTheme = NDUtils.getRandomThemeColor(this);
        setBarColors(selectedTheme);
    }

    protected void setBarColors(int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(color);
            getWindow().setStatusBarColor(color);
        }
    }

    private String getFormatName(String name)
    {
        return getString(R.string.title_name_prefix, name);
    }
}
