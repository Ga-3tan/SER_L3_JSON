package Geometry;

import org.jdom2.Element;

import java.util.LinkedList;
import java.util.List;

public class Polygon extends Geometry {
    private final List<Point> pointList = new LinkedList<>();

    public void add(Point point) {
        pointList.add(point);
    }

    @Override
    public Element toKML() {
        StringBuilder builder = new StringBuilder();

        for (Point point : pointList)
            builder.append(point.toString());

        Element coordinates = new Element("coordinates")
                                .addContent(builder.toString());

        return new Element(getClassName())
                .addContent(new Element("outerBoundaryIs")
                                .addContent(new Element("LinearRing")
                                                .addContent(coordinates)));
    }
}
