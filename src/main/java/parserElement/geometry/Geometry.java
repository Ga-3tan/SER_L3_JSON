package parserElement.geometry;

import org.jdom2.Element;
import parserElement.KMLElement;

public abstract class Geometry implements KMLElement {

    public String getClassName() {
        return getClass().getSimpleName();
    }

    public abstract Element toKML();
}
