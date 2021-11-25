package com.sgy.bezierv3.Bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.sgy.bezierv3.Myapplication;
import com.sgy.bezierv3.R;
import com.sgy.bezierv3.contract.DrawBezierInterface;
import com.sgy.bezierv3.point.PathPoint;

import java.util.logging.LogRecord;

public class DrawView extends View implements DrawBezierInterface {

    private final String TAG = DrawView.class.getName();

    private Paint mPaint = new Paint();
    private Bezier bezier;
    private PathPoint p0 = null;
    private PathPoint p1 = null;
    private PathPoint p2 = null;
    private PathPoint p3 = null;
    private PathPoint[] points = new PathPoint[2];

    public DrawView() {
        super(Myapplication.context);
    }

    public DrawView(Context context, PathPoint[] points) {
        super(context);
        this.points = points;
        bezier = new Bezier(points);
    }

    public DrawView(Context context, float x, float y) {
        super(context);
        p0 = new PathPoint(x, y);
        bezier = new Bezier(p0);
    }

    public DrawView(Context context, float m0_x, float m0_y, float m1_x, float m1_y) {
        super(context);
        p0 = new PathPoint(m0_x, m0_y);
        p1 = new PathPoint(m1_x, m1_y);
        bezier = new Bezier(p0, p1);
    }

    public DrawView(Context context, float m0_x, float m0_y, float m1_x, float m1_y, float m2_x, float m2_y) {
        super(context);
        p0 = new PathPoint(m0_x, m0_y);
        p1 = new PathPoint(m1_x, m1_y);
        p2 = new PathPoint(m2_x, m2_y);
        bezier = new Bezier(p0, p1, p2);
    }

    public DrawView(Context context, float m0_x, float m0_y, float m1_x, float m1_y, float m2_x, float m2_y, float m3_x, float m3_y) {
        super(context);
        p0 = new PathPoint(m0_x, m0_y);
        p1 = new PathPoint(m1_x, m1_y);
        p2 = new PathPoint(m2_x, m2_y);
        p3 = new PathPoint(m3_x, m3_y);
        bezier = new Bezier(p0, p1, p2, p3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initPaint();

        if (Myapplication.DRAW_BEZIER == Myapplication.DRAW_BEZIER_CURVE_3 || Myapplication.DRAW_BEZIER == Myapplication.DRAW_BEZIER_CURVE_ANIM_3) {
            bezier.drawBezier();
        } else {
            drawPoint(canvas, Myapplication.DRAW_BEZIER);
        }
    }

    private void initPaint() {
        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setStrokeWidth(50);
        mPaint.setAntiAlias(true); // 경계면을 부드럽게
        mPaint.setStrokeCap(Paint.Cap.ROUND); // BUTT : 지정한 좌표에서 선이 끝남 ROUND: 둥근 모양으로 끝이 마무리 SQUARE : 사각형 모양이되 지정된 자표보다 조금 더 그어짐
        mPaint.setStrokeJoin(Paint.Join.ROUND); // MITER : 모서리를 90도 각진 형태 (default 값) BEVEL: 모서리가 깎인 형태 ROUND : 둥근 형태
    }

    /**
     * 점 찍기
     * @param canvas
     */
    private void drawPoint(Canvas canvas, int lineCount) {
        switch (lineCount) {
            case Myapplication.DRAW_BEZIER_POINT: { // 0 : 점
                canvas.drawPoint((float) p0.x,(float) p0.y, mPaint);
                break;
            }
            case Myapplication.DRAW_BEZIER_LINE_1: { // 1
                mPaint.setStrokeWidth(5);
                canvas.drawLine((float) p0.x, (float) p0.y, (float) p1.x, (float) p1.y, mPaint);
                break;
            }
            case Myapplication.DRAW_BEZIER_LINE_2: { // 2
                mPaint.setStrokeWidth(5);
                canvas.drawLine((float) p0.x, (float) p0.y, (float) p1.x, (float) p1.y, mPaint);
                canvas.drawLine((float) p1.x, (float) p1.y, (float) p2.x, (float) p2.y, mPaint);
                break;
            }
            case Myapplication.DRAW_BEZIER_LINE_3: { // 3
                mPaint.setStrokeWidth(5);
                canvas.drawLine((float) p0.x, (float) p0.y, (float) p1.x, (float) p1.y, mPaint);
                canvas.drawLine((float) p1.x, (float) p1.y, (float) p2.x, (float) p2.y, mPaint);
                canvas.drawLine((float) p2.x, (float) p2.y, (float) p3.x, (float) p3.y, mPaint);
                break;
            }
//            case Myapplication.DRAW_BEZIER_CURVE_3: {
//                mPaint.setColor(getResources().getColor(R.color.red));
//                mPaint.setStrokeWidth(10);
//                bezier.drawBezier();
//                canvas.drawLine((float) points[0].x, (float) points[0].y, (float) points[1].x, (float) points[1].y, mPaint);
//                break;
//            }
//            case Myapplication.DRAW_BEZIER_CURVE_ANIM_3: {
//                mPaint.setColor(getResources().getColor(R.color.purple_500));
//                mPaint.setStrokeWidth(20);
//
//                bezier.drawBezier();
//                break;
//            }
            default: break;
        }
    }

    @Override
    public void drawBezier(PathPoint[] points) {
        Canvas canvas = new Canvas();
        switch (Myapplication.DRAW_BEZIER) {
            case Myapplication.DRAW_BEZIER_CURVE_3: {
                initPaint();
                mPaint.setColor(getResources().getColor(R.color.red));
                mPaint.setStrokeWidth(10);
                // bezier.drawBezier();
                canvas.drawLine((float) points[0].x, (float) points[0].y, (float) points[1].x, (float) points[1].y, mPaint);
                break;
            }
            case Myapplication.DRAW_BEZIER_CURVE_ANIM_3: {
                mPaint.setColor(getResources().getColor(R.color.purple_500));
                mPaint.setStrokeWidth(20);

                // bezier.drawBezier();
                break;
            }
            default: break;
        }
        Toast.makeText(Myapplication.context, "for 문이 끝났어요", Toast.LENGTH_SHORT).show();
    }
}
