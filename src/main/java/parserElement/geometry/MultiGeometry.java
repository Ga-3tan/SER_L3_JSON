/**
 * File     : MultiGeometry.java
 * Authors  : Gaétan Zwick, Alessandro Parrino
 * Date     : 18.05.2021
 */

package parserElement.geometry;

import org.jdom2.Element;
import org.json.simple.JSONArray;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe MultiGeometry (MultiPolygon), une Geometry qui contient une liste de Polygon
 */
public class MultiGeometry extends Geometry {
    private final List<Polygon> polygonList = new LinkedList<>();

    /**
     * Constructeur privé de MutliGeometry
     */
    private MultiGeometry() { }

    /**
     * Ajoute un Polygon à la liste de polygon de MutliGeometry
     * @param polygon polygon à rajouter
     */
    public void add(Polygon polygon) {
        polygonList.add(polygon);
    }

    /**
     * Parse un MutliGeometry JSON en objet MutliGeometry
     * @param geometryJSON JSONObject de la MutliGeometry
     * @return une MutliGeometry
     */
    public static Geometry parse(JSONArray geometryJSON) {
        MultiGeometry multiGeometry = new MultiGeometry();
        for (Object polygon : geometryJSON) {
            multiGeometry.add((Polygon) Polygon.parse((JSONArray) polygon));
        }
        return multiGeometry;
    }

    /**
     * Transforme l'instance en Element KML
     * @return MultiGeometry en Element KML
     */
    @Override
    public Element toKML() {
        Element multiGeometry = new Element(getClassName());

        polygonList.forEach(polygon -> multiGeometry.addContent(polygon.toKML()));

        return multiGeometry;
    }

    /**
     * Méthode toString
     * @return Les polygons sous la forme d'une String
     */
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
