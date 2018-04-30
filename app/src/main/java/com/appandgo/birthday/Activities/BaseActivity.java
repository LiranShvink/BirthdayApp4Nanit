package com.appandgo.birthday.Activities;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appandgo.birthday.R;
import com.appandgo.birthday.dataobjects.BirthdayObj;
import com.appandgo.birthday.utils.Utils;

import static com.appandgo.birthday.Activities.MainActivity.BIRTHDAY_OBJ;

/**
 * Created by liran on 27/4/2018.
 */

enum ThemeAssets {
    BG,
    PLACEHOLDER,
    CAMICON,
    OVERLAY
}

public class BaseActivity extends AppCompatActivity {

    protected int selectedTheme;
    protected TypedArray themeAssetArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        setTheme();

    }

    private void setTheme()
    {
        BirthdayObj obj = (BirthdayObj) getIntent().getSerializableExtra(BIRTHDAY_OBJ);
        if (obj == null) {
            // Should select random
            setRandomTheme();
        }
        else if (obj.selectedTheme != 0)
        {
            // should override the theme as we pass the one we wish to see
            overrideBarColors(obj.selectedTheme);
        }
        else
        {
            // There is an object but the theme is not set
            setRandomTheme();
        }
    }

    protected void setThemeGrapics(int color) {
        int[] themeColorArr = getResources().getIntArray(R.array.app_theme_list);
        TypedArray themesArr = getResources().obtainTypedArray(R.array.theme_array_list_chooser);

        for (int i = 0; i < themeColorArr.length; i++)
        {
            int item = themeColorArr[i];
            if (item == color)
            {
                int resourceId = themesArr.getResourceId(i, 0);
                themeAssetArr = getResources().obtainTypedArray(resourceId);
            }
        }
    }

    protected Drawable getCurrentThemeGrapics(ThemeAssets asset) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getResources().getDrawable(themeAssetArr.getResourceId(asset.ordinal(), 0), getTheme());
        } else {
            return getResources().getDrawable(themeAssetArr.getResourceId(asset.ordinal(), 0) );
        }
    }

    protected void setRandomTheme() {
        selectedTheme = Utils.getRandomThemeColor(this);
        setBarColors(selectedTheme);
        setThemeGrapics(selectedTheme);
    }

    protected void setBarColors(int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(color);
            getWindow().setStatusBarColor(color);
        }
    }

    protected void overrideBarColors(int color) {
        selectedTheme = color;
        setBarColors(color);
        setThemeGrapics(color);
    }

}
