package com.appandgo.birthday.Activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appandgo.birthday.R;
import com.appandgo.birthday.objects.NDBirthdayObj;
import com.appandgo.birthday.utils.NDUtils;

import static com.appandgo.birthday.Activities.NDMainActivity.ND_BIRTHDAY_OBJ;

public class NDBirthdayActivity extends NDBaseActivity {

    private Button btnShare;
    private ImageView ivAge;
    private ImageView ivOverlay;
    private TextView txtAge;
    private TextView txtName;
    private ViewGroup imagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        // Init views
        getViewsIDAndInit();

        setDataToViews();

    }

    private void getViewsIDAndInit() {

        btnShare = (Button) findViewById(R.id.btnShare);
        ivAge = (ImageView) findViewById(R.id.imageAge);
        ivOverlay = (ImageView) findViewById(R.id.imageViewBg);
        txtName = (TextView) findViewById(R.id.txtName);
        txtAge = (TextView) findViewById(R.id.txtAge);
        imagePicker = (ViewGroup) getLayoutInflater().inflate(R.layout.view_imagepicker, null);

        initTransitionAnimation();
    }

    private void initTransitionAnimation()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imagePicker.setTransitionName(getString(R.string.transition_name_imagepicker));
            ivOverlay.setTransitionName(getString(R.string.transition_name_background));

//            ivOverlay.bringToFront();
        }
    }

    private void setDataToViews() {
        NDBirthdayObj obj = (NDBirthdayObj) getIntent().getSerializableExtra(ND_BIRTHDAY_OBJ);
        if (obj == null) {
            return;
        }

        btnShare.setTransformationMethod(null);

        ivAge.setImageDrawable(NDUtils.convertNumberToImageDigit(this, 5));

        txtName.setText(getFormatName(obj.name));

        txtAge.setText("Months old!");
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
