/**
 * File     : Properties.java
 * Authors  : Gaétan Zwick, Alessandro Parrino
 * Date     : 18.05.2021
 */

package parserElement.geometry;

import org.jdom2.Element;
import parserElement.KMLElement;

/**
 * Classe abstraite Geometry
 */
public abstract class Geometry implements KMLElement {
    /**
     * Donne le nom de la classe
     * @return String du nom de la classe
     */
    public String getClassName() {
        return getClass().getSimpleName();
    }

    /**
     * Transforme l'instance en Element KML
     * @return Geometry en Element KML
     */
    public abstract Element toKML();
}
