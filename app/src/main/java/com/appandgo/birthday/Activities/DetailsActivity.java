package com.appandgo.birthday.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appandgo.birthday.R;
import com.appandgo.birthday.dataobjects.BirthdayObj;
import com.appandgo.birthday.utils.Utils;

import static com.appandgo.birthday.Activities.MainActivity.BITMAP_RESAULT;
import static com.appandgo.birthday.Activities.MainActivity.BIRTHDAY_OBJ;
/**
 * Created by liran on 27/4/2018.
 */
public class DetailsActivity extends BaseActivity {

    private Button btnShare;
    private ImageView ivAge;
    private ImageView imageViewBG;
    private ImageView imageViewUser;
    private ImageView imageViewOverlay;
    private ImageButton imageButtonCamera;
    private TextView txtAge;
    private TextView txtName;
    private ViewGroup imagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        // Init views
        getViewsIDAndInit();

        setThemeAssets();

        setDataToViews();

    }

    private void setThemeAssets()
    {
        imageViewUser.setImageDrawable(getCurrentThemeGrapics(ThemeAssets.PLACEHOLDER));
        imageViewOverlay.setImageDrawable(getCurrentThemeGrapics(ThemeAssets.OVERLAY));
        imageViewBG.setImageDrawable(getCurrentThemeGrapics(ThemeAssets.BG));
        imageButtonCamera.setImageDrawable(getCurrentThemeGrapics(ThemeAssets.CAMICON));

    }

    private void getViewsIDAndInit() {

        btnShare = (Button) findViewById(R.id.btnShare);
        ivAge = (ImageView) findViewById(R.id.imageAge);
        txtName = (TextView) findViewById(R.id.txtName);
        txtAge = (TextView) findViewById(R.id.txtAge);
        imagePicker = (ViewGroup) getLayoutInflater().inflate(R.layout.view_imagepicker, null);

        imageViewUser = (ImageView) findViewById(R.id.imageViewChild);
        imageViewOverlay = (ImageView) findViewById(R.id.imageViewOverlay);
        imageViewBG= (ImageView) findViewById(R.id.imageViewBg);
        imageButtonCamera= (ImageButton) findViewById(R.id.imageButtonCamera);

        RelativeLayout l = (RelativeLayout) findViewById(R.id.activity_background);
        l.setBackgroundColor(selectedTheme);

        initTransitionAnimation();
    }

    private void initTransitionAnimation()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imagePicker.setTransitionName(getString(R.string.transition_name_imagepicker));
            imageViewBG.setTransitionName(getString(R.string.transition_name_background));
        }
    }

    private void setDataToViews() {
        BirthdayObj obj = (BirthdayObj) getIntent().getSerializableExtra(BIRTHDAY_OBJ);
        if (obj == null) {
            return;
        }

        btnShare.setTransformationMethod(null);
        ivAge.setImageDrawable(Utils.convertNumberToImageDigit(this, Utils.getDateAsSingleNumber(obj.year, obj.month, obj.day)));

        txtName.setText(getFormatName(obj.name));

        txtAge.setText(Utils.getDateFormatToShowAsTimeline(obj.year, obj.month, obj.day));
    }

    /**
     * Called when the user clicks the imagePicker button
     */
    public void btnImagePickerPressed(View view) {

        Intent intent = new Intent(DetailsActivity.this, CaptureImageActivity.class);
        startActivity(intent);
        startActivityForResult(intent, BITMAP_RESAULT);
    }

    private String getFormatName(String name) {
        return getString(R.string.title_name_prefix, name);
    }

    /**
     * Called when the user clicks the next button
     */
    public void btnBackPressed(View view) {
        supportFinishAfterTransition();
    }
}
