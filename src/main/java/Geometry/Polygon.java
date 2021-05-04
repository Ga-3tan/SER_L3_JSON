package Geometry;

import java.util.LinkedList;
import java.util.List;

public class Polygon extends Geometry {
    private final List<Point> pointList = new LinkedList<>();

    public void add(Point point) {
        pointList.add(point);
    }
}
