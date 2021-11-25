package com.sgy.bezierv3.Bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sgy.bezierv3.Myapplication;
import com.sgy.bezierv3.R;
import com.sgy.bezierv3.point.PathPoint;

public class DrawView2 extends FrameLayout {

    private Paint mPaint = new Paint();
    private Paint bezierPaint = new Paint();

    private Path path;

    private double mu;              // 곡선의 시작과 끝을 알리는 변수 (0~1), 0: 시작, 1: 끝
    private PathPoint resultPoint;  // 계산된 결과를 넘겨주는 포인트

    // private DrawView drawView = new DrawView();
    private PathPoint[] points;
    private boolean isBezierDraw = false;

    float userTouchX1 = 0;  // 점
    float userTouchY1 = 0;  // 점

    float userTouchX2 = 0;  // 직선 (1차원 베지에)
    float userTouchY2 = 0;  // 직선 (1차원 베지에)

    float userTouchX3 = 0;  // 직선 (2차원 베지에)
    float userTouchY3 = 0;  // 직선 (2차원 베지에)

    float userTouchX4 = 0;  // 직선 (3차원 베지에)
    float userTouchY4 = 0;  // 직선 (3차원 베지에)

    int userTouchCount = 0;

    public DrawView2(@NonNull Context context) {
        super(context);
        initView();
    }

    public DrawView2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawView2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        resultPoint = new PathPoint();
        path = new Path();
        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setStrokeWidth(50);
        mPaint.setAntiAlias(true); // 경계면을 부드럽게
        mPaint.setStrokeCap(Paint.Cap.ROUND); // BUTT : 지정한 좌표에서 선이 끝남 ROUND: 둥근 모양으로 끝이 마무리 SQUARE : 사각형 모양이되 지정된 자표보다 조금 더 그어짐
        mPaint.setStrokeJoin(Paint.Join.ROUND); // MITER : 모서리를 90도 각진 형태 (default 값) BEVEL: 모서리가 깎인 형태 ROUND : 둥근 형태

        bezierPaint.setColor(getResources().getColor(R.color.red));
        bezierPaint.setStrokeWidth(10);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            userTouchCount++;

            if (userTouchCount > 4) {
                return false;
            }

            switch (userTouchCount) {
                case 1: {
                    userTouchX1 = ev.getX();
                    userTouchY1 = ev.getY();

                    // binding.subCanvas.addView(new DrawView(this, userTouchX1, userTouchY1));
                    break;
                }
                case 2: {
                    userTouchX2 = ev.getX();
                    userTouchY2 = ev.getY();
                    // binding.subCanvas.addView(new DrawView(this, userTouchX2, userTouchY2));
                    break;
                }
                case 3: {
                    userTouchX3 = ev.getX();
                    userTouchY3 = ev.getY();
                    // binding.subCanvas.addView(new DrawView(this, userTouchX3, userTouchY3));
                    break;
                }
                case 4: {
                    userTouchX4 = ev.getX();
                    userTouchY4 = ev.getY();
                    // binding.subCanvas.addView(new DrawView(this, userTouchX4, userTouchY4));
                    break;
                }
                default:
                    break;
            }
            invalidate();
        }
        return true;
    }

    public void drawBezier() {
        // bezier = new Bezier();
        points = new PathPoint[4];
        points[0] = new PathPoint();
        points[0].x = userTouchX1;
        points[0].y = userTouchY1;
        points[1] = new PathPoint();
        points[1].x = userTouchX2;
        points[1].y = userTouchY2;
        points[2] = new PathPoint();
        points[2].x = userTouchX3;
        points[2].y = userTouchY3;
        points[3] = new PathPoint();
        points[3].x = userTouchX4;
        points[3].y = userTouchY4;
        initResultPoint();
        isBezierDraw = true;
        invalidate();
    }

    /**
     * u변수를 설정
     */
    public void setMu(double mu) {
        this.mu = mu;
    }

    /**
     * 결과 값을 초기화
     */
    public void initResultPoint() {
        if (Myapplication.DRAW_BEZIER == Myapplication.DRAW_BEZIER_CURVE_3 || Myapplication.DRAW_BEZIER == Myapplication.DRAW_BEZIER_CURVE_ANIM_3) {
            resultPoint.x = points[0].x;
            resultPoint.y = points[0].y;
        }
    }

    /**
     * 3차 베지에 곡선 계산
     */
    private void nextBezier3() {

        if (Myapplication.DRAW_BEZIER == Myapplication.DRAW_BEZIER_CURVE_3 || Myapplication.DRAW_BEZIER == Myapplication.DRAW_BEZIER_CURVE_ANIM_3) {
            double mum1, mum13, mu3;
            mum1 = 1 - mu;
            mum13 = mum1 * mum1 * mum1;
            mu3 = mu * mu * mu;
            resultPoint.x = mum13 * points[0].x + 3 * mu * mum1 * mum1 * points[1].x + 3 * mu * mu * mum1 * points[2].x + mu3 * points[3].x;
            resultPoint.y = mum13 * points[0].y + 3 * mu * mum1 * mum1 * points[1].y + 3 * mu * mu * mum1 * points[2].y + mu3 * points[3].y;
        }
    }

    /**
     * 계산 결과값을 넘긴다.
     */
    public PathPoint getResult() {
        return resultPoint;
    }

    /**
     * 3차 베지에 곡선 그리기
     */
    private void drawBezier3() {

//        return pointArr;
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(userTouchX1 != 0 && userTouchY1 != 0) {
            canvas.drawPoint(userTouchX1, userTouchY1, mPaint);
        }

        if(userTouchX2 != 0 && userTouchY2 != 0) {
            canvas.drawPoint(userTouchX2, userTouchY2, mPaint);
        }

        if(userTouchX3 != 0 && userTouchY3 != 0) {
            canvas.drawPoint(userTouchX3, userTouchY3, mPaint);
        }

        if(userTouchX4 != 0 && userTouchY4 != 0) {
            canvas.drawPoint(userTouchX4, userTouchY4, mPaint);
        }

        if(isBezierDraw) {
            // 선 간격
            double muGap = 1.0 / 1000; //DRAW_LINE_COUNT;
            PathPoint startP = new PathPoint();
            PathPoint endP = new PathPoint();
            // mu 값이 1이 되었을 때가 목표점에 도달했다는 의미
            for (double mu = 0; mu <= 1; mu += muGap) {
                // 선 시작점
                startP.x = getResult().x;
                startP.y = getResult().y;

                // 베지에 곡선 다음 위치를 얻는다.
                setMu(mu);      // 비율
                nextBezier3();  // 비율에 따라 그리기

                // 선 끝 점
                endP.x = getResult().x;
                endP.y = getResult().y;
                canvas.drawLine((float) startP.x, (float) startP.y, (float) endP.x, (float) endP.y, bezierPaint);

            }
        }



    }
}
