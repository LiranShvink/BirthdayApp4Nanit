package com.appandgo.birthday.dataobjects;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by liran on 27/4/2018.
 */

public class BirthdayObj implements Serializable {

    static final long serialVersionUID = 101L;

    public String name;
    public int year;
    public int month;
    public int day;
    public Bitmap image;
    public int selectedTheme = 0;
}
