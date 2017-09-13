package com.appandgo.birthday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NDMainActivity extends NDBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndmain);
    }

    /** Called when the user clicks the next button */
    public void btnNextPressed(View view) {

        Intent i = new Intent(this, NDBirthdayActivity.class);
        startActivity(i);
    }
}
