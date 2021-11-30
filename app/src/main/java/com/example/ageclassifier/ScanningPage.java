package com.example.ageclassifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

public class ScanningPage extends AppCompatActivity {

    private static final String TAG = ScanningPage.class.getSimpleName();
    private CountDownTimer timer;
    public static Intent newIntent(Context context, String result) { Log.d(TAG,"newIntent()");
        return new Intent(context.getApplicationContext(), ScanningPage.class)
                .putExtra("RESULT", result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_page);
        timer = new CountDownTimer(2000, 1000) {

            public void onTick(long duration) {

            }

            public void onFinish() {
                startActivity(ResultPage.newIntent(getApplicationContext(), getIntent().getStringExtra("RESULT")));
            }

        };
        timer.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}