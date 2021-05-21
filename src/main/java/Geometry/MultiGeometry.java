package Geometry;

import org.jdom2.Element;

import java.util.LinkedList;
import java.util.List;

public class MultiGeometry extends Geometry {
    private final List<Polygon> polygonList = new LinkedList<>();

    public void add(Polygon polygon) {
        polygonList.add(polygon);
    }

    @Override
    public Element toKML() {
        Element multiGeometry = new Element(getClassName());
        polygonList.forEach( polygon -> multiGeometry.addContent(polygon.toKML()));

        return multiGeometry;
    }
}
