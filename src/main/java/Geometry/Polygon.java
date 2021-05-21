package Geometry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Polygon extends Geometry {
    private final List<List<Point>> ringList = new LinkedList<>();

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
}
