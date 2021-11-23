package com.sgy.bezierv3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {

    Paint mPaint = new Paint();
    PathPoint p0 = null;
    PathPoint p1 = null;
    PathPoint p2 = null;
    PathPoint p3 = null;

    public DrawView(Context context, float x, float y) {
        super(context);
        p0 = new PathPoint(x, y);
    }

    public DrawView(Context context, float m0_x, float m0_y, float m1_x, float m1_y) {
        super(context);
        p0 = new PathPoint(m0_x, m0_y);
        p1 = new PathPoint(m1_x, m1_y);
        initPaint();
    }

    public DrawView(Context context, float m0_x, float m0_y, float m1_x, float m1_y, float m2_x, float m2_y) {
        super(context);
        p0 = new PathPoint(m0_x, m0_y);
        p1 = new PathPoint(m1_x, m1_y);
        p2 = new PathPoint(m2_x, m2_y);
    }

    public DrawView(Context context, float m0_x, float m0_y, float m1_x, float m1_y, float m2_x, float m2_y, float m3_x, float m3_y) {
        super(context);
        p0 = new PathPoint(m0_x, m0_y);
        p1 = new PathPoint(m1_x, m1_y);
        p2 = new PathPoint(m2_x, m2_y);
        p3 = new PathPoint(m3_x, m3_y);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initPaint();
        drawPoint(canvas, Myapplication.DRAW_BEZIER);
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
            case Myapplication.DRAW_BEZIER_0: { // 0
                canvas.drawPoint((float) p0.x,(float) p0.y, mPaint);
                break;
            }
            case Myapplication.DRAW_BEZIER_1: { // 1
                canvas.drawPoint((float) p0.x,(float) p0.y, mPaint);
                mPaint.setStrokeWidth(15);
                canvas.drawLine((float) p0.x, (float) p0.y, (float) p1.x, (float) p1.y, mPaint);
                break;
            }
            case Myapplication.DRAW_BEZIER_2: { // 2
                canvas.drawLine((float) p0.x, (float) p0.y, (float) p1.x, (float) p1.y, mPaint);
                canvas.drawLine((float) p1.x, (float) p1.y, (float) p2.x, (float) p2.y, mPaint);
                break;
            }
            case Myapplication.DRAW_BEZIER_3: { // 3
                canvas.drawLine((float) p0.x, (float) p0.y, (float) p1.x, (float) p1.y, mPaint);
                canvas.drawLine((float) p1.x, (float) p1.y, (float) p2.x, (float) p2.y, mPaint);
                canvas.drawLine((float) p2.x, (float) p2.y, (float) p3.x, (float) p3.y, mPaint);
                break;
            }
            default: break;
        }
    }

    /**
     * 1차원 베지에곡선용 직선생성하기
     * @param canvas
     */
    public void drawLine(Canvas canvas, int lineCount) {
        mPaint.setStrokeWidth(15);
        switch (lineCount) {
            case 1: {
                canvas.drawLine((float) p0.x, (float) p0.y, (float) p1.x, (float) p1.y, mPaint);
                break;
            }
            case 2: {
                canvas.drawLine((float) p0.x, (float) p0.y, (float) p1.x, (float) p1.y, mPaint);
                canvas.drawLine((float) p1.x, (float) p1.y, (float) p2.x, (float) p2.y, mPaint);
                break;
            }
            case 3: {
                canvas.drawLine((float) p0.x, (float) p0.y, (float) p1.x, (float) p1.y, mPaint);
                canvas.drawLine((float) p1.x, (float) p1.y, (float) p2.x, (float) p2.y, mPaint);
                canvas.drawLine((float) p2.x, (float) p2.y, (float) p3.x, (float) p3.y, mPaint);
                break;
            }
        }
    }
}
