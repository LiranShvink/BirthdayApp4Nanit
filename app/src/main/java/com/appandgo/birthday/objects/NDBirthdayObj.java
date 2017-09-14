package com.appandgo.birthday.objects;

import android.graphics.Bitmap;
import android.media.Image;

import java.io.Serializable;

/**
 * Created by naor on 14/09/2017.
 */

public class NDBirthdayObj implements Serializable {

    static final long serialVersionUID = 101L;

    public String name;
    public int year;
    public int month;
    public int day;
    public Bitmap image;
}
