/**
 * File     : Polygon.java
 * Authors  : Gaétan Zwick, Alessandro Parrino
 * Date     : 18.05.2021
 */

package parserElement.geometry;

import org.json.simple.JSONArray;
import org.jdom2.Element;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe Polygon, cette classe contient les frontières d'un pays (frontière interne inclue)
 */
public class Polygon extends Geometry {
    /* Les frontieres sous forme d'une liste de liste de points */
    private final List<List<Point>> boundariesList = new LinkedList<>();

    /**
     * Constructeur privé de Polygon
     */
    private Polygon() {
    }

    /**
     * Parse un Polygon JSON en objet Polygon
     * @param geometryJSON JSONObject du Polygon
     * @return un Polygon
     */
    public static Geometry parse(JSONArray geometryJSON) {
        Polygon polygon = new Polygon();
        for (Object boundary : geometryJSON)
            polygon.addBoundaries(parseBoundary(boundary));
        return polygon;
    }

    /**
     * Parse une frontière en liste de Point
     * @param boundary frontière à transformer en liste de coordonnée
     * @return une boundary (une liste de Point)
     */
    public static List<Point> parseBoundary(Object boundary) {
        List<Point> boundaryOut = new LinkedList<>();
        for (Object point : (JSONArray) boundary)
            boundaryOut.add((Point) Point.parse((JSONArray) point));
        return boundaryOut;
    }

    /**
     * Ajoute une frontière la liste de frontière
     * @param boundary une frontière à rajouter
     */
    public void addBoundaries(List<Point> boundary) {
        boundariesList.add(boundary);
    }

    /**
     * Transforme l'instance en Element KML
     * @return Polygon en Element KML
     */
    @Override
    public Element toKML() {
        Element polygon = new Element(getClassName());
        StringBuilder builder = new StringBuilder();
        int size = boundariesList.size();
        for (int i = 0; i < size; ++i) {

            for (Point point : boundariesList.get(i)) {
                builder.append(point.toString());
            }

            String polygonType = i == 0 ? "outerBoundaryIs" : "innerBoundaryIs";

            Element coordinates = new Element("coordinates").addContent(builder.toString());

            polygon.addContent(new Element(polygonType)
                    .addContent(new Element("LinearRing")
                    .addContent(coordinates)));

            builder.setLength(0);
        }
        return polygon;

    }

    /**
     * Méthode toString
     * @return Un polygon sous la forme d'une String
     */
    @Override
    public String toString() {
        int out = 0;
        for (List<Point> boundaries : boundariesList)
            for (Point point : boundaries) out++;
        return "\t- " + out + " coordinates";
    }
}
