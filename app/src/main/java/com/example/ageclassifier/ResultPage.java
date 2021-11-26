package com.example.ageclassifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultPage extends AppCompatActivity {

    private static final String TAG = ResultPage.class.getSimpleName();

    public static Intent newIntent(Context context, String result) { Log.d(TAG,"newIntent()");
        return new Intent(context.getApplicationContext(), ResultPage.class)
                .putExtra("RESULT", result);
    }

    private ImageView imgResult;
    private TextView textViewPrediction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        imgResult = (ImageView) findViewById(R.id.resultImage);
        textViewPrediction = (TextView) findViewById(R.id.textViewPrediction);
        imgResult.setImageBitmap(MainActivity.bitmap);
        textViewPrediction.setText(getIntent().getStringExtra("RESULT"));
    }
}