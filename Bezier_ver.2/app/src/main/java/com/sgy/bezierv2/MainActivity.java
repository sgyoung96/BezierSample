package com.sgy.bezierv2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import com.sgy.bezierv2.databinding.ActivityMainBinding;

/*
1. 랜덤으로 점 두개를 찍는다.
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        double startP = getRandomPoint();
//        double endP = getRandomPoint();
//
//        Canvas canvas = new Canvas();
//        Paint mPaint = new Paint();
//        mPaint.setColor(getResources().getColor(R.color.red));
//        mPaint.setStrokeWidth(100);
//        canvas.drawPoint((float) startP, (float) endP, mPaint);

        binding.mainCanvas.addView(new DrawView(this));

    }

    private double getRandomPoint() {
        return Math.random();
    }
}