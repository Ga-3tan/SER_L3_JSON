package ParserElement.Geometry;

import org.jdom2.Element;
import org.json.simple.JSONArray;

import java.util.LinkedList;
import java.util.List;

public class MultiGeometry extends Geometry {
    private final List<Polygon> polygonList = new LinkedList<>();

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

}
