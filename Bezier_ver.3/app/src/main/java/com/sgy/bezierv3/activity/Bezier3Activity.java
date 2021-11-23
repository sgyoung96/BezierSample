package com.sgy.bezierv3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.sgy.bezierv3.DrawView;
import com.sgy.bezierv3.Myapplication;
import com.sgy.bezierv3.databinding.ActivityBezier3Binding;

public class Bezier3Activity extends BaseActivity {
    final String TAG = Bezier2Activity.class.getName();

    ActivityBezier3Binding binding = null;

    Canvas canvas;

    float userTouchX1;  // 점
    float userTouchY1;  // 점

    float userTouchX2;  // 직선 (1차원 베지에)
    float userTouchY2;  // 직선 (1차원 베지에)

    float userTouchX3;  // 직선 (2차원 베지에)
    float userTouchY3;  // 직선 (2차원 베지에)

    float userTouchX4;  // 직선 (3차원 베지에)
    float userTouchY4;  // 직선 (3차원 베지에)

    int userTouchCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBezier3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Myapplication.DRAW_BEZIER = 0;
        canvas = new Canvas();

        binding.fabDrawLine.setEnabled(false);
        binding.fabDrawBezier.setEnabled(false);

        binding.fabDrawLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Myapplication.DRAW_BEZIER =+ 3;
                binding.subCanvas.addView(new DrawView(getApplicationContext(), userTouchX1, userTouchY1, userTouchX2, userTouchY2, userTouchX3, userTouchY3, userTouchX4, userTouchY4));
                binding.fabDrawLine.setEnabled(false);
                binding.fabDrawBezier.setEnabled(true);
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBezier1 = new Intent(getApplicationContext(), MainActivity.class);
                goBezier1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(goBezier1);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            userTouchCount++;

            if (userTouchCount >= 3) {
                binding.fabDrawLine.setEnabled(true);
                binding.fabDrawBezier.setEnabled(true);
            }

            switch (userTouchCount) {
                case 1: {
                    userTouchX1 = ev.getX();
                    userTouchY1 = ev.getY() - (getStatusbarHeight() + getActionBarHeight());
                    binding.subCanvas.addView(new DrawView(this, userTouchX1, userTouchY1));
                    break;
                }
                case 2: {
                    userTouchX2 = ev.getX();
                    userTouchY2 = ev.getY() - (getStatusbarHeight() + getActionBarHeight());
                    binding.subCanvas.addView(new DrawView(this, userTouchX2, userTouchY2));
                    break;
                }
                case 3: {
                    userTouchX3 = ev.getX();
                    userTouchY3 = ev.getY() - (getStatusbarHeight() + getActionBarHeight());
                    binding.subCanvas.addView(new DrawView(this, userTouchX3, userTouchY3));
                    break;
                }
                case 4: {
                    userTouchX4 = ev.getX();
                    userTouchY4 = ev.getY() - (getStatusbarHeight() + getActionBarHeight());
                    binding.subCanvas.addView(new DrawView(this, userTouchX4, userTouchY4));
                    break;
                }
                default:
                    break;
            }
        }
        return true;
    }
}