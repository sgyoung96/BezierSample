package com.sgy.bezierv3;

import android.app.Application;
import android.content.Context;

public class Myapplication extends Application {
    public static Context context = null;

    public static final int DRAW_BEZIER_POINT = 0;
    public static final int DRAW_BEZIER_LINE_1 = 1;
    public static final int DRAW_BEZIER_LINE_2 = 2;
    public static final int DRAW_BEZIER_LINE_3 = 3;
    public static Integer DRAW_BEZIER = null;

    public static final int DRAW_BEZIER_CURVE_1 = 11;
    public static final int DRAW_BEZIER_CURVE_2 = 22;
    public static final int DRAW_BEZIER_CURVE_3 = 33;
    public static Integer DRAW_BEZIER_CURVE = null;

    public static final int DRAW_BEZIER_CURVE_ANIM_3 = 333;
}
