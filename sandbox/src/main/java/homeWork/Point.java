package homeWork;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    ;

    public double distance1(Point p2) {

        return sqrt(pow((p2.x - this.x), 2) + pow((p2.y - this.y), 2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
