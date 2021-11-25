package com.sgy.bezierv3.Bezier;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.sgy.bezierv3.Myapplication;
import com.sgy.bezierv3.activity.BaseActivity;
import com.sgy.bezierv3.point.PathPoint;

public class Bezier {
    private final String TAG = Bezier.class.getName();

    private PathPoint p0, p1, p2, p3;
    private PathPoint[] arrPn;      // n 차원의 조절 포인트 저장
    private double mu;              // 곡선의 시작과 끝을 알리는 변수 (0~1), 0: 시작, 1: 끝
    private PathPoint resultPoint;  // 계산된 결과를 넘겨주는 포인트

    // private DrawView drawView = new DrawView();
    private PathPoint[] points;

    public Bezier() {

    }

    public Bezier(PathPoint[] points) {
        super();
        this.points = points;
        resultPoint = new PathPoint();
        initResultPoint();
    }

    public Bezier(PathPoint p0) {
        super();
        this.p0 = p0;

        resultPoint = new PathPoint();
        initResultPoint();
    }

    public Bezier(PathPoint p0, PathPoint p1) {
        super();
        this.p0 = p0;
        this.p1 = p1;

        resultPoint = new PathPoint();
        initResultPoint();
    }

    public Bezier(PathPoint p0, PathPoint p1, PathPoint p2) {
        super();
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;

        resultPoint = new PathPoint();
        initResultPoint();
    }

    public Bezier(PathPoint p0, PathPoint p1, PathPoint p2, PathPoint p3) {
        super();
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        resultPoint = new PathPoint();
        initResultPoint();
    }

    /**
     * 베지에 곡선 그리기
     */
    public void drawBezier() {
        switch (Myapplication.DRAW_BEZIER) {
            case Myapplication.DRAW_BEZIER_CURVE_3: { // 3차원 베지에 곡선 그리기
//                PathPoint[] points = drawBezier3();
//
//                drawView.drawBezier(points);
                drawBezier3();
                break;
            }
            case Myapplication.DRAW_BEZIER_CURVE_ANIM_3: { // 3차원 베지에 곡선 애니메이션
//                drawBezier3Anim(canvas, mPaint);
                break;
            }
        }
    }

    public void setBezier3(PathPoint[] points) {
        this.points = points;
        resultPoint = new PathPoint();
        initResultPoint();
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
        } else {
            resultPoint.x = p0.x;
            resultPoint.y = p0.y;
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
        } else {
            double mum1, mum13, mu3;
            mum1 = 1 - mu;
            mum13 = mum1 * mum1 * mum1;
            mu3 = mu * mu * mu;
            resultPoint.x = mum13 * p0.x + 3 * mu * mum1 * mum1 * p1.x + 3 * mu * mu * mum1 * p2.x + mu3 * p3.x;
            resultPoint.y = mum13 * p0.y + 3 * mu * mum1 * mum1 * p1.y + 3 * mu * mu * mum1 * p2.y + mu3 * p3.y;
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
        // 선 간격
        double muGap = 1.0 / 1000; //DRAW_LINE_COUNT;

        PathPoint startP = new PathPoint();
        PathPoint endP = new PathPoint();
        PathPoint[] pointArr = new PathPoint[2];

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
//            canvas.drawLine((float) startP.x, (float) startP.y, (float) endP.x, (float) endP.y, mPaint);
            pointArr = new PathPoint[]{startP, endP};
            // drawView.drawBezier(pointArr);
        }
//        return pointArr;
    }

//    private void drawBezier3Anim(Canvas canvas, Paint mPaint) {
//        // 선 간격
//        double muGap = 1.0 / 1000; //DRAW_LINE_COUNT;
//
//        PathPoint startP = new PathPoint();
//        PathPoint endP = new PathPoint();
//
//        // mu 값이 1이 되었을 때가 목표점에 도달했다는 의미
//        for (double mu = 0; mu <= 1; mu += muGap) {
//            // 선 시작점
//            startP.x = getResult().x;
//            startP.y = getResult().y;
//
//            // 베지에 곡선 다음 위치를 얻는다.
//            setMu(mu);      // 비율
//            nextBezier3();  // 비율에 따라 그리기
//
//            // 선 끝 점
//            endP.x = getResult().x;
//            endP.y = getResult().y;
//            canvas.drawLine((float) startP.x, (float) startP.y, (float) endP.x, (float) endP.y, mPaint);
//        }
//    }

//    /**
//     * N차원 베지어 곡선 포인터 설정
//     */
//    public void setBezierN(PathPoint[] arrPn) {
//        this.arrPn = arrPn;
//    }
//
//    /**
//     * N차원 베지어 곡선 계산
//     */
//    public void nextBezierN() {
//        int k, kn, nn, nkn;
//        double blend, muk, munk;
//        int n = arrPn.length - 1;
//
//        initResultPoint();
//
//        muk = 1;
//        munk = Math.pow(1 - mu, (double) n);
//
//        for (k = 0; k <= n; k++) {
//            nn = n;
//            kn = k;
//            nkn = n - k;
//            blend = muk * munk;
//            muk *= mu;
//            munk /= (1 - mu);
//            while (nn >= 1) {
//                blend *= nn;
//                nn--;
//                if (kn > 1) {
//                    blend /= (double) kn;
//                    kn--;
//                }
//                if (nkn > 1) {
//                    blend /= (double) nkn;
//                    nkn--;
//                }
//            }
//            resultPoint.x += arrPn[k].x * blend;
//            resultPoint.y += arrPn[k].y * blend;
//            resultPoint.z += arrPn[k].z * blend;
//        }
//    }
}
