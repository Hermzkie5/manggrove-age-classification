package com.example.ageclassifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultPage extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ResultPage.class.getSimpleName();

    public static Intent newIntent(Context context, String result) { Log.d(TAG, "newIntent()");
        return new Intent(context.getApplicationContext(), ResultPage.class)
                .putExtra("RESULT", result);
    }

    private ImageButton backButton;
    private ImageView imgResult;
    private TextView textViewPrediction;
    private ImageButton SaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        imgResult = (ImageView) findViewById(R.id.resultImage);
        textViewPrediction = (TextView) findViewById(R.id.textViewPrediction);
        backButton = (ImageButton) findViewById(R.id.backButton );
        SaveButton = (ImageButton) findViewById( R.id.saveButton);
        imgResult.setImageBitmap(MainActivity.bitmap);
        textViewPrediction.setText(getIntent().getStringExtra("RESULT"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backButton:
                onBackPressed();
                break;
            case R.id.saveButton:
                //BitmapDrawable drawable = (BitmapDrawable) imgResult.getDrawable();
                Bitmap bitmap = MainActivity.bitmap; //Bitmap bitmap = drawable.getBitmap();

                File filepath = getApplicationContext().getCacheDir();
                Log.d(TAG,"Environment.getExternalStorageDirectory().getPath()-" + Environment.getExternalStorageDirectory().getPath());
                Log.d(TAG,"filepath.getAbsolutePath()-" + filepath.getAbsolutePath());
                Log.d(TAG,"getApplicationContext().getFilesDir()-" + getApplicationContext().getFilesDir());
                Log.d(TAG,"getApplicationContext().getCacheDir()-" + getApplicationContext().getCacheDir());

                File dir = new File( filepath,"Mangrove Age Classification");
                Log.d(TAG,"dir.exists()-" + dir.exists());
                if (!dir.exists() && !dir.mkdir()) {
                    Log.d(TAG,"failed to create directory Mangrove Age Classification");
                } else if (dir.exists()) {
                    Log.d(TAG,"Already created directory Mangrove Age Classification");
                }

                try { Log.d(TAG,"try");
                    //region create a file to write bitmap data
                    File file = new File(dir, System.currentTimeMillis() + ".jpeg");
                    file.createNewFile();
                    //endregion
                    //region Convert bitmap to byte array
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress( Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] bitmapdata = baos.toByteArray();
                    //endregion
                    //region write the bytes in file
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    //endregion
                    Toast.makeText( getApplicationContext(),"Image Save.", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) { e.printStackTrace();
                    Log.e(TAG,"catch FileNotFoundException " + e.getMessage());
                } catch (IOException e) { e.printStackTrace();
                    Log.e(TAG,"catch IOException " + e.getMessage());
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        backButton.setOnClickListener(this::onClick);
        SaveButton.setOnClickListener(this::onClick);
    }

    @Override
    protected void onPause() {
        super.onPause();
        backButton.setOnClickListener(null);
        SaveButton.setOnClickListener(null);
    }

    @Override
    public void onBackPressed() { //super.onBackPressed();
        startActivity(MainActivity.newIntent(this));
    }
}