package com.appandgo.birthday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appandgo.birthday.utils.NDUtils;

public class NDBirthdayActivity extends NDBaseActivity {

    private Button btnShare;
    private ImageView ivAge;
    private TextView txtAge;
    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        // Init views
        getViewsIDAndInit();

    }

    private void getViewsIDAndInit() {

        btnShare = (Button) findViewById(R.id.btnShare);
        btnShare.setTransformationMethod(null);

        ivAge = (ImageView) findViewById(R.id.imageAge);
        ivAge.setImageDrawable(NDUtils.convertNumberToImageDigit(this, 5));

        txtName = (TextView) findViewById(R.id.txtName);
        txtName.setText(getFormatName("Naor"));

        txtAge = (TextView) findViewById(R.id.txtAge);
        txtAge.setText("Months old!");
    }

    private String getFormatName(String name)
    {
        return getString(R.string.title_name_prefix, name);
    }
}
