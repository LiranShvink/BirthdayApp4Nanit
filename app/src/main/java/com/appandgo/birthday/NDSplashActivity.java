package com.appandgo.birthday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by naor on 13/09/2017.
 */

public class NDSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We DON'T call setContentView View is displaying from the theme and this way it is faster than creating a layout

        // Start home activity
        startActivity(new Intent(NDSplashActivity.this, NDMainActivity.class));
        // close splash activity
        finish();

    }

}
