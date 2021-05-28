/**
 * File     : Style.java
 * Authors  : Gaétan Zwick, Alessandro Parrino
 * Date     : 18.05.2021
 */

package parserElement.style;

import parserElement.KMLElement;
import org.jdom2.Element;

/**
 * Style KML pour Google Earth Pro
 */
public class Style implements KMLElement {
    private final String id;
    private final String color;
    private final int width;
    private final int fill;

    /**
     * Constructeur de style KML
     * @param id
     * @param color
     * @param width
     * @param fill
     */
    public Style(String id, String color, int width, int fill) {
        this.id = id;
        this.color = color;
        this.width = width;
        this.fill = fill;
    }

    /**
     * donne l'id du Style
     * @return #id
     */
    public String getId() {
        return "#" + id;
    }

    /**
     * Transforme l'instance en Element KML
     * @return Style en Element KML
     */
    @Override
    public Element toKML() {
        Element style = new Element(getClass().getSimpleName()).setAttribute("id", id);
        Element lineStyle = new Element("LineStyle")
                .addContent(new Element("color").addContent(color))
                .addContent(new Element("width").addContent(Integer.toString(width)));

        Element polyStyle = new Element("PolyStyle")
                .addContent(new Element("fill").addContent(Integer.toString(fill)));
        style.addContent(lineStyle).addContent(polyStyle);
        return style;
    }
}
