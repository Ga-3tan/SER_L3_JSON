package ParserElement.style;

import ParserElement.KMLElement;
import org.jdom2.Element;

public class Style implements KMLElement {
    private final String id;
    private final String color;
    private final int width;
    private final int fill;

    public Style(String id, String color, int width, int fill) {
        this.id = id;
        this.color = color;
        this.width = width;
        this.fill = fill;
    }


    public String getId() {
        return "#" + id;
    }

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
