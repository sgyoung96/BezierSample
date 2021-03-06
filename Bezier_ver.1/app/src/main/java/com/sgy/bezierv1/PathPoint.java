package com.sgy.bezierv1;

public class PathPoint {
    public double x;
    public double y;
    public double z;

    public PathPoint() {
        super();
    }
    public PathPoint(double x) {
        super();
        this.x = x;
    }
    public PathPoint(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }
    public PathPoint(double x, double y, double z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
