package homeWork;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

    @Test
    public void test1Distance() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        assert p1.distance1(p2) < 15;
    }

    @Test
    public void negativeTestDistance1() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        assert p1.distance1(p2) != 14.142135623730951;
    }

    @Test
    public void Test2Distance() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Assert.assertEquals(p1.distance1(p2), 14.142135623730951);
    }

    @Test
    public void negativeTestDistance2() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Assert.assertEquals(p1.distance1(p2), 14.143);
    }
}
