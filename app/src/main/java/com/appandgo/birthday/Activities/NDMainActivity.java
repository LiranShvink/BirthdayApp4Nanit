package com.appandgo.birthday.Activities;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.DatePicker;

import com.appandgo.birthday.R;
import com.appandgo.birthday.objects.NDBirthdayObj;

public class NDMainActivity extends NDBaseActivity {

    public static final String ND_BIRTHDAY_OBJ = "ndBirthdayObj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndmain);
    }

    /**
     * Called when the user clicks the next button
     */
    public void btnNextPressed(View view) {

        NDBirthdayObj ndBirthdayObj = new NDBirthdayObj();
        ndBirthdayObj.day = 7;
        ndBirthdayObj.month = 4;
        ndBirthdayObj.year = 1979;
        ndBirthdayObj.image = null;
        ndBirthdayObj.name = "bar";

        Intent intent = new Intent(this, NDBirthdayActivity.class);
        intent.putExtra(ND_BIRTHDAY_OBJ, ndBirthdayObj);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            // get the common element for the transition in this activity
            final View imagePicker = findViewById(R.id.imagePicker);
            final View bg = findViewById(R.id.imageViewBg);
            // create the transition animation - the images in the layouts
            Pair<View, String> pair1 = Pair.create(imagePicker, getString(R.string.transition_name_imagepicker));
            Pair<View, String> pair2 = Pair.create(bg, getString(R.string.transition_name_background));

            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(this, pair1, pair2);

            // start the new activity
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

}


