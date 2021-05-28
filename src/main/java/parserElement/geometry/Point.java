/**
 * File     : Point.java
 * Authors  : Gaétan Zwick, Alessandro Parrino
 * Date     : 18.05.2021
 */

package parserElement.geometry;

import org.jdom2.Element;
import org.json.simple.JSONArray;

/**
 * Classe Point, une coordonée (x,y)
 */
public class Point extends Geometry { //TODO Faire Heruter Point de Geometry??
    private final double x;
    private final double y;

    /**
     * Constructeur privé de Point
     * @param x coordonnée X
     * @param y coordonnée Y
     */
    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Parse un Point JSON en objet Point
     * @param geometryJSON JSONObject du Point
     * @return un Point
     */
    public static Geometry parse(JSONArray geometryJSON) {
        Double x = (Double) geometryJSON.get(0);
        Double y = (Double) geometryJSON.get(1);
        return new Point(x, y);
    }

    /**
     * Transforme l'instance en Element KML
     * @return Point en Element KML
     */
    @Override
    public Element toKML() {
        Element coordinates = new Element("coordinates")
                .addContent(toString());
        return new Element(getClassName()).addContent(coordinates);
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return x + "," + y + " ";
    }
}
