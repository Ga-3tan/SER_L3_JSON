package Feature;

import Geometry.Geometry;
import org.jdom2.Element;



public class Feature { /* PAYS */
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

    public Element toKML(){
        Element placemark = new Element("Placemark");
        placemark.addContent(new Element("name").addContent(properties.getADMIN()));
        placemark.addContent(new Element("styleUrl").addContent(styleId));
        placemark.addContent(properties.toKML());
        placemark.addContent(geometry.toKML());
        return placemark;}
}
