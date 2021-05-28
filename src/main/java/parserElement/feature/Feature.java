/**
 * File     : Feature.java
 * Authors  : Gaétan Zwick, Alessandro Parrino
 * Date     : 18.05.2021
 */

package parserElement.feature;

import parserElement.geometry.*;
import parserElement.KMLElement;
import org.jdom2.Element;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *  Classe représentant une Feature (Pays)
 */
public class Feature implements KMLElement {
    private final Properties properties;
    private final Geometry geometry;
    private final String styleId;

    /**
     * Constructeur de Feature
     * @param properties les propriétés (meta data du pays)
     * @param geometry les frontières
     * @param styleId le style de représentation KML
     */
    private Feature(Properties properties, Geometry geometry, String styleId) {
        this.properties = properties;
        this.geometry = geometry;
        this.styleId = styleId;
    }

    /**
     * Parse un Feature JSON en objet Feature
     * @param pays JSONObject de la feature
     * @param styleId style du Feature pour le KML
     * @return une Feature
     */
    public static Feature parse(JSONObject pays, String styleId) {
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

        return new Feature(properties, geometry, styleId);
    }

    /**
     * Transforme l'instance en Element KML
     * @return Feature en Element KML
     */
    @Override
    public Element toKML() {
        Element placemark = new Element("Placemark");
        placemark.addContent(new Element("name").addContent(properties.getADMIN()));
        placemark.addContent(new Element("styleUrl").addContent(styleId));
        placemark.addContent(properties.toKML());
        placemark.addContent(geometry.toKML());
        return placemark;
    }

    /**
     * Méthode toString
     * @return Le pays sous la forme d'une String
     */
    @Override
    public String toString() {
        return properties + "\n" + geometry;
    }
}
