package com.appandgo.birthday.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.appandgo.birthday.R;
import com.appandgo.birthday.fragment.BirthPickerDialog;
import com.appandgo.birthday.dataobjects.BirthdayObj;
import com.appandgo.birthday.utils.Utils;

import java.util.Calendar;
/**
 * Created by liran on 27/4/2018.
 */
public class MainActivity extends BaseActivity {

    public static final String BIRTHDAY_OBJ = "birthdayObj";
    public static final int BITMAP_RESAULT = 100;


    private EditText etName;
    private EditText etBirthdayDate;
    private ImageView imageViewUser;
    private ImageView imageViewOverlay;
    private ImageView imageViewBG;
    private ImageButton imageButtonCamera;
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
        imageViewOverlay.setImageDrawable(getCurrentThemeGrapics(ThemeAssets.OVERLAY));
        imageViewBG.setImageDrawable(getCurrentThemeGrapics(ThemeAssets.BG));
        imageButtonCamera.setImageDrawable(getCurrentThemeGrapics(ThemeAssets.CAMICON));

    }

    private void getViewsIDAndInit() {

        etBirthdayDate = (EditText) findViewById(R.id.etBirthdayDate);
        etName = (EditText) findViewById(R.id.etName);
        imageViewUser = (ImageView) findViewById(R.id.imageViewChild);
        imageViewOverlay = (ImageView) findViewById(R.id.imageViewOverlay);
        imageViewBG= (ImageView) findViewById(R.id.imageViewBg);
        imageButtonCamera= (ImageButton) findViewById(R.id.imageButtonCamera);
        RelativeLayout l= (RelativeLayout) findViewById(R.id.activity_background);
        l.setBackgroundColor(selectedTheme);

        selectedDate = Calendar.getInstance();
    }

    private void handleClicks() {

        etBirthdayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BirthPickerDialog birthPickerDialog = new BirthPickerDialog(new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        selectedDate.set(i, i1, i2);
                        etBirthdayDate.setText(Utils.getDateFormatToShowAsDate(i, i1 - 1, i2));

                    }
                });
                birthPickerDialog.show(getSupportFragmentManager(), "datepicker");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == BITMAP_RESAULT) {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            imageViewUser.setImageBitmap(bp);

        }
    }

    /**
     * Called when the user clicks the imagePicker button
     */
    public void btnImagePickerPressed(View view) {

        Intent intent = new Intent(MainActivity.this, CaptureImageActivity.class);
        startActivity(intent);
        startActivityForResult(intent, BITMAP_RESAULT);
    }

    /**
     * Called when the user clicks the next button
     */
    public void btnNextPressed(View view) {
        BirthdayObj birthdayObj = new BirthdayObj();
        birthdayObj.year = selectedDate.get(Calendar.YEAR);
        birthdayObj.month = selectedDate.get(Calendar.MONTH);;
        birthdayObj.day = selectedDate.get(Calendar.DAY_OF_MONTH);
        birthdayObj.image = null;
        birthdayObj.name = etName.getText().toString();
        birthdayObj.selectedTheme = this.selectedTheme;

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(BIRTHDAY_OBJ, birthdayObj);


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


