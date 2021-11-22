package com.sgy.bezierv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.sgy.bezierv3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding = null;

    float userTouchX;
    float userTouchY;

    private int mWidth;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                // 화면 사이즈
//                Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
//                mWidth = display.getWidth();
//                mHeight = display.getHeight();

                userTouchX = ev.getX();
                userTouchY = ev.getY();
//                userTouchX = getPosition(ev.getX(), mWidth);
//                userTouchY = getPosition(ev.getY(), mHeight);
                binding.mainCanvas.addView(new DrawView(this, userTouchX, userTouchY));
            }
        }

        return true;
    }

    private float getPosition(float min, float max) {
        float pointPosition = (0.01f * (max - min)) + min;
        return pointPosition;
    }
}