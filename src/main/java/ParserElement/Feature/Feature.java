package ParserElement.Feature;

import ParserElement.Geometry.Geometry;
import ParserElement.Geometry.MultiGeometry;
import ParserElement.Geometry.Point;
import ParserElement.Geometry.Polygon;
import ParserElement.KMLElement;
import org.jdom2.Element;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Feature implements KMLElement {
    private final Properties properties;
    private final Geometry geometry;
    private final String styleId;

    public Feature(Properties properties, Geometry geometry, String styleId) {
        this.properties = properties;
        this.geometry = geometry;
        this.styleId = styleId;
    }

    public Properties getProperties() {
        return properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public static Feature parse(JSONObject pays) {
        JSONObject propertiesJSON = (JSONObject) pays.get("properties");
        Properties properties = Properties.parse(propertiesJSON);
        JSONObject geometryJSON = (JSONObject) pays.get("geometry");

        Geometry geometry;
        switch ((String) geometryJSON.get("type")) {
            case "MultiPolygon":
                geometry = MultiGeometry.parse((JSONArray) geometryJSON.get("coordinates"));
                break;
            case "Polygon":
                geometry = Polygon.parse((JSONArray) geometryJSON.get("coordinates"));
                break;
            case "Point":
                geometry = Point.parse((JSONArray) geometryJSON.get("coordinates"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + geometryJSON.get("type"));
        }

        return new Feature(properties, geometry, null);
    }

    @Override
    public Element toKML() {
        Element placemark = new Element("Placemark");
        placemark.addContent(new Element("name").addContent(properties.getADMIN()));
        placemark.addContent(new Element("styleUrl").addContent(styleId));
        placemark.addContent(properties.toKML());
        placemark.addContent(geometry.toKML());
        return placemark;
    }
}