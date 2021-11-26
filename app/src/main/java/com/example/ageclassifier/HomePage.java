package com.example.ageclassifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomePage extends AppCompatActivity {
    private ImageButton exploreButton;
    private ImageButton uploadButton;
    private ImageButton captureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        exploreButton = (ImageButton) findViewById(R.id.exploreButton);
        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExplorePage();
            }
        });
    }
    public void openExplorePage() {
        Intent intent = new Intent(this, ExplorePage.class);
        startActivity(intent);
    }
}