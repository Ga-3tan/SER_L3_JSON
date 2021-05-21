package Geometry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Point extends Geometry {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Geometry parse(JSONArray geometryJSON) {
        Double x = (Double) geometryJSON.get(0);
        Double y = (Double) geometryJSON.get(1);
        return new Point(x,y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
