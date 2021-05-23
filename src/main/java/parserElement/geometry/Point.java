package parserElement.geometry;

import org.jdom2.Element;
import org.json.simple.JSONArray;

public class Point extends Geometry {
    private final double x;
    private final double y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public static Geometry parse(JSONArray geometryJSON) {
        Double x = (Double) geometryJSON.get(0);
        Double y = (Double) geometryJSON.get(1);
        return new Point(x, y);
    }

    public String toString() {
        return x + "," + y + " ";
    }

    @Override
    public Element toKML() {
        Element coordinates = new Element("coordinates")
                .addContent(toString());
        return new Element(getClassName()).addContent(coordinates);
    }
}
