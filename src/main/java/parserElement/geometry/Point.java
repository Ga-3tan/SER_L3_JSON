package parserElement.geometry;

import org.jdom2.Element;
import org.json.simple.JSONArray;

public class Point extends Geometry { //TODO Faire Heruter Point de Geometry??
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Geometry parse(JSONArray geometryJSON) {
        Double x = (Double) geometryJSON.get(0);
        Double y = (Double) geometryJSON.get(1);
        return new Point(x, y);
    }

    public double getX() {
        return x;
    }//TODO Garder les Getters que on utilsie pas?? (Aussi pour le autres classes)

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + "," + y + " ";
    }

    @Override
    public Element toKML() {
        return null;
    }

}
