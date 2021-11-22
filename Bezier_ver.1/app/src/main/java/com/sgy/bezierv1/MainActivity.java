package com.sgy.bezierv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.sgy.bezierv1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding = null;

    private static final int POINT_COUNT = 5; // n차원의 갯수 지정
    private static final int DRAW_LINE_COUNT = 26;

    private Bezier bezier;
    private View drawView;
    private int mWidth;
    private int mHeight;

    private int menuSelectedId = Menu.FIRST;

    private Paint mPaint;

    private static final int BEZIER3_MENU_ID = Menu.FIRST;
    private static final int BEZIER4_MENU_ID = Menu.FIRST + 1;
    private static final int BEZIERN_MENU_ID = Menu.FIRST + 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView = new MyView(this);
        setContentView(drawView);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        // 화면 사이즈
        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        mWidth = display.getWidth();
        mHeight = display.getHeight();

        // 베지에 곡선 초기화 (기본 3차원)
        bezier = new Bezier();
        settingBezier3();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFF0000);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
    }

    public void colorChanged(int color) {
        mPaint.setColor(color);
    }

    public class MyView extends View {

        public MyView(Context c) {
            super(c);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(0xFFAAAAAA);
            // 선 간격
            double muGap = 1.0/DRAW_LINE_COUNT;

            PathPoint startP = new PathPoint();
            PathPoint endP = new PathPoint();

            // mu 값이 1이 되었을 때가 목표점에 도달했다는 의미
            for (double mu = 0; mu <= 1; mu += muGap) {
                // 선 시작점
                startP.x = bezier.getResult().x;
                startP.y = bezier.getResult().y;

                // 베지에 곡선 다음 위치를 얻는다.
                bezier.setMu(mu);
                switch (menuSelectedId) {
                    case BEZIER3_MENU_ID : {
                        bezier.nextBezier3();
                        break;
                    }
                    case BEZIER4_MENU_ID : {
                        bezier.nextBezier4();
                    }
                    case BEZIERN_MENU_ID : {
                        bezier.nextBezierN();
                        break;
                    }
                }

                // 선 끝 점
                endP.x = bezier.getResult().x;
                endP.y = bezier.getResult().y;
                canvas.drawLine((float)startP.x, (float)startP.y, (float)endP.x, (float)endP.y, mPaint);
            }

            super.onDraw(canvas);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, BEZIER3_MENU_ID, 0, "3개 점");
        menu.add(0, BEZIER4_MENU_ID, 0, "4개 점");
        menu.add(0, BEZIERN_MENU_ID, 0, "n개 점");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xFF);

        menuSelectedId = item.getItemId();

        switch (item.getItemId()) {
            case BEZIER3_MENU_ID: {
                settingBezier3();
                drawView.invalidate();
                return true;
            }
            case BEZIER4_MENU_ID: {
                settingBezier4();
                drawView.invalidate();
                return true;
            }
            case BEZIERN_MENU_ID: {
                settingBezierN();
                drawView.invalidate();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void settingBezier3() {
        PathPoint[] arrP3 = new PathPoint[3];

        for (int index = 0; index < arrP3.length; index ++) {
            PathPoint p = new PathPoint();
            p.x = getRandomDoubleNum(0, mWidth);
            p.y = getRandomDoubleNum(0, mHeight);

            arrP3[index] = p;
            // p.z = getRandomDoubleNum(0,1); // canvas 는 2차원이라서 삭제
        }
        bezier.setBezier3(arrP3[0], arrP3[1], arrP3[2]);
    }

    private void settingBezier4() {
        PathPoint[] arrP4 = new PathPoint[4];

        for (int index = 0; index < arrP4.length; index++) {
            PathPoint p = new PathPoint();
            p.x = getRandomDoubleNum(0, mWidth);
            p.y = getRandomDoubleNum(0, mHeight);
            arrP4[index] = p;
        }
        bezier.setBezier4(arrP4[0], arrP4[1], arrP4[2], arrP4[3]);
    }

    private void settingBezierN() {
        PathPoint[] arrPn = new PathPoint[4];

        for (int index = 0; index < arrPn.length; index++) {
            PathPoint p = new PathPoint();
            p.x = getRandomDoubleNum(0, mWidth);
            p.y = getRandomDoubleNum(0, mHeight);
            arrPn[index] = p;
        }
        bezier.setBezierN(arrPn);
    }

    private double getRandomDoubleNum(double min, double max) {
        Double randomNum = (Math.random() * (max - min)) + min;
        return randomNum;
    }
}