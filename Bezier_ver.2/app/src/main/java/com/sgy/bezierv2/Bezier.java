package com.sgy.bezierv2;

public class Bezier {
    private PathPoint p0, p1;       // 제어점 (1차원 베지에 - 점 2개)
    private double ingPercent;      // 0: 시작, 1: 끝
    private PathPoint resultPoint;  // 계산된 결과를 넘겨주는 포인트

    public Bezier() {
        super();

        resultPoint = new PathPoint();
        initResultPoint();
    }

    /**
     * 결과 값을 초기화
     */
    public void initResultPoint(){
        resultPoint.x = 0.0;
        resultPoint.y = 0.0;
        resultPoint.z = 0.0;
    }

    /**
     * 0~1 사이의 진행도 % 를 알려주는 변수 초기화
     */
    public void setIngPercent(double ingPercent) {
        this.ingPercent = ingPercent;
    }

    /**
     * 계산된 결과값 반환
     */
    public PathPoint getResult() {
        return resultPoint;
    }
}
