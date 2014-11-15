package com.niced.sup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Alejandro on 11/15/14.
 */
public class SplashScreen extends Activity {
    private static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

/* Showing the splash screen with timer

*/
            @Override
            public void run() {
// this method executed once the timer is over
                Intent i = new Intent(SplashScreen.this, Main.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
