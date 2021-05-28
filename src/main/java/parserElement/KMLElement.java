/**
 * File     : KMLElement.java
 * Authors  : Gaétan Zwick, Alessandro Parrino
 * Date     : 18.05.2021
 */

package parserElement;

import org.jdom2.Element;

/**
 * Interface représentant un Element convertible en KML
 */
public interface KMLElement {
    /**
     * Converti l'objet en Element KML
     * @return Element KML
     */
    Element toKML();
}
