package com.sgy.bezierv2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.sgy.bezierv2.databinding.ActivityMainBinding;

/*
1. 랜덤으로 점 두개를 찍는다.
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding = null;
    PathPoint p0 = new PathPoint();
    PathPoint p1 = new PathPoint();

    private int mWidth;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRandomPoint();
            }
        });

        // binding.mainCanvas.addView(new DrawView(this, p0, p1));

    }

    private void showRandomPoint() {
        // 화면 사이즈
        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        mWidth = display.getWidth();
        mHeight = display.getHeight();

        p0.x = getRandomPoint(0, mWidth);
        p0.y = getRandomPoint(0, mHeight);

        p1.x = getRandomPoint(0, mWidth);
        p1.y = getRandomPoint(0, mHeight);

        binding.mainCanvas.addView(new DrawView(this, p0, p1));
    }

    private double getRandomPoint(double min, double max) {
        Double randomPoint = (Math.random() * (max - min)) + min;
        return randomPoint;
    }
}