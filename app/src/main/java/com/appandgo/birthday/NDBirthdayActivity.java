package com.appandgo.birthday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class NDBirthdayActivity extends NDBaseActivity {

    private Button btnShare;

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

    }

}
