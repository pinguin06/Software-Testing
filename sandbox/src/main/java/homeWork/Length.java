package homeWork;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Length {


    public static double distance(Point p1, Point p2) {

        return sqrt(pow((p2.x - p1.x), 2) + pow((p2.y - p1.y), 2));
    }

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        System.out.println("Вычисление расстояния с помощью функции");
        System.out.println("Расстояние между двумя точками p1("+p1.x+","+p1.y+") и p2("+p2.x+","+p2.y+") = "+ distance(p1, p2));
        System.out.println("Вычисление расстояния с помощью метода");
        System.out.println("Расстояние между двумя точками p1("+p1.x+","+p1.y+") и p2("+p2.x+","+p2.y+") = "+ p1.distance1(p2));
    }

}
