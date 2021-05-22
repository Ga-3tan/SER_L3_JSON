package ParserElement.Geometry;

import org.jdom2.Element;
import ParserElement.KMLElement;

public abstract class Geometry implements KMLElement {

    public String getClassName() {
        return getClass().getSimpleName();
    }

    public abstract Element toKML();
}
