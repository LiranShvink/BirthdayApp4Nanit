package com.appandgo.birthday.Activities;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.nfc.NfcManager;
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
import com.appandgo.birthday.utils.NDUtils;

import java.util.Calendar;

public class NDMainActivity extends NDBaseActivity {

    public static final String ND_BIRTHDAY_OBJ = "ndBirthdayObj";
    private EditText etName;
    private EditText etBirthdayDate;
    private ViewGroup imagePicker;
    private ImageView imageViewUser;
    private Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndmain);

        getViewsIDAndInit();

        setThemeAssets();

        handleClicks();
    }

    private void setThemeAssets() {

        imageViewUser.setImageDrawable(getCurrentThemeGrapics(ThemeAssets.PLACEHOLDER));
    }

    private void getViewsIDAndInit() {

        etBirthdayDate = (EditText) findViewById(R.id.etBirthdayDate);
        etName = (EditText) findViewById(R.id.etName);
        imagePicker = (ViewGroup) getLayoutInflater().inflate(R.layout.view_imagepicker, null);
        imageViewUser = (ImageView) findViewById(R.id.imageViewChild);

        selectedDate = Calendar.getInstance();
    }

    private void handleClicks() {

        etBirthdayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NDDatePickerDialog ndDatePickerDialog = new NDDatePickerDialog(new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        selectedDate.set(i, i1, i2);
                        etBirthdayDate.setText(NDUtils.getDateFormatToShowAsDate(i, i1 - 1, i2));

                    }
                });
                ndDatePickerDialog.show(getSupportFragmentManager(), "datepicker");
            }
        });

    }

    /**
     * Called when the user clicks the imagePicker button
     */
    public void btnImagePickerPressed(View view) {
        NDCaptureAndCropActivity captureActivity = new NDCaptureAndCropActivity();
        captureActivity.setListener(new NDCaptureAndCropActivity.OnImageCroppedListener() {
            @Override
            public void onImageCropped(Bitmap bitmap) {
               imageViewUser.setImageBitmap(bitmap);
            }
        });
        Intent intent = new Intent(NDMainActivity.this, NDCaptureAndCropActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the next button
     */
    public void btnNextPressed(View view) {
        NDBirthdayObj ndBirthdayObj = new NDBirthdayObj();
        ndBirthdayObj.year = selectedDate.get(Calendar.YEAR);
        ndBirthdayObj.month = selectedDate.get(Calendar.MONTH);;
        ndBirthdayObj.day = selectedDate.get(Calendar.DAY_OF_MONTH);
        ndBirthdayObj.image = null;
        ndBirthdayObj.name = etName.getText().toString();
        ndBirthdayObj.selectedTheme = this.selectedTheme;

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


