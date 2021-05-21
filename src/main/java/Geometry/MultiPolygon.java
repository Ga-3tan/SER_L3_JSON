package Geometry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class MultiPolygon extends Geometry {
    private final List<Polygon> polygonList = new LinkedList<>();

    public static Geometry parse(JSONArray geometryJSON) {
        MultiPolygon multiPolygon = new MultiPolygon();
        for (Object polygon : geometryJSON) {
            multiPolygon.add((Polygon) Polygon.parse((JSONArray) polygon));
        }
        return multiPolygon;
    }

    public void add(Polygon polygon) {
        polygonList.add(polygon);
    }
}
