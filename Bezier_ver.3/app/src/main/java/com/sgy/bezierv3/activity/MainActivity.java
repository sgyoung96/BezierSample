package com.sgy.bezierv3.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.sgy.bezierv3.Bezier.DrawView;
import com.sgy.bezierv3.Myapplication;
import com.sgy.bezierv3.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    private final String TAG = MainActivity.class.getName();

    private ActivityMainBinding binding = null;

    private DrawView drawView;
    private Canvas canvas;

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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Myapplication.context = getApplicationContext();

        Myapplication.DRAW_BEZIER = 0;
        canvas = new Canvas();

        binding.fabDrawLine.setEnabled(false);
        binding.fabDrawBezier.setEnabled(false);
        binding.fabDrawAnim.setEnabled(false);

        binding.fabDrawLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Myapplication.DRAW_BEZIER++;
                binding.subCanvas.addView(new DrawView(getApplicationContext(), userTouchX1, userTouchY1, userTouchX2, userTouchY2));
                binding.fabDrawLine.setEnabled(false);
                binding.fabDrawBezier.setEnabled(true);
                binding.fabDrawAnim.setEnabled(true);
            }
        });

        binding.fabDrawBezier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Myapplication.DRAW_BEZIER = Myapplication.DRAW_BEZIER_CURVE_1;
                // setBezier();
                binding.subCanvas.addView(new DrawView(getApplicationContext(), userTouchX1, userTouchY1, userTouchX2, userTouchY2));
                binding.fabDrawLine.setEnabled(false);
                binding.fabDrawBezier.setEnabled(false);
                binding.fabDrawBezier.setEnabled(false);
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBezier2 = new Intent(getApplicationContext(), Bezier2Activity.class);
                goBezier2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(goBezier2);
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

            if (userTouchCount >= 2) {
                binding.fabDrawLine.setEnabled(true);
                binding.fabDrawBezier.setEnabled(false);
                binding.fabDrawAnim.setEnabled(false);
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
                default:
                    break;
            }
        }
        return true;
    }
}