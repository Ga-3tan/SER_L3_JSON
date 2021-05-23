package parserElement.geometry;

import org.json.simple.JSONArray;
import org.jdom2.Element;
import java.util.LinkedList;
import java.util.List;

public class Polygon extends Geometry {
    private final List<List<Point>> ringList = new LinkedList<>();

    private Polygon() { }

    public static Geometry parse(JSONArray geometryJSON) {
        Polygon polygon = new Polygon();
        for (Object ring : geometryJSON)
            polygon.addRing(parseRing(ring));
        return polygon;
    }

    public static List<Point> parseRing(Object rings) {
        List<Point> ring = new LinkedList<>();
        for (Object point : (JSONArray) rings)
            ring.add((Point) Point.parse((JSONArray) point));
        return ring;
    }

    public void addRing(List<Point> ring) {
        ringList.add(ring);
    }

    @Override
    public Element toKML() {
        Element polygon = new Element(getClassName());
        StringBuilder builder = new StringBuilder();
        int size = ringList.size();
        for(int i = 0; i < size; ++i){

            for (Point point : ringList.get(i)) {
                builder.append(point.toString());
            }

            String polygonType = i == 0 ? "outerBoundaryIs" :
                                          "innerBoundaryIs";

            Element coordinates = new Element("coordinates")
                    .addContent(builder.toString());

            polygon.addContent(new Element(polygonType)
                                   .addContent(new Element("LinearRing")
                                                    .addContent(coordinates)));
            builder.setLength(0);
        }
        return polygon;

    }

    @Override
    public String toString() {
        int out = 0;
        for (List<Point> ring : ringList)
            for (Point p : ring) out++;
        return "\t- " + out + " coordinates";
    }
}
