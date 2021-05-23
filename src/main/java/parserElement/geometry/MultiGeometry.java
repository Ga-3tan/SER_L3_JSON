package parserElement.geometry;

import org.jdom2.Element;
import org.json.simple.JSONArray;
import java.util.LinkedList;
import java.util.List;

public class MultiGeometry extends Geometry {
    private final List<Polygon> polygonList = new LinkedList<>();

    private MultiGeometry() {}

    public void add(Polygon polygon) {
        polygonList.add(polygon);
    }

    public static Geometry parse(JSONArray geometryJSON) {
        MultiGeometry multiGeometry = new MultiGeometry();
        for (Object polygon : geometryJSON) {
            multiGeometry.add((Polygon) Polygon.parse((JSONArray) polygon));
        }
        return multiGeometry;
    }

    @Override
    public Element toKML() {
        Element multiGeometry = new Element(getClassName());
        polygonList.forEach(polygon -> multiGeometry.addContent(polygon.toKML()));

        return multiGeometry;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < polygonList.size(); i++) {
            out.append(polygonList.get(i));
            if (i != polygonList.size()-1) out.append("\n");
        }
        return out.toString();
    }
}
