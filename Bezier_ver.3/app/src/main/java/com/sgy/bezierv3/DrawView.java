package com.sgy.bezierv3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {

    Paint mPaint = new Paint();
    PathPoint p0 = null;

    public DrawView(Context context, float x, float y) {
        super(context);
        p0 = new PathPoint(x, y);
//        p0.x = x;
//        p0.y = y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setStrokeWidth(50);
        mPaint.setAntiAlias(true); // 경계면을 부드럽게
        mPaint.setStrokeCap(Paint.Cap.ROUND); // BUTT : 지정한 좌표에서 선이 끝남 ROUND: 둥근 모양으로 끝이 마무리 SQUARE : 사각형 모양이되 지정된 자표보다 조금 더 그어짐
        mPaint.setStrokeJoin(Paint.Join.ROUND); // MITER : 모서리를 90도 각진 형태 (default 값) BEVEL: 모서리가 깎인 형태 ROUND : 둥근 형태

        canvas.drawPoint((float) p0.x,(float) p0.y, mPaint);
    }
}
