package Geometry;

import java.util.LinkedList;
import java.util.List;

public class MultiPolygon extends Geometry {
    private final List<Polygon> polygonList = new LinkedList<>();

    public void add(Polygon polygon) {
        polygonList.add(polygon);
    }
}
