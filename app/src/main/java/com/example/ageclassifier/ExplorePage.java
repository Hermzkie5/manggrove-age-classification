package com.example.ageclassifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.slider.Slider;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class ExplorePage extends AppCompatActivity {

    private ImageButton backButton;
    SliderView SliderView;
    int[] images = {R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_page);
        SliderView = findViewById( R.id.image_slider );
        SliderAdapter sliderAdapter = new SliderAdapter( images );

        SliderView.setSliderAdapter(sliderAdapter);
        SliderView.setIndicatorAnimation( IndicatorAnimationType.WORM);
        SliderView.setSliderTransformAnimation( SliderAnimations.DEPTHTRANSFORMATION);

        backButton = (ImageButton) findViewById( R.id.backButton );
        backButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity();
            }
        } );
    }
    private void MainActivity() {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity( intent );

    }
}