package com.appandgo.birthday.Activities;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.appandgo.birthday.R;
import com.appandgo.birthday.components.NDDatePickerDialog;
import com.appandgo.birthday.objects.NDBirthdayObj;

public class NDMainActivity extends NDBaseActivity {

    public static final String ND_BIRTHDAY_OBJ = "ndBirthdayObj";
    private EditText etBirthdayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndmain);

        getViewsIDAndInit();

        handleClicks();
    }

    private void getViewsIDAndInit() {

        etBirthdayDate = (EditText) findViewById(R.id.etBirthdayDate);

    }

    private void handleClicks() {

        etBirthdayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NDDatePickerDialog ndDatePickerDialog = new NDDatePickerDialog(new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Log.d("date", i + "/" + i1 + "/" + i2 );
                    }
                });
                ndDatePickerDialog.show(getSupportFragmentManager(), "datepicker");
            }
        });

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


